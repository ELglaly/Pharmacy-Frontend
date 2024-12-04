package org.example.pharmacymanagmentfrontend.View;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import org.example.pharmacymanagmentfrontend.Model.Prescription;

import java.time.LocalDate;
import java.time.ZoneId;

public class InsuranceClaim extends Stage {

    public static ScrollPane createInsuranceClaimView(Prescription prescription) {
        Stage stage = new Stage();
        stage.setTitle("Insurance Claim Submission");

        // Root layout
        GridPane root = new GridPane();
        root.setPadding(new Insets(20));
        root.setHgap(15); // Horizontal gap between columns
        root.setVgap(20); // Vertical gap between rows

        // Set background color
        root.setStyle("-fx-background-color: #f8f8f8;");

        // Section: Patient Information
        Label patientInfoLabel = new Label("Patient Information:");
        patientInfoLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        root.add(patientInfoLabel, 0, 0, 2, 1);

        Label nameLabel = new Label("Name:");
        TextField nameField = new TextField();
        nameField.setText(prescription.getPatient().getName());
        root.add(nameLabel, 0, 1);
        root.add(nameField, 1, 1);

        Label dobLabel = new Label("Date of Birth:");
        DatePicker dobPicker = new DatePicker();
        if(prescription.getPatient().getBirthDate()==null){
            dobPicker.setValue(LocalDate.now());
        }
        else {
            dobPicker.setValue((prescription.getPatient().getBirthDate().toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate()));
        }

        root.add(dobLabel, 0, 2);
        root.add(dobPicker, 1, 2);

        Label insuranceNumberLabel = new Label("Insurance Number:");
        TextField insuranceNumberField = new TextField();
        insuranceNumberField.setPromptText("Enter insurance number");
        root.add(insuranceNumberLabel, 0, 3);
        root.add(insuranceNumberField, 1, 3);

        // Section: Prescription Details
        Label prescriptionDetailsLabel = new Label("Prescription Details:");
        prescriptionDetailsLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        root.add(prescriptionDetailsLabel, 0, 5, 2, 1);


        Label prescriptionDateLabel = new Label("Date of Prescription:");
        DatePicker prescriptionDatePicker = new DatePicker(LocalDate.now());
        root.add(prescriptionDateLabel, 0, 7);
        root.add(prescriptionDatePicker, 1, 7);


        // Section: Claim Details
        Label claimDetailsLabel = new Label("Claim Details:");
        claimDetailsLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        root.add(claimDetailsLabel, 0, 9, 2, 1);

        Label claimIdLabel = new Label("Claim ID:");
        TextField claimIdField = new TextField("Auto-generated");
        claimIdField.setEditable(false);
        root.add(claimIdLabel, 0, 10);
        root.add(claimIdField, 1, 10);

        Label claimDateLabel = new Label("Date of Claim:");
        DatePicker claimDatePicker = new DatePicker(LocalDate.now());
        root.add(claimDateLabel, 0, 11);
        root.add(claimDatePicker, 1, 11);

        Label claimAmountLabel = new Label("Claim Amount:");
        TextField claimAmountField = new TextField(String.valueOf(prescription.getTotalPrice()));
        claimAmountField.setPromptText("Enter claim amount");
        root.add(claimAmountLabel, 0, 12);
        root.add(claimAmountField, 1, 12);

        Label claimDescriptionLabel = new Label("Description:");
        TextArea claimDescriptionArea = new TextArea(prescription.toString());
        claimDescriptionArea.setPromptText("Enter claim description");
        claimDescriptionArea.setPrefHeight(100);
        root.add(claimDescriptionLabel, 0, 13);
        root.add(claimDescriptionArea, 1, 13);

        // Action Buttons with colors
        Button submitButton = ManagementDashboard.createStyledButton("Submit", "#2ecc71", "#27ae60");

        Button clearButton = ManagementDashboard.createStyledButton("Clear", "#2ecc71", "#27ae60");


        HBox buttonBox = new HBox(15, submitButton, clearButton);
        buttonBox.setAlignment(Pos.CENTER_RIGHT);
        root.add(buttonBox, 0, 14, 2, 1);

        // Button Actions
        submitButton.setOnAction(event -> {
            if (nameField.getText().isEmpty() || dobPicker.getValue() == null ||
                    insuranceNumberField.getText().isEmpty() || prescriptionDatePicker.getValue() == null || claimAmountField.getText().isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "Validation Error", "All fields are required!");
            } else {
                claimIdField.setText("CLM-" + Math.random() * 10000);
                showAlert(Alert.AlertType.INFORMATION, "Submission Successful", "Claim submitted successfully!");
            }
        });

        clearButton.setOnAction(event -> {
            nameField.clear();
            dobPicker.setValue(null);
            insuranceNumberField.clear();
            prescriptionDatePicker.setValue(null);
            claimAmountField.clear();
            claimDescriptionArea.clear();
            claimIdField.setText("Auto-generated");
        });


        // Wrap in ScrollPane for scrollability
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(root);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("fx-background-color: #f8f8f8;");

        // Set up the scene and stage
        return scrollPane;
    }

    private static void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
