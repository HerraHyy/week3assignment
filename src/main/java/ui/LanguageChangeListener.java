package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LanguageChangeListener implements ActionListener {

    private EmployeeManagementApp app;
    private JComboBox<String> languageComboBox;

    public LanguageChangeListener(EmployeeManagementApp app, JComboBox<String> languageComboBox) {
        this.app = app;
        this.languageComboBox = languageComboBox;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String selectedLanguage = (String) languageComboBox.getSelectedItem();
        app.updateLanguage(selectedLanguage);
    }
}
