# Join Backend

This is the backend API for a "Join" application clone, a project management tool, built using Spring Boot.<br>

With the Key Features

* **Java Spring Boot**
* **Spring Security**
* **JWT Authentication**
* **Swagger/OpenAPI**
* **Docker**
* **PostgreSQL**

## Requirements

- Java 21
- PostgreSQL 15

## Installation

1. Clone the repository:
    ```sh
    git clone https://github.com/DerEns86/join_api.git
    cd join_api
    ```

2. Install dependencies:
    ```sh
    mvn clean install
    ```

## Database Setup

This project uses **PostgreSQL 15** running in a Docker container.  
To set up the database, follow these steps:

### Run PostgreSQL with Docker

Start a PostgreSQL 15 container using Docker:

```sh
docker run --name postgres-db -e POSTGRES_USER=myuser -e POSTGRES_PASSWORD=mypassword -e POSTGRES_DB=mydatabase -p 5432:5432 -d postgres:15
```


## Configuration

1. Set the following environment variables to configure the application:
    ```ini
    frontend.url=${FRONTEND_URL}
    spring.datasource.url=jdbc:${DB_URL}
    spring.datasource.username=${DB_USERNAME}
    spring.datasource.password=${DB_PASSWORD}
    spring.app.jwtSecret=${JWT_SECRET}
    ```

2. These environment variables will automatically be used by the Spring Boot application to configure the datasource and other settings.

## Running the Application

1. Start the PostgreSQL database.

2. Run the application:
    ```sh
    mvn spring-boot:run
    ```

## Swagger

- **GET** `/v3/**` - Swagger API documentation
- **GET** `/swagger-ui/**` - Swagger UI

## Security

The application uses JWT for authentication and authorization.

### Roles

- **USER** - Default role for authenticated users.
- **ADMIN** - Role for admin users.

### Password Encoding

Passwords are encoded using BCrypt. The password encoder is defined in the `SecurityConfig` class.
