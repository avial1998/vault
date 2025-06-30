# Vault

---

> **Copyright © 2025 avial1998**
>
> Permission is hereby granted to any person viewing this code for personal, non-commercial educational purposes only.
>
> **You are NOT permitted to:**
> - Use this code in any commercial product, service, or publication
> - Redistribute or rehost the contents of this repository
> - Use this repository or its contents to train AI models
>
> All rights are otherwise reserved. By accessing this repository, you agree to these terms.
>
> For other use cases, contact avial1998@gmail.com.

Vault is a Spring Boot application that provides a secure API for managing credentials in a vault. It supports storing, retrieving, and managing sensitive information with encryption and robust security configurations.

## Features
- RESTful API for credential management
- Encryption of sensitive data
- Exception handling for common errors
- OpenAPI (Swagger) documentation
- Modular architecture with services, controllers, and repositories
- OAuth2 authentication using Keycloak (Keycloak username is used as the `vaultId`)

## Architecture

### VaultApplication
The `VaultApplication` class is the main entry point of the application. It uses the `@SpringBootApplication` annotation to enable component scanning and auto-configuration. The application is documented using OpenAPI (Swagger) for easy API exploration and testing. The main method starts the Spring Boot application.

### Authentication Architecture
Authentication is handled using OAuth2 with Keycloak as the identity provider. The security configuration is managed in the `SecurityConfig` class. Key points:
- All API endpoints are secured and require a valid OAuth2 token.
- Keycloak manages user authentication and issues JWT tokens.
- The authenticated user's Keycloak username is used as the `vaultId` to uniquely identify and segregate each user's credentials.
- The application validates the token and extracts the username for all credential operations, ensuring data isolation and security.

## Getting Started

### Prerequisites
- Java 17 or higher
- Maven 3.6+
- Keycloak server (for authentication)

### Running the Application

1. Clone the repository:
   ```sh
   git clone <repository-url>
   cd vault
   ```
2. Build and run the application:
   ```sh
   mvn spring-boot:run
   ```

The application will start on `http://localhost:8080` by default.

### API Documentation
OpenAPI documentation is available at: `http://localhost:8080/swagger-ui.html`

## Project Structure
- `controllers/` - REST controllers for handling API requests
- `services/` - Business logic and encryption services
- `repositories/` - Data access layer
- `entities/` - JPA entities for database mapping
- `dtos/` - Data Transfer Objects
- `exceptions/` - Custom exception classes and global handler
- `configs/` - Security and encryption configuration

## Example Usage
To store a credential, send a POST request to `/api/credentials` with the required data. Retrieve credentials with a GET request to `/api/credentials/{id}`. All requests must be authenticated using OAuth2 tokens from Keycloak.

## License
This project is licensed under the MIT License.
