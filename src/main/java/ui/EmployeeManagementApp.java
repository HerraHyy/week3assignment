package ui;

import db.Employee;
import db.EmployeeDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class EmployeeManagementApp extends JFrame {

    private JLabel firstNameLabel;
    private JLabel lastNameLabel;
    private JLabel emailLabel;
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField emailField;
    private JButton saveButton;
    private JComboBox<String> languageComboBox;

    private EmployeeDAO employeeDAO;

    public EmployeeManagementApp() {
        employeeDAO = new EmployeeDAO();

        setTitle(Translations.getTranslation("English", "title"));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 250);
        setLayout(new GridLayout(6, 2, 5, 5));

        // Language selector
        languageComboBox = new JComboBox<>(Translations.getAvailableLanguages());
        languageComboBox.addActionListener(new LanguageChangeListener(this, languageComboBox));
        add(new JLabel("Select Language:"));
        add(languageComboBox);

        // First name label and field
        firstNameLabel = new JLabel(Translations.getTranslation("English", "first_name"));
        firstNameField = new JTextField();
        add(firstNameLabel);
        add(firstNameField);

        // Last name label and field
        lastNameLabel = new JLabel(Translations.getTranslation("English", "last_name"));
        lastNameField = new JTextField();
        add(lastNameLabel);
        add(lastNameField);

        // Email label and field
        emailLabel = new JLabel(Translations.getTranslation("English", "email"));
        emailField = new JTextField();
        add(emailLabel);
        add(emailField);

        // Save button
        saveButton = new JButton(Translations.getTranslation("English", "save"));
        saveButton.addActionListener(new SaveButtonListener());
        add(new JLabel()); // empty label for layout
        add(saveButton);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void updateLanguage(String selectedLanguage) {
        setTitle(Translations.getTranslation(selectedLanguage, "title"));
        firstNameLabel.setText(Translations.getTranslation(selectedLanguage, "first_name"));
        lastNameLabel.setText(Translations.getTranslation(selectedLanguage, "last_name"));
        emailLabel.setText(Translations.getTranslation(selectedLanguage, "email"));
        saveButton.setText(Translations.getTranslation(selectedLanguage, "save"));
    }

    private class SaveButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String email = emailField.getText();
                String firstName = firstNameField.getText();
                String lastName = lastNameField.getText();
                String language = (String) languageComboBox.getSelectedItem();

                // Validate required fields
                if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "All fields are required.");
                    return;
                }

                if (firstName == null || firstName.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "First name cannot be empty.");
                    return;
                }

                Employee employee = new Employee(0, firstName, lastName, email); // ID is 0 for new employees
                employeeDAO.saveEmployee(employee, language);
                JOptionPane.showMessageDialog(null, "Employee saved successfully!");
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error saving employee.");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(EmployeeManagementApp::new);
    }
}
