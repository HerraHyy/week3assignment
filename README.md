# Employee Management Application

## Overview

This application is a simple Employee Management System built using Java and Swing for the user interface. It allows users to add employees with their first name, last name, and email, and save this information to a MySQL database. The application supports multiple languages for the UI.

## Features

- Add new employees with first name, last name, and email.
- Save employee information to a MySQL database.
- Support for multiple languages in the UI.

## Prerequisites

- Java Development Kit (JDK) 8 or higher
- Apache Maven
- MySQL database

## Database Setup

1. Create a MySQL database named `employee_db`.
2. Create the `employee` and `employee_translation` tables:

```sql
CREATE TABLE employee (
    id INT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(255) NOT NULL
);

CREATE TABLE employee_translation (
    employee_id INT,
    first_name VARCHAR(255) DEFAULT 'default_value',
    last_name VARCHAR(255) DEFAULT 'default_value',
    language_code VARCHAR(10),
    PRIMARY KEY (employee_id, language_code),
    FOREIGN KEY (employee_id) REFERENCES employee(id)
);
```
## Configuration

Update the database connection details in `src/main/java/db/DatabaseConnection.java`:

```java
package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/employee_db";
    private static final String USER = "user1";
    private static final String PASSWORD = "1234";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
```

## Build and Run

1. Clone the repository.
2. Navigate to project directory.
3. Build the project using Maven:
````java
mvn clean install
````
4. Run the application:
````java
java -jar target/employee-management-app.jar
````
## Usage
1. Launch the app.
2. Select desired language from the dropdown menu.
3. Enter employee details (first name, last name, email).
4. Click the "Save" button to save the employee information to the database.

## Screenshots
![Application Screenshot](src/main/resources/IMG/Image1.png)

## Troubleshooting
- If you encounter any issues, please check the database connection details in `DatabaseConnection.java`.
- Make sure the MySQL database is running and accessible.
- Check the console for an error messages and stack traces.

## License

This project is licensed under the MIT License.