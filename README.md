# Spring Boot Vaadin Application: Person Search App

This is a simple Spring Boot application built with Vaadin, providing a basic staff directory with name filtering.

## Features

**Home Page:** A simple "Hello World" page.

**About Page:** An "About" page (potentially with company information, etc.)

**Staff Page:**

* Displays a list of staff members.
* Allows filtering by name (first or last name).
* Allows adding new staff members through refresh button.
* Allows deleting staff from grid through delete button.
* Utilizes a database for storing and retrieving staff data (likely using Spring Data JPA).
* Uses dummy data for initial setup.

## Technologies

* **Java (Version 17):** Programming language 
* **Spring Boot (Version 3.4.2):** Framework for building Java applications. 
* **Vaadin (Version 24.6.5):** UI framework for creating web applications. 
* **Spring Data JPA (Version 3.4.2):** Data access layer for interacting with the database.
* **Maven (Version 4.0.0):** Project dependency management and compilation operations.

## Prerequisites

Before setting up the project, ensure you have the following installed:

- **Java Development Kit (JDK 17 or higher)** - Download
  from [Oracle](https://www.oracle.com/java/technologies/javase-jdk17-downloads.html) or use an open-source alternative
  like [Adoptium](https://adoptium.net/).
- **Maven 4.0.0** - Download from [Apache Maven](https://maven.apache.org/download.cgi).

## Setup and Installation

### 1. Clone the Repository

```sh
  git clone https://github.com/yagmurbarank/person-search-app.git
```

```sh
  cd person-search-app
```

### 2. Build the Application

```sh
  mvn clean install
```

### 3. Run the Application

```sh
  mvn spring-boot:run
```

Alternatively, run the JAR file created in the `target` directory:

```sh
  java -jar target/[your-application-name].jar
```

### 4. Access the Application

- **Home Page:** [http://localhost:8081/](http://localhost:8081/)
- **Hello World Page:** [http://localhost:8081/hello-world](http://localhost:8081/hello-world)
- **About Page:** [http://localhost:8081/about](http://localhost:8081/about)
- **Staff Page:** [http://localhost:8081/staff](http://localhost:8081/staff)

## Usage
This section outlines how to interact with the key features related to staff management within the application.


- **Filtering Staff:** Utilize the search bar (located at the top of the staff list) to filter the displayed staff members by entering a first name or last name. The list will dynamically update to show only matching results.

- **Refreshing Staff Data:** To ensure you have the most up-to-date staff information, use the "Refresh" button, in the bottom-right corner of the screen. This action will reload the staff data from the server. Note that refresh button adds new data for now.

- **Deleting Staff Members:** Select a staff member from the list and click the "Delete" button to remove them from the system. 

# Project Structure

This project follows a modular architecture, prioritizing separation of concerns for enhanced clarity and maintainability.

### **Packages:** 
- **`view` package in `src/main/java`**: Contains server-side Java views, representing different screens/sections of the application's user interface.

- **`repository` package in `src/main/java`**: Implements the data access layer with Spring Data JPA, providing interfaces for CRUD operations on entities.

- **`service` package in `src/main/java`**: Encompasses the application's business logic, managing data, coordinating component interactions, and enforcing business rules.

- **`presenter` package in `src/main/java`**: Manages presentation logic, acting as an intermediary between views and services for data retrieval, formatting, and user interaction handling.

- **`model` package in `src/main/java`**: Houses Data Transfer Objects (DTOs) or ViewModels, decoupling views from underlying data entities.

- **`exception` package in `src/main/java`**: Contains custom exception classes, providing a consistent approach to error handling.


## Screenshots

* **Home Page:**

  ![Home Page Screenshot](https://github.com/yagmurbarank/person-search-app/blob/main/img/1.PNG)
* **Staff Page:**

  ![Staff Page Screenshot](https://github.com/yagmurbarank/person-search-app/blob/main/img/4.PNG)

* **Filtering:**

  ![Filtering Screenshot](https://github.com/yagmurbarank/person-search-app/blob/main/img/5.PNG)

* **About Page:**

  ![About Page Screenshot](https://github.com/yagmurbarank/person-search-app/blob/main/img/2.PNG)

* **Hello Page:**

  ![Hello Page Screenshot](https://github.com/yagmurbarank/person-search-app/blob/main/img/3.PNG)

## Useful links

- [Vaadin Documentation](https://vaadin.com/docs).
- [Vaadin Tutorial](https://vaadin.com/docs/latest/tutorial/overview).
- [Vaadin Start ](https://start.vaadin.com/).
- [Vaadin UI Components](https://vaadin.com/docs/latest/components).
- [Vaadin Examples & Demos](https://vaadin.com/examples-and-demos).
- [Vaadin CSS Utility Classes](https://vaadin.com/docs/styling/lumo/utility-classes).
- [Vaadin Cookbook (Common Solutions)](https://cookbook.vaadin.com/).
- [Vaadin Add-ons](https://vaadin.com/directory).
- [Stack Overflow (Vaadin Questions)](https://stackoverflow.com/questions/tagged/vaadin)
- [Vaadin Forum](https://vaadin.com/forum).
- [GitHub](https://github.com/vaadin).
