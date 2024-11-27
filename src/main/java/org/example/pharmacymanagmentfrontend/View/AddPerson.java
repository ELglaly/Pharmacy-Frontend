package org.example.pharmacymanagmentfrontend.View;

import javax.swing.*;

import javafx.collections.FXCollections;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.util.Date;

import static javafx.application.Application.launch;

public class AddPerson extends JFrame {
    private static TextField nameField;
    private static TextField usernameField;
    private static TextField passwordField;
    private static TextField emailField;
    private static TextField phoneField;
    private static TextField licenseField;
    private static TextField addressField;
    private static ComboBox<String> userTypeCombo;
    private static SpinnerDateModel birthDateChooser;
    private static Button submitButton;

    public static VBox createAddPersonView() {
        // Create the root VBox for the form
        VBox root = new VBox(20); // 20px spacing
        root.setPadding(new Insets(20));
        root.setStyle("-fx-background-color: #ffffff; -fx-border-color: #dcdcdc; -fx-border-width: 1px; -fx-border-radius: 8px;");

        // Header Section
        Label headerText = new Label("Add Person");
        headerText.setFont(Font.font("Arial", 28));
        headerText.setStyle("-fx-font-weight: bold; -fx-text-fill: #2c3e50;");
        headerText.setUnderline(true); // Adds an underline to the header
        HBox headerTextContainer = new HBox(headerText);
        headerTextContainer.setAlignment(Pos.CENTER); // Center the button inside the HBox

        root.getChildren().add(headerTextContainer);

        // Create the form section

        // Add form fields
        root.getChildren().add(createForm());

        // Add Submit Button
        submitButton = ManagementDashboard.createStyledButton("Add Person", "#2ecc71", "#27ae60");
        submitButton.setOnAction(event -> handleSubmit());

        HBox buttonContainer = new HBox(submitButton);
        buttonContainer.setAlignment(Pos.CENTER); // Center the button inside the HBox

        root.getChildren().add(buttonContainer);

        return root;
    }

    private static GridPane createForm() {
        GridPane formGrid = new GridPane();
        formGrid.setHgap(10); // Horizontal gap between columns
        formGrid.setVgap(15); // Vertical gap between rows
        formGrid.setStyle("-fx-padding: 20px;");
        formGrid.setAlignment(Pos.CENTER_LEFT);

        // Add labeled fields to the grid
        addLabeledFieldToGrid(formGrid, "Name:", new TextField(), 0);
        addLabeledFieldToGrid(formGrid, "Username:", new TextField(), 1);
        addLabeledFieldToGrid(formGrid, "Email:", new TextField(), 2);
        addLabeledFieldToGrid(formGrid, "Phone:", new TextField(), 3);
        addLabeledFieldToGrid(formGrid, "License Number:", new TextField(), 4);
        addLabeledFieldToGrid(formGrid, "Address:", new TextField(), 5);
        addLabeledFieldToGrid(formGrid, "Birth Date:", new DatePicker(), 6);
        addLabeledFieldToGrid(formGrid, "User Type:",
                new ComboBox<>(FXCollections.observableArrayList(
                        "PharmacyManager", "Pharmacist", "PharmacyTechnician", "Cashier", "Patient")),
                7);

        return formGrid;
    }

    private static void addLabeledFieldToGrid(GridPane grid, String labelText, Control inputField, int rowIndex) {
        Label label = new Label(labelText);
        label.setStyle("-fx-font-weight: bold; -fx-text-fill: #2c3e50;"); // Styling the label

        // Align label and input fields
        GridPane.setHalignment(label, HPos.LEFT); // Right align the label
        grid.add(label, 0, rowIndex); // Add label to the first column
        grid.add(inputField, 1, rowIndex); // Add input field to the second column
    }


    private static HBox createLabeledField(String labelText, Control field) {
        Label label = new Label(labelText);
        label.setFont(Font.font("Arial", 16));
        label.setStyle("-fx-font-weight: bold; -fx-text-fill: #2c3e50;");


        HBox fieldBox = new HBox(10); // 10px spacing
        fieldBox.setAlignment(Pos.CENTER_LEFT);
        fieldBox.getChildren().addAll(label, field);

        return fieldBox;
    }

    private static void handleSubmit() {
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
            String userType = (String) userTypeCombo.getSelectionModel().getSelectedItem();

            // Create the new person (you can use these values to create a new instance of your class)
            System.out.println("Adding new person: " + name + ", " + username + ", " + email + ", " + userType);

            // Here you can further process and save the user data as needed
            // For example, create a new Person object or save to a database
        }
    }

    private static boolean validateFields() {
        // Check if any of the fields are empty
        TextField[] fields = {nameField, usernameField, passwordField, emailField, phoneField, licenseField, addressField};
        for (TextField field : fields) {
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
