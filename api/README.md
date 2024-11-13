# ToDo and Assignee API

This repository contains an API for managing tasks (`ToDos`) and the users assigned to them (`Assignees`). It provides endpoints to create, update, and retrieve tasks, assign users to tasks, and perform other operations on both `ToDos` and `Assignees`.

## Features

- **ToDo Management**: Create, update, delete, and retrieve tasks.
- **Assignee Management**: CRUD Operations for  managing assignees who can be assigned to tasks.
- **Assignment Handling**: Assign `ToDos` to `Assignees` for effective task delegation.
- **Data Initialization**: On the first startup, the application initializes the database with sample data for testing purposes.
- **Model Mapping**: Uses `ModelMapper` to handle object mapping between DTOs and entities, simplifying data transfer across layers.

## Technologies Used

- **Java**
- **Spring Boot**
- **Spring Data JPA** for data access and persistence
- **MariaDB Database**  for storage
- **ModelMapper** for easy conversion between entities and request/response objects
- **Maven** for project management and dependency handling