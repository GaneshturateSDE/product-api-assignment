# 📦 Product Management API (Spring Boot)

## 🚀 Overview

This project is a **RESTful Product Management API** built using **Spring Boot**.
It supports **CRUD operations, JWT authentication (Access + Refresh Token), pagination, and item management**.

---

## 🧠 Features

* ✅ Product CRUD APIs
* ✅ Get Items by Product
* ✅ Pagination & Sorting
* ✅ JWT Authentication (Access Token)
* ✅ Refresh Token (DB-based)
* ✅ Input Validation (Jakarta Validation)
* ✅ Global Exception Handling
* ✅ Swagger/OpenAPI Documentation
* ✅ Unit  Testing
* ✅ Docker Ready

---

## 🏗️ Architecture (Layered)

```id="arch1"
Controller → Service → Repository → Database
```

### 🔹 Layers Explained

* **Controller Layer**

  * Handles HTTP requests/responses
  * Example: `ProductController`

* **Service Layer**

  * Business logic
  * Example: `ProductServiceImpl`

* **Repository Layer**

  * Data access using JPA
  * Example: `ProductRepository`

* **DTO Layer**

  * Request/Response objects
  * Avoid exposing entities

* **Security Layer**

  * JWT authentication
  * Refresh token handling

---

## 📁 Project Structure

```id="structure1"
com.products.products
│
├── config
├── controller
├── dto
├── entity
├── repository
├── services
├── servicesImpl
├── security
└── exception
```

---

## ⚙️ Setup Instructions

### 🔧 Prerequisites

* Java 17+
* Maven
* MySQL / PostgreSQL

---

### ▶️ Steps to Run

```bash id="setup1"
# Clone project
git clone https://github.com/GaneshturateSDE/product-api-assignment.git

# Navigate
cd products

# Build
mvn clean install

# Run
mvn spring-boot:run
```

---

### 🗄️ Database Configuration

Update `application.properties`:

```properties id="db1"
spring.datasource.url=jdbc:mysql://localhost:3306/product_db
spring.datasource.username=root
spring.datasource.password=yourpassword

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

---

## 🔐 Authentication Flow

1. **Login**

   * Returns `accessToken`
   * Stores `refreshToken` in DB (and cookie optional)

2. **Access APIs**

   * Use:

     ```
     Authorization: Bearer <accessToken>
     ```

3. **Refresh Token**

   * Generate new access token

---

## 🌐 API Endpoints

### 🔹 Product APIs

| Method | Endpoint                      | Description                   |
| ------ | ----------------------------- | ----------------------------- |
| GET    | `/api/v1/products`            | Get all products (pagination) |
| GET    | `/api/v1/products/{id}`       | Get product                   |
| POST   | `/api/v1/products`            | Create product                |
| PUT    | `/api/v1/products/{id}`       | Update                        |
| DELETE | `/api/v1/products/{id}`       | Delete                        |
| GET    | `/api/v1/products/{id}/items` | Get items                     |

---

### 🔹 Auth APIs

| Method | Endpoint               |
| ------ | ---------------------- |
| POST   | `/api/v1/auth/signin`  |
| POST   | `/api/v1/auth/signup`  |
| POST   | `/api/v1/auth/refresh` |

---

## 📊 Pagination Example

```id="page1"
GET /api/v1/products?page=0&size=5&sort=productName,asc
```

---

## 📘 Swagger

```id="swagger1"
http://localhost:8080/swagger-ui/index.html
```

---

## 🧪 Testing

```bash id="test1"
mvn test
```

* Unit Tests (Mockito)

---



---

## 💬 Key Concepts

* JWT Authentication
* Refresh Token Rotation
* Pagination using Pageable
* One-to-Many relationship (Product → Items)

---


