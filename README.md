# 🍳 Gourmet Kitchen - Recipe Management API

RESTful API para la gestión integral de recetas de cocina e ingredientes, desarrollada con **Spring Boot 4**, **Java 21** y **PostgreSQL**, completamente contenerizada con **Docker Compose**.

---

## 🚀 Características Principales

- **Arquitectura en capas**: Separación limpia entre Controladores, Servicios, Repositorios y Entidades JPA.
- **Consultas avanzadas**:
    - Paginación y ordenación dinámica con Spring Data (`Pageable`, `Sort`).
    - Filtrado multicriterio (búsqueda insensible a mayúsculas/minúsculas por título y filtro por dificultad).
- **Documentación interactiva**: Integración completa con OpenAPI 3 / Swagger UI.
- **Infraestructura contenerizada**: Orquestación reproducible de API y base de datos con Docker y Docker Compose mediante variables de entorno dinámicas.

---

## 🛠️ Stack Tecnológico

- **Lenguaje:** Java 21
- **Framework:** Spring Boot 4.1.0
- **Persistencia:** Spring Data JPA / Hibernate
- **Base de Datos:** PostgreSQL 16
- **Documentación:** SpringDoc OpenAPI 2.8.5 (Swagger UI)
- **Contenedores:** Docker & Docker Compose
- **Construcción:** Maven

---

## 📋 Endpoints Principales

| Método | Endpoint | Descripción |
| :--- | :--- | :--- |
| `GET` | `/api/recipes` | Listado paginado con filtros opcionales (`title`, `difficulty`, `page`, `size`, `sort`) |
| `GET` | `/api/recipes/{id}` | Obtener una receta por su ID |
| `POST` | `/api/recipes` | Crear una nueva receta |
| `PUT` | `/api/recipes/{id}` | Actualizar una receta existente |
| `DELETE` | `/api/recipes/{id}` | Eliminar una receta |

---

## 🐳 Ejecución con Docker (Recomendado)

Requisitos previos: tener instalado **Docker Desktop**.

1. **Clonar el repositorio:**
   ```bash
   git clone https://github.com/Saul-3196/recipe-api.git
   cd recipe-api

2. **Empaquetar la aplicación:**
   ```bash
   ./mvnw clean package -DskipTests
   # O en Windows:
   ./mvnw.cmd clean package -DskipTests
   ```

3. **Levantar los servicios:**
   ```bash
   docker compose up --build -d
   ```

4. **Acceder a la documentación interactiva:**
   Abre en tu navegador:  
   👉 [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

---

## 💻 Ejecución en Local (Sin Docker)

Si prefieres ejecutar PostgreSQL por separado y arrancar la app desde tu IDE:

1. Asegúrate de tener una instancia de PostgreSQL corriendo en el puerto `5432` con la base de datos `gourmetkitchendb`.
2. Ejecuta la clase principal `RecipeApiApplication.java` desde tu IDE o mediante terminal:
   ```bash
   ./mvnw spring-boot:run
   ```

---

## 👤 Saúl Martínez

Desarrollado como proyecto de backend profesional para portafolio.
