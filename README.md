# Spring Boot Vaadin Application: Person Search App

This is a simple Spring Boot application built with Vaadin, providing a basic staff directory with name filtering.

## Features

*   **Home Page:** A simple "Hello World" page.
*   **About Page:** An "About" page (potentially with company information, etc.)
*   **Staff Page:**
  *   Displays a list of staff members.
  *   Allows filtering by name (first or last name).
  *   Utilizes a database for storing and retrieving staff data (likely using Spring Data JPA).
  *   Uses dummy data for initial setup.

## Screenshots

*   **Example Home Page:**

    ![Home Page Screenshot](https://github.com/yagmurbarank/person-search-app/blob/main/img/1.PNG)
*   **Example Staff Page:**

    ![Staff Page Screenshot](https://github.com/yagmurbarank/person-search-app/blob/main/img/4.PNG)

*   **Example Filtering:**

    ![Filtering Screenshot](https://github.com/yagmurbarank/person-search-app/blob/main/img/5.PNG)

*   **Example About Page:**

    ![About Page Screenshot](https://github.com/yagmurbarank/person-search-app/blob/main/img/2.PNG)

*   **Example Hello Page:**

    ![Hello Page Screenshot](https://github.com/yagmurbarank/person-search-app/blob/main/img/3.PNG)


## Technologies Used

*   **Java:** Programming language
*   **Spring Boot:** Framework for building Java applications
*   **Vaadin:** UI framework for creating web applications
*   **Spring Data JPA:** Data access layer for interacting with the database
*   **Maven:** Build tool


## Prerequisites

*   **Java Development Kit (JDK):** Version 17 or higher (as required by Spring Boot and Vaadin).  Download from [https://www.oracle.com/java/technologies/javase-jdk17-downloads.html](https://www.oracle.com/java/technologies/javase-jdk17-downloads.html) or use an open-source distribution like [https://adoptium.net/](https://adoptium.net/).
*   **Maven:** Build automation tool.  Download from [https://maven.apache.org/download.cgi](https://maven.apache.org/download.cgi).

## Setup and Installation

1.  **Clone the Repository:**

    ```
    git clone https://github.com/yagmurbarank/person-search-app
    cd [your repository directory]
    ```

2.  **Configure the Database:**

  *   Update the `application.properties` or `application.yml` file in the `src/main/resources` directory with your database connection details.
  *   Example (`application.properties` for H2 database):

      ```
      spring.datasource.url=jdbc:h2:file:./data/staffdb
      spring.datasource.username=sa
      spring.datasource.password=
      spring.jpa.hibernate.ddl-auto=update  # Automatically update schema
      spring.h2.console.enabled=true #Enable the H2 Console
      spring.h2.console.path=/h2-console #Set the path
      ```

  *   If using H2, you can access the H2 console at `http://localhost:8081/h2-console` (assuming you have the line added from above and that the application runs on port 8081). You'll need to set the JDBC URL to `jdbc:h2:file:./data/staffdb`.
3.  **Build the Application:**

    ```
    mvn clean install
    ```

4.  **Run the Application:**

    ```
    mvn spring-boot:run
    ```

    Alternatively, you can run the JAR file created in the `target` directory:

    ```
    java -jar target/[your-application-name].jar
    ```

5.  **Access the Application:**

    Open your web browser and navigate to:

  *   **Home Page:** `http://localhost:8081/`
  *   **Hello World Page:** `http://localhost:8081/hello-world`
  *   **About Page:** `http://localhost:8081/about`
  *   **Staff Page:** `http://localhost:8081/staff`

## Usage

*   **Staff Page:**  On the Staff Page, you'll see a list of staff members.  Use the search bar to filter the list by name.


## Project structure

- `MainLayout.java` in `src/main/java` contains the navigation setup (i.e., the
  side/top bar and the main menu). This setup uses
  [App Layout](https://vaadin.com/docs/components/app-layout).
- `views` package in `src/main/java` contains the server-side Java views of your application.
- `views` folder in `src/main/frontend` contains the client-side JavaScript views of your application.
- `themes` folder in `src/main/frontend` contains the custom CSS styles.

## Useful links

- Read the documentation at [vaadin.com/docs](https://vaadin.com/docs).
- Follow the tutorial at [vaadin.com/docs/latest/tutorial/overview](https://vaadin.com/docs/latest/tutorial/overview).
- Create new projects at [start.vaadin.com](https://start.vaadin.com/).
- Search UI components and their usage examples at [vaadin.com/docs/latest/components](https://vaadin.com/docs/latest/components).
- View use case applications that demonstrate Vaadin capabilities at [vaadin.com/examples-and-demos](https://vaadin.com/examples-and-demos).
- Build any UI without custom CSS by discovering Vaadin's set of [CSS utility classes](https://vaadin.com/docs/styling/lumo/utility-classes). 
- Find a collection of solutions to common use cases at [cookbook.vaadin.com](https://cookbook.vaadin.com/).
- Find add-ons at [vaadin.com/directory](https://vaadin.com/directory).
- Ask questions on [Stack Overflow](https://stackoverflow.com/questions/tagged/vaadin) or join our [Forum](https://vaadin.com/forum).
- Report issues, create pull requests in [GitHub](https://github.com/vaadin).
