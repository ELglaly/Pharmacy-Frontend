package org.example.pharmacymanagmentfrontend.View;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.pharmacymanagmentfrontend.Controller.PatientController;
import org.example.pharmacymanagmentfrontend.Model.Inventory;
import org.example.pharmacymanagmentfrontend.Model.Patient;
import org.example.pharmacymanagmentfrontend.Model.Prescription;
import org.example.pharmacymanagmentfrontend.Model.UserGenerator;
import static org.example.pharmacymanagmentfrontend.HelloApplication.primaryScene;

import java.util.HashMap;

import static javafx.application.Application.launch;
import static org.example.pharmacymanagmentfrontend.View.SharedView.alterMessage;

public class PrescriptionView {

    private static final HashMap<String, String> patientData = new HashMap<>();

    public static VBox createPrescriptionView() {
        // Header
        Label header = new Label("Pharmacy Management System");
        header.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: darkgreen;");
        header.setAlignment(Pos.CENTER);
        header.setPadding(new Insets(20));

        // Left Pane: Prescription Form
        VBox leftPane = new VBox(10);
        leftPane.setPadding(new Insets(20));
        leftPane.setStyle("-fx-border-color: lightgray; -fx-border-width: 2; -fx-border-radius: 10; -fx-background-color: #f8f8f8; -fx-background-radius: 10;");

        Label formTitle = new Label("Enter Prescription Details");
        formTitle.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        TextField usernameField = createTextField("Enter Username");
        TextField nameField = createTextField("Enter Prescription Name");
        TextField dosageField = createTextField("Enter Dosage");
        TextField durationField = createTextField("Enter Duration");
        TextArea detailsArea = new TextArea();
        detailsArea.setPromptText("Enter Other Details");
        detailsArea.setWrapText(true);

        Button saveButton = createStyledButton("Save", "✓");

        leftPane.getChildren().addAll(formTitle, usernameField, nameField, dosageField, durationField, detailsArea, saveButton);

        // Right Pane: Inventory List
        VBox rightPane = new VBox(10);
        rightPane.setPadding(new Insets(20));
        rightPane.setStyle("-fx-border-color: lightgray; -fx-border-width: 2; -fx-border-radius: 10; -fx-background-color: #f8f8f8; -fx-background-radius: 10;");

        Label inventoryTitle = new Label("Available Drugs");
        inventoryTitle.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        ListView<String> drugList = new ListView<>();
        for(Inventory medicine: UserGenerator.inventoryStock)
        drugList.getItems().addAll(
                medicine.getName()
        );
        drugList.setPrefHeight(380);

        ScrollPane scrollPane = new ScrollPane(drugList);
        scrollPane.setFitToWidth(true);

        rightPane.getChildren().addAll(inventoryTitle, scrollPane);

        // Drug selection event
        drugList.setOnMouseClicked(event -> {
            String selectedDrug = drugList.getSelectionModel().getSelectedItem();
            if (selectedDrug != null) {
                Inventory inventorytTest=UserGenerator.findMedicine(selectedDrug);
                if(inventorytTest.getLowStock())
                {
                        Stage alert = alterMessage("attention: Low Stock Items Detected \nThis item has low only "+inventorytTest.getQuantity()+". Please restock them soon.",
                                "Low Stock Warning",
                                "OK",null);
                        alert.show();
                    }
                }
                nameField.setText( nameField.getText() +" - "+ selectedDrug);
        });

        // Save Button Functionality
        saveButton.setOnAction(event -> {
            String username = usernameField.getText().trim();
            if (username.isEmpty()) {
                showAlert("Error", "Username is required!", Alert.AlertType.ERROR);
                return;
            }

            String prescriptionName = nameField.getText();
            String dosage = dosageField.getText();
            String duration = durationField.getText();
            String details = detailsArea.getText();
            Patient patient=(Patient) UserGenerator.checkUsername(username);
            if(patient==null)
            {
                Stage alert = alterMessage("There is no patient with this Username",
                        "Incorrect Data",
                        "OK",null);
                alert.show();
            }
            else {
                Prescription prescription =new Prescription(patient,prescriptionName, dosage,duration,details);
                patient.addPrescriptions(prescription);
                showAlert("Success", "Prescription saved successfully!", Alert.AlertType.INFORMATION);
                // Clear the per
                usernameField.clear();
                nameField.clear();
                dosageField.clear();
                durationField.clear();
                detailsArea.clear();
                PatientController.CheckoutView(prescription);
            }
        });

        // View Data Button Functionality

        HBox content = new HBox(20, leftPane, rightPane);
        content.setPadding(new Insets(20));

        VBox root = new VBox(header, content);

        return root;
    }

    private static TextField createTextField(String promptText) {
        TextField textField = new TextField();
        textField.setPromptText(promptText);
        textField.setStyle("-fx-padding: 8px; -fx-border-color: lightgray; -fx-border-radius: 5;");
        return textField;
    }

    private static Button createStyledButton(String text, String icon) {
        Button button = new Button(icon + " " + text);
        button.setStyle("-fx-padding: 10px; -fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-weight: bold; -fx-border-radius: 5; -fx-background-radius: 5;");
        button.setOnMouseEntered(e -> button.setStyle("-fx-padding: 10px; -fx-background-color: #45a049; -fx-text-fill: white; -fx-font-weight: bold; -fx-border-radius: 5; -fx-background-radius: 5;"));
        button.setOnMouseExited(e -> button.setStyle("-fx-padding: 10px; -fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-weight: bold; -fx-border-radius: 5; -fx-background-radius: 5;"));
        return button;
    }

    static void showAlert(String title, String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
