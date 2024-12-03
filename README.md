# ToDo Management Application

This project is a comprehensive web application designed for managing ToDos and their respective Assignees. It combines a **Vue.js frontend** and a **REST API backend** built with Spring Boot to provide an intuitive user experience for task and assignee management.

Created by :  Hristo Baldzhiyski ,  student at University of Stuttgart 

## Features

### Assignee Management
- **View All Assignees**: Displays a list or table of all assignees.
- **Create Assignee**: Add new assignees to the system.
- **Edit/Delete Assignee**: Update or remove existing assignees.

### ToDo Management
- **View All ToDos**: Organized into:
    - **Pending ToDos**: Tasks that need to be completed.
    - **Finished ToDos**: Completed tasks are hidden under a collapsible section.
- **Create ToDo**: Add new tasks with optional assignee assignment.
- **Mark as Finished**: Toggle the "finished" state of any ToDo.
- **Filter and Sort**: Filter tasks by `title` and sort by `title` or `dueDate` (ascending/descending).

### Backend Features
- **CRUD Operations**: For `ToDos` and `Assignees`.
- **Assignment Handling**: Assign or unassign `ToDos` to/from `Assignees`.
- **Model Mapping**: Simplifies data transfer between DTOs and entities using ModelMapper.
- **Database Initialization**: Preloads the database with sample data on first startup for easier testing.

## Technology Stack

- **Frontend**: Vue.js
- **Backend**:
    - Java 17
    - Spring Boot (Spring Web, Spring Data JPA)
    - MariaDB for data storage
    - ModelMapper for object mapping
    - Maven for dependency management

---

## Setup and Running

### Prerequisites
- **Frontend**:
    - [Node.js](https://nodejs.org) (v16+ recommended)
    - [npm](https://www.npmjs.com/) (included with Node.js)
- **Backend**:
    - Java 17 or later
    - Maven
    - MariaDB (local instance or Docker container)

---

### Frontend Setup
1. Clone the repository:
   ```bash  
   git clone https://github.com/your-repository/todo-management.git  
   cd todo-management/frontend  
   ```

2. Start the development server :
 ```bash
npm run dev
  ```

3. Access the frontend :
- Open http://localhost:5173

### Backend Setup

1. Run the following docker command :
```bash
  docker run -d --name mariadb-container -e MARIADB_ROOT_USER=root -e MARIADB_ROOT_PASSWORD=root -e MARIADB_DATABASE=ToDoDB -p 3306:3306 mariadb:latest

```

2. Build the project using Maven:
```bash
  mvn clean install
```

3. Start the app
```bash
  java -jar target/api-0.0.1-SNAPSHOT.jar
```

4. Access the API
 ```bash
  http://localhost:8080
```

### Summary
- This ToDo Management Application enables efficient task and assignee handling with a feature-rich frontend and robust backend. With its intuitive UI and RESTful API, it simplifies task delegation and management, making it ideal for personal and team use.