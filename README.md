# 🛒 Event-Driven E-Commerce Platform (Microservices Architecture)

A robust, full-stack e-commerce application built on a microservices architecture to ensure high scalability, fault tolerance, and secure transactions. The system is designed to handle high concurrency, leveraging event-driven principles for loose coupling between services.

## ✨ Key Features & Technology Showcase

* **Microservices Orchestration:** Uses **Spring Boot** for core business logic and **Eureka Server** for service discovery, enabling dynamic service registration and client-side load balancing.
* **Event-Driven Communication:** Implemented **Apache Kafka** for asynchronous, reliable communication between services (e.g., placing an order instantly triggers inventory update and notification events).
* **Security & Authentication:** Secured all **RESTful APIs** using **Spring Security** and **JSON Web Tokens (JWT)** for stateless, robust user authentication and authorization.
* **Data Persistence:** Managed relational data using **MySQL** and **JPA/Hibernate** for efficient ORM and transactional integrity.
* **User Interface:** Developed a dynamic and responsive user interface using **ReactJS** for seamless user experience (including user authentication and product management).

## 🛠️ Tech Stack

| Component | Technology | Rationale |
| :--- | :--- | :--- |
| **Backend Framework** | Java, Spring Boot | Enterprise-grade stability, rapid development, and extensive ecosystem. |
| **Service Registry** | Eureka | Essential for microservice discovery and managing service instances. |
| **Messaging Queue** | Apache Kafka | Enables asynchronous, scalable, and durable inter-service communication. |
| **Security** | Spring Security, JWT | Industry-standard for secure and stateless API access control. |
| **Database/ORM** | MySQL, JPA/Hibernate | Reliable relational data storage and efficient object-relational mapping. |
| **Frontend** | ReactJS | Component-based UI for a modern, responsive user experience. |

## 🚀 Getting Started (Run Locally)

1.  **Clone the repository:**
    ```bash
    git clone [Your Repo URL]
    ```
2.  **Start Kafka & MySQL:** Ensure your local Kafka and MySQL instances are running.
3.  **Configure Environment:** Update the application properties files in each microservice to point to your local Eureka, Kafka, and MySQL instances.
4.  **Launch Services (Backend):** Navigate to each microservice folder and run the Spring Boot application:
    ```bash
    ./mvnw spring-boot:run
    ```
5.  **Launch Frontend (React):** Navigate to the frontend directory and install dependencies, then start the server:
    ```bash
    npm install
    npm start
    ```
---
