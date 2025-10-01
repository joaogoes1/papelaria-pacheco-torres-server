# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

Spring Boot application for Papelaria Pacheco Torres - a stationery store management system built with Java 21, Spring Boot 3.5.3, PostgreSQL, and JWT authentication. The system manages clients, products, inventory, sales, and reports.

## Development Commands

### Running the Application
```bash
# Start all services (app, postgres, evolution-api, n8n) with Docker Compose
docker compose up

# Rebuild and restart services
make  # equivalent to: docker compose down && docker compose up -d
```

The application runs on `http://localhost:8080` with the following services:
- **Backend API**: port 8080
- **PostgreSQL**: port 5432
- **Evolution API**: port 8081
- **n8n**: port 5678

### Building and Testing
```bash
# Build the project
./gradlew build

# Run all tests
./gradlew test

# Run tests for a specific class
./gradlew test --tests ClassName

# Clean build artifacts
./gradlew clean

# View dependency tree
./gradlew dependencies
```

### Database
- Uses PostgreSQL with JPA/Hibernate
- Schema is managed via [schema.sql](src/main/resources/schema.sql) (DDL-auto set to `validate`)
- Initial data loaded from [data.sql](src/main/resources/data.sql)
- SQL initialization runs on every startup (`spring.sql.init.mode=always`)

## Architecture

### Layered Architecture
The codebase follows a standard Spring Boot layered architecture with module-based organization:

```
Controller → Service → Repository → Entity (JPA)
```

Each business domain (clientes, produtos, estoque, vendas) is organized as a separate package with:
- **Entity**: JPA entity with PostgreSQL mappings
- **Repository**: Spring Data JPA repository interface
- **Service**: Business logic layer
- **Controller**: REST API endpoints
- **DTO**: Data transfer objects (where applicable)

### Key Modules

**Authentication (`auth/`)**
- JWT-based authentication with stateless sessions
- `/login` endpoint is public; all other endpoints require JWT token
- `SecurityConfig` configures Spring Security with JWT filter
- `JwtRequestFilter` intercepts requests and validates tokens
- Mock users stored in `MockUserRepository` for development

**Domain Modules**
- `clientes/` - Client management
- `produtos/` - Product catalog
- `estoque/` - Inventory tracking with minimum stock alerts
- `vendas/` - Sales transactions with line items (`itens_venda`)
- `relatorios/` - CSV import/export functionality
- `dashboard/` - Analytics and chart data

### Database Schema

Primary tables with relationships:
- `clientes` → `vendas` (one-to-many)
- `produtos` → `estoque` (one-to-one)
- `produtos` ← `itens_venda` → `vendas` (many-to-many via junction table)

Foreign key constraints are enforced at the database level.

### CORS Configuration

Global CORS is configured in [PapelariaPachecoTorresApplication.java](src/main/java/org/papelariapachecotorres/PapelariaPachecoTorresApplication.java):
- Allows all origins (`*`)
- Supports GET, POST, PUT, DELETE, OPTIONS
- Allows all headers

### CSV Reports

The `relatorios/` module provides CSV export for clients, products, inventory, and sales. Files are saved to the `reports/` directory. Client import is also supported via CSV upload.

## Environment Configuration

Required environment variables (see `.env`):
- `POSTGRES_DB`, `POSTGRES_USER`, `POSTGRES_PASSWORD` - PostgreSQL credentials
- `SPRING_DATASOURCE_URL`, `SPRING_DATASOURCE_USERNAME`, `SPRING_DATASOURCE_PASSWORD` - Spring datasource config
- `EVOLUTION_API_AUTHENTICATION_API_KEY` - Evolution API key
- `DATABASE_ENABLED`, `DATABASE_PROVIDER`, `DATABASE_CONNECTION_URI` - Evolution API database config
- `GENERIC_TIMEZONE` - n8n timezone setting

## Frontend Integration

This backend is designed to work with a React frontend located in a separate repository (`PapelariaPachecoTorres` in CamelCase). The frontend runs on `http://localhost:5173` during development.