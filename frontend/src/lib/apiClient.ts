// lib/apiClient.ts

import config from "../config.ts";

type RequestMethod = 'GET' | 'POST' | 'PUT' | 'DELETE';

class CustomError extends Error {
    fieldErrors?: Record<string, any>;
    generalErrors?: any[];

    constructor(
        errorMessage: string,
        fieldErrors?: Record<string, any>,
        generalErrors?: any[]
    ) {
        super(errorMessage); // Pass the message to the base Error class
        this.name = 'Error'; // Set the error name
        this.fieldErrors = fieldErrors;
        this.generalErrors = generalErrors;
    }
}


interface FetchApiOptions {
    method?: RequestMethod;
    body?: Record<string, unknown> | FormData;
    headers?: Record<string, string>;
}

class ApiClient {
    private baseUrl: string;

    constructor(baseUrl: string) {
        if (!baseUrl) {
            throw new Error('API base URL is not defined.');
        }
        this.baseUrl = baseUrl;
    }

    private async fetchApi<T>(
        endpoint: string,
        { method = 'GET', body, headers = {} }: FetchApiOptions = {}
    ): Promise<T> {
        const url = `${this.baseUrl}/${endpoint}`;
        const isFormData = body instanceof FormData;

        const config: RequestInit = {
            method,
            headers: {
                ...headers,
                ...(isFormData ? {} : { 'Content-Type': 'application/json' }),
            },
            body: isFormData ? body : body ? JSON.stringify(body) : undefined,
        };

            const response = await fetch(url, config);

            if (!response.ok) {
                let errorData;
                try {
                    errorData = await response.json();
                } catch (error) {
                    errorData = { message: 'An error occurred while parsing the error response.' };
                }

                const errorMessage = errorData.message || 'Failed to fetch data from the API';
                const fieldErrors = errorData.errors || {};
                const generalErrors = errorData.generalErrors || [];

                console.error('API Error:', { errorMessage, fieldErrors, generalErrors });

                // Create a custom error object and include additional properties
                const error = new CustomError(errorMessage,fieldErrors,generalErrors);
                error.stack

                throw error;
            }
            // Handle 204 No Content response
            if (response.status === 204) {
                return {} as T; // No content, return an empty object
            }

            // Handle GET, POST, PUT, and DELETE responses
            // For DELETE requests, it could return an empty response or a success message
            if (method === 'DELETE' && response.status === 200) {
                return {} as T;  // No content, returning empty object
            }


            return response.json();

    }

    public async get<T>(endpoint: string, headers?: Record<string, string>): Promise<T> {
        return this.fetchApi<T>(endpoint, { method: 'GET', headers });
    }

    public async post<T>(endpoint: string, body: Record<string, unknown>, headers?: Record<string, string>): Promise<T> {
        return this.fetchApi<T>(endpoint, { method: 'POST', body, headers });
    }

    public async put<T>(endpoint: string, body: Record<string, unknown>, headers?: Record<string, string>): Promise<T> {
        return this.fetchApi<T>(endpoint, { method: 'PUT', body, headers });
    }

    public async delete<T>(endpoint: string, headers?: Record<string, string>): Promise<T> {
        return this.fetchApi<T>(endpoint, { method: 'DELETE', headers });
    }

}

// Create a singleton instance and export it
const apiClient = new ApiClient(config.apiBaseUrl);
export default apiClient;
