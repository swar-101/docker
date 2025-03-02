# Docker & Microservices: Session 1 Revision Guide

## 1. Containerization vs. Virtualization
- **Virtualization:**  
  - Involves running entire virtual machines with their own OS.
  - Heavier, slower boot times, more resource-intensive.
- **Containerization (Docker):**  
  - Packages an application along with its dependencies into lightweight containers.
  - Shares the host OS kernel; much faster and more resource efficient.
  - Provides consistent environments across development, testing, and production.

## 2. Real-World Use Case: Android App Development
- **Problem:**  
  - Multiple developers working on the same app experience different build environments.
  - Issues can arise due to version differences, varying build tools (like Gradle or Maven), and different dependency versions.
- **Solution:**  
  - Containerize the development environment using Docker.
  - Ensures every developer works with the exact same setup, eliminating "it works on my machine" problems.

## 3. Docker to the Rescue
- **How Docker Helps:**  
  - **Consistency:** Same environment for all developers regardless of their host machine.
  - **Portability:** Easily share and run containers on any system.
  - **Speed:** Containers start quickly, making development and testing more efficient.
- **Analogy:**  
  - Think of a Docker image as an ISO file for PC games (like FIFA) that is “mounted” as a container where your app runs.
  
## 4. Docker Internals Overview
- **Images & Containers:**  
  - **Image:** A snapshot of your app (like a blueprint).
  - **Container:** A running instance of an image.
- **Layers & Caching:**  
  - Docker images are built in layers. This improves build speed and resource usage.
- **Workflow:**  
  - App → Docker Image → Container → Run

## 5. Docker Steps in Practice
- **Basic Commands:**  
  - **Build an Image:**  
    ```bash
    docker build -t <image-name>:<tag> <build-context>
    ```
    *Example for local dev:*  
    ```bash
    docker build -t order-service-image:dev .
    ```
  - **Run a Container:**  
    ```bash
    docker run -p <host_port>:<container_port> --name <container_name> <image-name>:<tag>
    ```
    *Tip:* Keep host port and container port the same (e.g., 8086:8086) for simplicity.
- **Docker Desktop:**  
  - Provides both a GUI and CLI interface.
  - Lets you pull images, view container logs, and manage containers easily.

## 6. CI/CD Integration
- **Continuous Integration:**  
  - Automate building and testing (e.g., using Maven, Jenkins, GitHub Actions).
  - Every change triggers a new image build ensuring up-to-date containers.
- **Continuous Deployment:**  
  - Once tests pass, automatically deploy containers to various environments (DEV, TEST, PERF, PROD).
  - Docker helps manage versioning and rollbacks efficiently.

## 7. Case Study: Microservices Architecture
- **Our Microservices Example:**
  1. **Product Service:**  
     - **Framework/Language:** Spring Boot (Java 17)
     - **Database:** PostgreSQL  
     - **Dockerfile:** Uses a multi-stage build with Maven and a JRE runtime.
  2. **Order Service:**  
     - **Framework/Language:** Spring Boot (Java 17)
     - **Database:** PostgreSQL  
     - **Dockerfile:** Similar multi-stage build as Product Service.
  3. **Payment Service:**  
     - **Framework/Language:** Golang
     - **Database:** PostgreSQL  
     - **Dockerfile:** Builds a static binary using CGO disabled.
  4. **User Service:**  
     - **Framework/Language:** Node.js  
     - **Database:** MongoDB

- **Key Principles:**
  - **Container per Service:** Each microservice runs in its own container.
  - **Database per Service:** Each microservice maintains its own database to ensure loose coupling.
  - **Inter-Service Communication:** Managed through well-defined REST APIs.

- **Deployment Workflow:**
  - **Build:** Create Docker images (using Dockerfiles or a batch script for local dev).
  - **Compose:** Use Docker Compose to spin up the entire architecture (each microservice + its database).
  - **Run:** Ensure port mappings and environment variables are correctly set to mimic production.
