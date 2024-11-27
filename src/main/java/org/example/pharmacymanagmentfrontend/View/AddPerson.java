package org.example.pharmacymanagmentfrontend.View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.JTextComponent;
import com.toedter.calendar.JDateChooser;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class AddPerson extends JFrame {
    private JTextField nameField, usernameField, passwordField, emailField, phoneField, licenseField, addressField;
    private JDateChooser birthDateChooser;
    private JComboBox<String> userTypeCombo;
    private JButton submitButton;

    public AddPerson() {
        // Create panel for the form content
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(11, 2, 10, 10)); // 10 rows, 2 columns
        formPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        add(formPanel, BorderLayout.CENTER);

        JLabel headerText = new JLabel("User Login Logs");
        headerText.setFont(new Font("Arial", Font.BOLD, 20));
        formPanel.add(headerText, BorderLayout.CENTER);

        headerText.setFont(new Font("Arial", Font.BOLD, 20));
        formPanel.add(new JLabel(""));
        // Add the Lables anf textfile
        Addformfields(formPanel);
        // Submit Button
        submitButton = new JButton("Add Person");
        submitButton.setBackground(Color.black);
        submitButton.setFont(new Font("Arial", Font.BOLD, 14));
        submitButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        submitButton.setFocusPainted(false);
        submitButton.setForeground(Color.WHITE);
        add(submitButton, BorderLayout.SOUTH);
        // Button Action: On Submit
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleSubmit();
            }
        }); // Set frame visibility
        setVisible(true);
    }

    private void Addformfields(JPanel formPanel) {
        // Labels and Fields for form entries
        formPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        formPanel.add(nameField);

        formPanel.add(new JLabel("Username:"));
        usernameField = new JTextField();
        formPanel.add(usernameField);

        formPanel.add(new JLabel("Email:"));
        emailField = new JTextField();
        formPanel.add(emailField);

        formPanel.add(new JLabel("Phone:"));
        phoneField = new JTextField();
        formPanel.add(phoneField);

        formPanel.add(new JLabel("License Number:"));
        licenseField = new JTextField();
        formPanel.add(licenseField);

        formPanel.add(new JLabel("Address:"));
        addressField = new JTextField();
        formPanel.add(addressField);

        formPanel.add(new JLabel("Birth Date:"));
        birthDateChooser = new JDateChooser();
        birthDateChooser.setDateFormatString("yyyy-MM-dd");
        formPanel.add(birthDateChooser);

        formPanel.add(new JLabel("User Type:"));
        userTypeCombo = new JComboBox<>(new String[]{"PharmacyManager", "Pharmacist", "PharmacyTechnician", "Cashier", "Patient"});
        formPanel.add(userTypeCombo);
    }

    private void handleSubmit() {
        // Validate the input fields
        if (validateFields()) {
            // Get values from the form
            String name = nameField.getText().trim();
            String username = usernameField.getText().trim();
            String password = passwordField.getText().trim();
            String email = emailField.getText().trim();
            String phone = phoneField.getText().trim();
            String licenseNumber = licenseField.getText().trim();
            String address = addressField.getText().trim();
            Date birthDate = birthDateChooser.getDate();
            String userType = (String) userTypeCombo.getSelectedItem();

            // Create the new person (you can use these values to create a new instance of your class)
            System.out.println("Adding new person: " + name + ", " + username + ", " + email + ", " + userType);

            // Here you can further process and save the user data as needed
            // For example, create a new Person object or save to a database
        } else {
            JOptionPane.showMessageDialog(this, "Please fill in all fields correctly.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean validateFields() {
        // Check if any of the fields are empty
        JTextComponent[] fields = {nameField, usernameField, passwordField, emailField, phoneField, licenseField, addressField};
        for (JTextComponent field : fields) {
            if (field.getText().trim().isEmpty()) {
                return false;
            }
        }

        // Check if the birth date is valid
        if (birthDateChooser.getDate() == null) {
            return false;
        }

        return true;
    }
}
