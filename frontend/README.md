# ToDo Management Application

This project is a web application for managing ToDos and assigning them to Assignees. It allows users to create, update, delete, and manage ToDos and Assignees using a Vue.js frontend and a REST API backend.

## Features

### Assignee Management
- **View All Assignees**: Displays a list or table of all assignees.
- **Create Assignee**: Allows adding a new assignee.
- **Edit/Delete Assignee**: Enables updating or removing an assignee.

### ToDo Management
- **View All ToDos**: Lists all ToDos, grouped by:
    - **Pending ToDos**: Tasks that are yet to be marked as finished.
    - **Finished ToDos**: Completed tasks are hidden in a collapsible section.
- **Create ToDo**: Add a new task, optionally assign it to an Assignee.
- **Mark as Finished**: Easily toggle the "finished" state of a ToDo.
- **Filter and Sort**: ToDos can be:
    - Filtered by `title` using a substring filter.
    - Sorted by `title` and `dueDate`, both ascending and descending.

## Project Structure

The project consists of:
1. **Backend**: REST API built in (insert your framework/technology here, e.g., Spring Boot).
2. **Frontend**: Vue.js application for managing ToDos and Assignees.

## Prerequisites

Ensure you have the following installed:
- [Node.js](https://nodejs.org) (v16 or later recommended)
- [npm](https://www.npmjs.com/) (comes with Node.js)
- Check the backend README.md

## Setup Instructions

### Backend Setup
1. Read the README.md in the api folder to configure backend


### Frontend Setup
1. Install dependencies 
 ```bash
npm install
  ```
2. Start the development server : 
 ```bash
npm run dev
  ```

3. Access the frontend :
- Open http://localhost:5173