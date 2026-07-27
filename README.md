# ❤️ Lovable App

Lovable App is a full-stack relationship and social interaction platform designed to help users connect, communicate, and engage through a modern and secure web application. The project follows a layered architecture with a Java Spring Boot backend, RESTful APIs, JWT-based authentication, and a responsive frontend to deliver a seamless user experience.

---

## 🚀 Features

- User Registration & Authentication
- JWT-based Secure Authorization
- Role-Based Access Control (RBAC)
- User Profile Management
- Friend Request & Connection Management
- Real-time Chat Architecture *(planned)*
- Secure REST APIs
- Input Validation & Exception Handling
- Responsive User Interface
- Scalable Layered Architecture

---

## 🛠️ Tech Stack

### Backend
- Java 17
- Spring Boot
- Spring Security
- Spring Data JPA
- Hibernate
- JWT Authentication

### Database
- PostgreSQL

### Build Tool
- Maven

### Development Tools
- IntelliJ IDEA
- Postman
- Git & GitHub

---

## 🏗️ System Architecture

```
Client
   │
   ▼
REST API
   │
   ▼
Spring Security (JWT)
   │
   ▼
Controller
   │
   ▼
Service Layer
   │
   ▼
Repository (JPA)
   │
   ▼
PostgreSQL
```

---

## 📂 Project Structure

```
Lovable-App/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── config/
│   │   │   ├── controller/
│   │   │   ├── dto/
│   │   │   ├── entity/
│   │   │   ├── exception/
│   │   │   ├── repository/
│   │   │   ├── security/
│   │   │   ├── service/
│   │   │   └── util/
│   │   └── resources/
│   └── test/
│
├── pom.xml
└── README.md
```

---

## ⚙️ Getting Started

### Clone the Repository

```bash
git clone https://github.com/istiakahasan/Lovable-App.git
cd Lovable-App
```

### Install Dependencies

```bash
mvn clean install
```

### Configure Database

Update the `application.properties` file with your PostgreSQL credentials.

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/lovable_db
spring.datasource.username=your_username
spring.datasource.password=your_password
```

### Run the Application

```bash
mvn spring-boot:run
```

The application will be available at:

```
http://localhost:8080
```

---

## 🔐 Authentication

The application uses **JWT (JSON Web Token)** for secure authentication.

Authentication flow:

```
User Login
     │
     ▼
Authenticate Credentials
     │
     ▼
Generate JWT Token
     │
     ▼
Client Stores Token
     │
     ▼
Protected API Requests
```

---

## 📌 Core Modules

- Authentication & Authorization
- User Management
- Profile Management
- Friend Management
- Security Configuration
- Exception Handling
- Validation
- REST APIs

---

## 🌟 Future Enhancements

- Real-time Messaging using WebSockets
- Notifications
- Media Sharing
- Group Chats
- AI-powered Recommendations
- Docker Deployment
- Kubernetes Deployment
- CI/CD Pipeline
- Cloud Deployment (AWS)

---

## 📖 Learning Objectives

This project demonstrates:

- Spring Boot Development
- Spring Security
- JWT Authentication
- REST API Design
- Layered Architecture
- JPA & Hibernate
- PostgreSQL Integration
- Backend Best Practices

---

## 👨‍💻 Author

**Istiak Ahsan**


---

## 🚧 Project Status

> **This project is currently under active development.**
>
> Core backend functionalities have been implemented, and additional features, performance improvements, and production-ready enhancements are being added continuously. The repository will be updated regularly as development progresses.
```
