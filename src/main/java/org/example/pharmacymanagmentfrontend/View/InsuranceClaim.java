
// InsuranceClaim.java
package org.example.pharmacymanagmentfrontend.View;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import org.example.pharmacymanagmentfrontend.Controller.PharmacyPersonnelController;
import org.example.pharmacymanagmentfrontend.Model.Prescription;

import java.time.LocalDate;
import java.time.ZoneId;

public class InsuranceClaim extends Stage {
    // Declare UI components (buttons, fields, labels, etc.)
    private static Button submitButton;
    private static Button clearButton;
    private static TextField insuranceNumberField;
    private static TextField claimIdField;
    private static TextField claimAmountField;
    private static TextArea claimDescriptionArea;
    private static TextField nameField;
    private static DatePicker prescriptionDatePicker;
    private static DatePicker dobPicker;
    private static Label patientInfoLabel;
    private static Label nameLabel;
    private static Label dobLabel;
    private static Label insuranceNumberLabel;
    private static Label prescriptionDetailsLabel;
    private static Label claimDetailsLabel;
    private static Label claimIdLabel;
    private static Label claimDateLabel;
    private static Label claimAmountLabel;
    private static Label claimDescriptionLabel;
    private static Label prescriptionDateLabel;
    private static DatePicker claimDatePicker;

    // Create the main view of the Insurance Claim form as a ScrollPane
    public static ScrollPane createInsuranceClaimView(Prescription prescription) {
        Stage stage = new Stage();  // Create a new stage for the view
        stage.setTitle("Insurance Claim Submission"); // Set the window title

        // Root layout using GridPane for organizing form components
        GridPane root = createRootLayout();

        // Call method to add preset details to the fields based on the prescription
        addDetailsToFields(prescription);

        // Add sections to the root layout
        addPatientInfoSection(root);
        addPrescriptionDetailsSection(root);
        addClaimDetailsSection(root);
        addActionButtons(root);

        // Wrap the root layout inside a ScrollPane for better usability on small screens
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(root);
        scrollPane.setFitToWidth(true); // Ensure it fits the width of the screen
        scrollPane.setStyle("fx-background-color: #f8f8f8;");

        // Call a method to reset any timers or session data
        PharmacyPersonnelController.resetTimeUp(scrollPane);

        // Return the ScrollPane which contains the full UI
        return scrollPane;
    }

    // Create the root layout
    private static GridPane createRootLayout() {
        GridPane root = new GridPane();
        root.setPadding(new Insets(20)); // Add padding around the GridPane
        root.setHgap(15); // Set horizontal gap between columns
        root.setVgap(20); // Set vertical gap between rows
        root.setStyle("-fx-background-color: #f8f8f8;");
        return root;
    }

    // Add patient information section to the root layout
    private static void addPatientInfoSection(GridPane root) {
        root.add(patientInfoLabel, 0, 0, 2, 1);
        root.add(nameLabel, 0, 1);
        root.add(nameField, 1, 1);
        root.add(dobLabel, 0, 2);
        root.add(dobPicker, 1, 2);
        root.add(insuranceNumberLabel, 0, 3);
        root.add(insuranceNumberField, 1, 3);
    }

    // Add prescription details section to the root layout
    private static void addPrescriptionDetailsSection(GridPane root) {
        root.add(prescriptionDetailsLabel, 0, 5, 2, 1);
        root.add(prescriptionDateLabel, 0, 7);
        root.add(prescriptionDatePicker, 1, 7);
    }

    // Add claim details section to the root layout
    private static void addClaimDetailsSection(GridPane root) {
        root.add(claimDetailsLabel, 0, 9, 2, 1);
        root.add(claimIdLabel, 0, 10);
        root.add(claimIdField, 1, 10);
        root.add(claimDateLabel, 0, 11);
        root.add(claimDatePicker, 1, 11);
        root.add(claimAmountLabel, 0, 12);
        root.add(claimAmountField, 1, 12);
        root.add(claimDescriptionLabel, 0, 13);
        root.add(claimDescriptionArea, 1, 13);
    }

