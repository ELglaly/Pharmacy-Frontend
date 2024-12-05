package org.example.pharmacymanagmentfrontend.View;

import javafx.geometry.Insets;
import javafx.geometry.Pos;

import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.example.pharmacymanagmentfrontend.Controller.PharmacyPersonnelController;

public class InsuranceInterface extends Stage {


    public static ScrollPane createInsuranceInterfaceView() {

        Stage stage = new Stage();
        stage.setTitle("Insurance Claim Management");

        GridPane root = new GridPane();
        root.setPadding(new Insets(15));
        root.setHgap(10);
        root.setVgap(15);

        root.setStyle("-fx-background-color: #f8f8f8;");
        // Style constants
        String sectionHeaderStyle = "-fx-font-size: 14px; -fx-font-weight: bold; -fx-text-fill: #004080;";
        String labelStyle = "-fx-font-size: 12px; -fx-text-fill: #333333;";
        Font textFieldFont = Font.font("Arial", 12);

        // Section: Search Claim
        Label searchLabel = new Label("Search Claim:");
        searchLabel.setStyle(sectionHeaderStyle);
        root.add(searchLabel, 0, 0, 2, 1);

        Label searchByIdLabel = new Label("Claim ID:");
        searchByIdLabel.setStyle(labelStyle);
        TextField searchByIdField = new TextField();
        searchByIdField.setFont(textFieldFont);
        searchByIdField.setPromptText("Enter Claim ID");
        root.add(searchByIdLabel, 0, 1);
        root.add(searchByIdField, 1, 1);

        Label searchByNameLabel = new Label("Patient Name:");
        searchByNameLabel.setStyle(labelStyle);
        TextField searchByNameField = new TextField();
        searchByNameField.setFont(textFieldFont);
        searchByNameField.setPromptText("Enter Patient Name");
        root.add(searchByNameLabel, 0, 2);
        root.add(searchByNameField, 1, 2);

        Button searchButton = new Button("Search");
        searchButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-weight: bold;");
        root.add(searchButton, 1, 3);

        // Edit or Correct Claim
        Label editLabel = new Label("Edit or Correct Claim:");
        editLabel.setStyle(sectionHeaderStyle);
        root.add(editLabel, 0, 4, 2, 1);

        Label claimIdLabel = new Label("Claim ID:");
        claimIdLabel.setStyle(labelStyle);
        TextField claimIdField = new TextField();
        claimIdField.setFont(textFieldFont);
        claimIdField.setPromptText("Claim ID");
        claimIdField.setEditable(false);
        root.add(claimIdLabel, 0, 5);
        root.add(claimIdField, 1, 5);

        Label patientNameLabel = new Label("Patient Name:");
        patientNameLabel.setStyle(labelStyle);
        TextField patientNameField = new TextField();
        patientNameField.setFont(textFieldFont);
        root.add(patientNameLabel, 0, 6);
        root.add(patientNameField, 1, 6);

        Label claimAmountLabel = new Label("Claim Amount:");
        claimAmountLabel.setStyle(labelStyle);
        TextField claimAmountField = new TextField();
        claimAmountField.setFont(textFieldFont);
        root.add(claimAmountLabel, 0, 7);
        root.add(claimAmountField, 1, 7);

        Label claimDescriptionLabel = new Label("Description:");
        claimDescriptionLabel.setStyle(labelStyle);
        TextArea claimDescriptionArea = new TextArea();
        claimDescriptionArea.setFont(textFieldFont);
        claimDescriptionArea.setPromptText("Enter description");
        claimDescriptionArea.setPrefHeight(70);
        root.add(claimDescriptionLabel, 0, 8);
        root.add(claimDescriptionArea, 1, 8);

        Button saveChangesButton = new Button("Save Changes");
        saveChangesButton.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white; -fx-font-weight: bold;");
        root.add(saveChangesButton, 1, 9);

        // Section: Track Claim Status
        Label trackLabel = new Label("Track Claim Status:");
        trackLabel.setStyle(sectionHeaderStyle);
        root.add(trackLabel, 0, 10, 2, 1);

        Label statusLabel = new Label("Status:");
        statusLabel.setStyle(labelStyle);
        ChoiceBox<String> statusBox = new ChoiceBox<>();
        statusBox.getItems().addAll("Pending", "Approved", "Rejected");
        root.add(statusLabel, 0, 11);
        root.add(statusBox, 1, 11);

        Label commentsLabel = new Label("Comments:");
        commentsLabel.setStyle(labelStyle);
        TextArea commentsArea = new TextArea();
        commentsArea.setFont(textFieldFont);
        commentsArea.setPromptText("Optional: Add comments for rejected claims");
        commentsArea.setPrefHeight(70);
        root.add(commentsLabel, 0, 12);
        root.add(commentsArea, 1, 12);

        Button updateStatusButton = new Button("Update Status");
        updateStatusButton.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white; -fx-font-weight: bold;");
        root.add(updateStatusButton, 1, 13);


        // Button Actions
        searchButton.setOnAction(event -> showAlert(Alert.AlertType.INFORMATION, "Search", "Searching..."));
        saveChangesButton.setOnAction(event -> showAlert(Alert.AlertType.INFORMATION, "Save", "Changes saved."));
        updateStatusButton.setOnAction(event -> showAlert(Alert.AlertType.INFORMATION, "Update Status", "Status updated."));


        ScrollPane scrollPane = new ScrollPane(root);
        scrollPane.setFitToWidth(true);

        PharmacyPersonnelController.resetTimeUp(scrollPane);

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
