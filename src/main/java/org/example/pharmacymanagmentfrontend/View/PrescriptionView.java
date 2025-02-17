// PrescriptionView.java
package org.example.pharmacymanagmentfrontend.View;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.pharmacymanagmentfrontend.Model.*;

import java.util.HashMap;

import static org.example.pharmacymanagmentfrontend.View.SharedView.alterMessage;

public class PrescriptionView {

    private static final HashMap<String, String> patientData = new HashMap<>();

    // Create the main view for prescription
    public static VBox createPrescriptionView() {
        VBox root = new VBox(20);
        root.getChildren().addAll(createHeader(), createContent());
        return root;
    }

    // Create the header section
    private static Label createHeader() {
        Label header = new Label("Pharmacy Management System");
        header.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: darkgreen;");
        header.setAlignment(Pos.CENTER);
        header.setPadding(new Insets(20));
        return header;
    }

    // Create the content section
    private static HBox createContent() {
        HBox content = new HBox(20, createLeftPane(), createRightPane());
        content.setPadding(new Insets(20));
        return content;
    }

    // Create the left pane for prescription form
    private static VBox createLeftPane() {
        VBox leftPane = new VBox(10);
        leftPane.setPadding(new Insets(20));
        leftPane.setStyle("-fx-border-color: lightgray; -fx-border-width: 2; -fx-border-radius: 10; -fx-background-color: #f8f8f8; -fx-background-radius: 10;");
        leftPane.getChildren().addAll(createFormTitle(), createFormFields(), createSaveButton());
        return leftPane;
    }

    // Create the form title
    private static Label createFormTitle() {
        Label formTitle = new Label("Enter Prescription Details");
        formTitle.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        return formTitle;
    }

    // Create the form fields
    private static VBox createFormFields() {
        VBox formFields = new VBox(10);
        formFields.getChildren().addAll(createTextField("Enter Username"), createTextField("Enter Prescription Name"),
                createTextField("Enter Dosage"), createTextField("Enter Duration"), createDetailsArea());
        return formFields;
    }

    // Create the details area
    private static TextArea createDetailsArea() {
        TextArea detailsArea = new TextArea();
        detailsArea.setPromptText("Enter Other Details");
        detailsArea.setWrapText(true);
        return detailsArea;
    }

    // Create the save button
    private static Button createSaveButton() {
        Button saveButton = new Button("✓ Save");
        saveButton.setStyle("-fx-padding: 10px; -fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-weight: bold; -fx-border-radius: 5; -fx-background-radius: 5;");
        saveButton.setOnMouseEntered(e -> saveButton.setStyle("-fx-padding: 10px; -fx-background-color: #45a049; -fx-text-fill: white; -fx-font-weight: bold; -fx-border-radius: 5; -fx-background-radius: 5;"));
        saveButton.setOnMouseExited(e -> saveButton.setStyle("-fx-padding: 10px; -fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-weight: bold; -fx-border-radius: 5; -fx-background-radius: 5;"));
        saveButton.setOnAction(event -> savePrescription());
        return saveButton;
    }

    // Save the prescription details
    private static void savePrescription() {
        // Implementation of saving prescription details
    }

    // Create the right pane for inventory list
    private static VBox createRightPane() {
        VBox rightPane = new VBox(10);
        rightPane.setPadding(new Insets(20));
        rightPane.setStyle("-fx-border-color: lightgray; -fx-border-width: 2; -fx-border-radius: 10; -fx-background-color: #f8f8f8; -fx-background-radius: 10;");
        rightPane.getChildren().addAll(createInventoryTitle(), createInventoryList());
        return rightPane;
    }

    // Create the inventory title
    private static Label createInventoryTitle() {
        Label inventoryTitle = new Label("Available Drugs");
        inventoryTitle.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        return inventoryTitle;
    }

    // Create the inventory list
    private static ScrollPane createInventoryList() {
        ListView<String> drugList = new ListView<>();
        for (Inventory medicine : UserGenerator.inventoryStock)
            drugList.getItems().addAll(medicine.getName());
        drugList.setPrefHeight(380);
        drugList.setOnMouseClicked(event -> handleDrugSelection(drugList));
        ScrollPane scrollPane = new ScrollPane(drugList);
        scrollPane.setFitToWidth(true);
        return scrollPane;
    }

    // Handle drug selection event
    private static void handleDrugSelection(ListView<String> drugList) {
        String selectedDrug = drugList.getSelectionModel().getSelectedItem();
        if (selectedDrug != null) {
            Inventory inventoryTest = UserGenerator.findMedicine(selectedDrug);
            if (inventoryTest != null && inventoryTest.getLowStock()) {
                Stage alert = alterMessage("Attention: Low Stock Items Detected \nThis item has low only " + inventoryTest.getQuantity() + ". Please restock them soon.",
                        "Low Stock Warning", "OK", null);
                alert.show();
            }
        }
    }

    // Create a text field with a prompt text
    private static TextField createTextField(String promptText) {
        TextField textField = new TextField();
        textField.setPromptText(promptText);
        textField.setStyle("-fx-padding: 8px; -fx-border-color: lightgray; -fx-border-radius: 5;");
        return textField;
    }

    // Show an alert message
    static void showAlert(String title, String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}