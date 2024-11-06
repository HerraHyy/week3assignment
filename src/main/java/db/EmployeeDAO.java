package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeDAO {

    public void saveEmployee(Employee employee, String language) throws SQLException {
        String employeeQuery = "INSERT INTO employee (email) VALUES (?) ON DUPLICATE KEY UPDATE email = ?";
        String translationQuery = "INSERT INTO employee_translation (employee_id, first_name, last_name, language_code) " +
                "VALUES (?, ?, ?, ?) " +
                "ON DUPLICATE KEY UPDATE first_name = VALUES(first_name), last_name = VALUES(last_name)";

        try (Connection conn = DatabaseConnection.getConnection()) {
            conn.setAutoCommit(false); // Start transaction
            try (PreparedStatement employeeStmt = conn.prepareStatement(employeeQuery, PreparedStatement.RETURN_GENERATED_KEYS)) {
                employeeStmt.setString(1, employee.getEmail());
                employeeStmt.setString(2, employee.getEmail());
                employeeStmt.executeUpdate();

                // Get the employee ID for the translation
                int employeeId;
                ResultSet generatedKeys = employeeStmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    employeeId = generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Failed to retrieve employee ID.");
                }

                try (PreparedStatement translationStmt = conn.prepareStatement(translationQuery)) {
                    translationStmt.setInt(1, employeeId);
                    translationStmt.setString(2, employee.getFirstName() != null ? employee.getFirstName() : "default_value");
                    translationStmt.setString(3, employee.getLastName() != null ? employee.getLastName() : "default_value");
                    translationStmt.setString(4, language);
                    translationStmt.executeUpdate();
                }

                conn.commit(); // Commit transaction
            } catch (SQLException e) {
                conn.rollback(); // Rollback if there's an error
                System.err.println("Error saving employee: " + e.getMessage());
                throw e;
            }
        } catch (SQLException e) {
            System.err.println("Database connection error: " + e.getMessage());
            throw e;
        }
    }
}