    // Add action buttons to the root layout
    private static void addActionButtons(GridPane root) {
        submitButton = ManagementDashboard.createStyledButton("Submit", "#2ecc71", "#27ae60");
        clearButton = ManagementDashboard.createStyledButton("Clear", "#2ecc71", "#27ae60");

        HBox buttonBox = new HBox(15, submitButton, clearButton);
        buttonBox.setAlignment(Pos.CENTER_RIGHT); // Align buttons to the right
        root.add(buttonBox, 0, 14, 2, 1); // Add buttons to the layout

        // Set button actions
        addActionToButtons();
    }

    // Add actions to the Submit and Clear buttons
    private static void addActionToButtons() {
        // Action for Submit button
        submitButton.setOnAction(event -> handleSubmitAction());

        // Action for Clear button
        clearButton.setOnAction(event -> handleClearAction());
    }

    // Handle the submit button action
    private static void handleSubmitAction() {
        if (validateFields()) {
            claimIdField.setText("CLM-" + Math.random() * 10000);
            showAlert(Alert.AlertType.INFORMATION, "Submission Successful", "Claim submitted successfully!");
        } else {
            showAlert(Alert.AlertType.ERROR, "Validation Error", "All fields are required!");
        }
    }

    // Handle the clear button action
    private static void handleClearAction() {
        nameField.clear();
        dobPicker.setValue(null);
        insuranceNumberField.clear();
        prescriptionDatePicker.setValue(null);
        claimAmountField.clear();
        claimDescriptionArea.clear();
        claimIdField.setText("Auto-generated");
    }

    // Validate the input fields
    private static boolean validateFields() {
        return !nameField.getText().isEmpty() && dobPicker.getValue() != null &&
                !insuranceNumberField.getText().isEmpty() && prescriptionDatePicker.getValue() != null &&
                !claimAmountField.getText().isEmpty();
    }

    // Method to show an alert (validation or success messages)
    private static void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);  // No header text for alert
        alert.setContentText(message);
        alert.showAndWait(); // Show the alert and wait for user response
    }

    // Method to populate form fields with prescription data (if available)
    private static void addDetailsToFields(Prescription prescription) {
        patientInfoLabel = new Label("Patient Information:");
        patientInfoLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        nameLabel = new Label("Name:");
        nameField = new TextField();
        nameField.setText(prescription.getPatient().getName()); // Pre-fill name from prescription

        dobLabel = new Label("Date of Birth:");
        dobPicker = new DatePicker();
        checkDateValue(prescription); // Set the date of birth if available

        insuranceNumberLabel = new Label("Insurance Number:");
        insuranceNumberField = new TextField();
        insuranceNumberField.setPromptText("Enter insurance number");

        prescriptionDetailsLabel = new Label("Prescription Details:");
        prescriptionDetailsLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        prescriptionDateLabel = new Label("Date of Prescription:");
        prescriptionDatePicker = new DatePicker(LocalDate.now()); // Default to today's date

        claimDetailsLabel = new Label("Claim Details:");
        claimDetailsLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        claimIdLabel = new Label("Claim ID:");
        claimIdField = new TextField("Auto-generated");
        claimIdField.setEditable(false); // Disable editing of claim ID

        claimDateLabel = new Label("Date of Claim:");
        claimDatePicker = new DatePicker(LocalDate.now()); // Default to today's date

        claimAmountLabel = new Label("Claim Amount:");
        claimAmountField = new TextField(String.valueOf(prescription.getTotalPrice())); // Pre-fill with prescription total price
        claimAmountField.setPromptText("Enter claim amount");

        claimDescriptionLabel = new Label("Description:");
        claimDescriptionArea = new TextArea();
        claimDescriptionArea.setPromptText("Enter claim description");
        claimDescriptionArea.setPrefHeight(100); // Set height of description area
    }

    // Method to check and set the patient's birthdate in the date picker
    private static void checkDateValue(Prescription prescription) {
        if (prescription.getPatient().getBirthDate() == null) {
            dobPicker.setValue(LocalDate.now());
        } else {
            dobPicker.setValue(prescription.getPatient().getBirthDate().toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate());
        }
    }
}