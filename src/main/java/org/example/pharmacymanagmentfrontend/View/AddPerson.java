// AddPerson.java
package org.example.pharmacymanagmentfrontend.View;

import javafx.collections.FXCollections;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import org.example.pharmacymanagmentfrontend.Controller.PharmacyPersonnelController;

import javax.swing.*;
import java.time.LocalDate;

import static org.example.pharmacymanagmentfrontend.View.UpdatePerson.userTypeCombo;

public class AddPerson {

    // Define input fields
    private static TextField nameField;
    private static TextField usernameField;
    private static PasswordField passwordField;
    private static TextField emailField;
    private static TextField phoneField;
    private static TextField licenseField;
    private static TextField addressField;
    private static DatePicker birthDatePicker;
    // Create the main view for adding a person
    public static VBox createAddPersonView() {
        // Root layout
        VBox root = new VBox(20);
        root.setPadding(new Insets(20));
        root.setStyle("-fx-background-color: #f8f8f8; -fx-border-color: #dcdcdc; -fx-border-width: 1px; -fx-border-radius: 8px;");

        // Header Section
        Label headerText = new Label("Add Person");
        headerText.setFont(Font.font(28));
        headerText.setStyle("-fx-font-weight: bold; -fx-text-fill: #2c3e50;");
        HBox headerTextContainer = new HBox(headerText);
        headerTextContainer.setAlignment(Pos.CENTER);
        root.getChildren().add(headerTextContainer);

        // Add Form
        root.getChildren().add(createForm());

        // Submit Button
        Button submitButton = ManagementDashboard.createStyledButton("Add Person", "#2ecc71", "#27ae60");
        submitButton.setOnAction(event -> {
            if (validateFields()) {
                showAlert(Alert.AlertType.INFORMATION, "Person added successfully.");
                handleSubmit();
            } else {
                showAlert(Alert.AlertType.ERROR, "Please fill in all required fields.");
            }
        });
        HBox buttonContainer = new HBox(submitButton);
        buttonContainer.setAlignment(Pos.CENTER);
        root.getChildren().add(buttonContainer);

        return root;
    }

    // Create the form with labeled fields
    private static GridPane createForm() {
        GridPane formGrid = new GridPane();
        formGrid.setHgap(10);
        formGrid.setVgap(15);
        formGrid.setAlignment(Pos.CENTER_LEFT);
        // Initialize form fields
        initializeFields();
        // Add labeled fields to the grid
        addFieldsToGrid(formGrid);
        PharmacyPersonnelController.resetTimeUp(formGrid); // Additional logic for timing
        return formGrid;
    }

    // Initialize form fields
    private static void initializeFields() {
        nameField = new TextField();
        usernameField = new TextField();
        passwordField = new PasswordField();
        emailField = new TextField();
        phoneField = new TextField();
        licenseField = new TextField();
        addressField = new TextField();
        birthDatePicker = new DatePicker();
        userTypeCombo = new ComboBox<>(FXCollections.observableArrayList(
                "PharmacyManager", "Pharmacist", "PharmacyTechnician", "Cashier", "Patient"
        ));
    }

    // Add labeled fields to the grid
    private static void addFieldsToGrid(GridPane grid) {
        addLabeledFieldToGrid(grid, "Name:", nameField, 0);
        addLabeledFieldToGrid(grid, "Username:", usernameField, 1);
        addLabeledFieldToGrid(grid, "Password:", passwordField, 2);
        addLabeledFieldToGrid(grid, "Email:", emailField, 3);
        addLabeledFieldToGrid(grid, "Phone:", phoneField, 4);
        addLabeledFieldToGrid(grid, "License Number:", licenseField, 5);
        addLabeledFieldToGrid(grid, "Address:", addressField, 6);
        addLabeledFieldToGrid(grid, "Birth Date:", birthDatePicker, 7);
        addLabeledFieldToGrid(grid, "User Type:", userTypeCombo, 8);
    }

    // Add a labeled field to the grid
    private static void addLabeledFieldToGrid(GridPane grid, String labelText, Control inputField, int rowIndex) {
        Label label = new Label(labelText);
        label.setStyle("-fx-font-weight: bold; -fx-text-fill: #2c3e50;");
        GridPane.setHalignment(label, HPos.LEFT);
        grid.add(label, 0, rowIndex);
        grid.add(inputField, 1, rowIndex);
    }

    // Handle the submit button action
    private static void handleSubmit() {
        if (validateFields()) {
            showAlert(Alert.AlertType.INFORMATION, "Person added successfully.");
            processFormData();
        } else {
            showAlert(Alert.AlertType.ERROR, "Please fill in all required fields.");
        }
    }

    // Validate the input fields
    private static boolean validateFields() {
        // Validate that all required fields are filled
        TextField[] fields = {nameField, usernameField, passwordField, emailField, phoneField, licenseField, addressField};
        for (TextField field : fields) {
            if (field == null || field.getText().trim().isEmpty()) {
                return false;
            }
        }
        // Validate date picker and combo box
        return birthDatePicker.getValue() != null && userTypeCombo.getSelectionModel().getSelectedItem() != null;
    }

    // Process the form data
    private static void processFormData() {
        // Get values from the form
        String name = nameField.getText().trim();
        String username = usernameField.getText().trim();
        String password = passwordField.getText().trim();
        String email = emailField.getText().trim();
        String phone = phoneField.getText().trim();
        String licenseNumber = licenseField.getText().trim();
        String address = addressField.getText().trim();
        LocalDate birthDate = birthDatePicker.getValue();
        String userType = userTypeCombo.getSelectionModel().getSelectedItem();

        // Process the form data (e.g., save to database)
        System.out.printf("User added: %s (%s)%n", name, userType);
    }

    // Show an alert with the specified message
    private static void showAlert(Alert.AlertType alertType, String message) {
        Alert alert = new Alert(alertType);
        alert.setContentText(message);
        alert.showAndWait();
    }
}