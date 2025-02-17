// InsuranceInterface.java
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

    // Create the main view of the Insurance Interface
    public static ScrollPane createInsuranceInterfaceView() {
        Stage stage = new Stage();
        stage.setTitle("Insurance Claim Management");

        GridPane root = createRootLayout();
        addSearchClaimSection(root);
        addEditClaimSection(root);
        addTrackClaimSection(root);
        addButtonsActions(root);

        ScrollPane scrollPane = new ScrollPane(root);
        scrollPane.setFitToWidth(true);
        PharmacyPersonnelController.resetTimeUp(scrollPane);

        return scrollPane;
    }

    // Create the root layout
    private static GridPane createRootLayout() {
        GridPane root = new GridPane();
        root.setPadding(new Insets(15));
        root.setHgap(10);
        root.setVgap(15);
        root.setStyle("-fx-background-color: #f8f8f8;");
        return root;
    }

    // Add the search claim section
    private static void addSearchClaimSection(GridPane root) {
        Label searchLabel = createSectionHeader("Search Claim:");
        root.add(searchLabel, 0, 0, 2, 1);

        addSearchFields(root);
        addSearchButton(root);
    }

    // Create and add search fields
    private static void addSearchFields(GridPane root) {
        Label searchByIdLabel = createLabel("Claim ID:");
        TextField searchByIdField = createTextField("Enter Claim ID");
        root.add(searchByIdLabel, 0, 1);
        root.add(searchByIdField, 1, 1);

        Label searchByNameLabel = createLabel("Patient Name:");
        TextField searchByNameField = createTextField("Enter Patient Name");
        root.add(searchByNameLabel, 0, 2);
        root.add(searchByNameField, 1, 2);
    }

    // Create and add search button
    private static void addSearchButton(GridPane root) {
        Button searchButton = createButton("Search", "#4CAF50");
        root.add(searchButton, 1, 3);
    }

    // Add the edit claim section
    private static void addEditClaimSection(GridPane root) {
        Label editLabel = createSectionHeader("Edit or Correct Claim:");
        root.add(editLabel, 0, 4, 2, 1);

        addEditFields(root);
        addSaveChangesButton(root);
    }

    // Create and add edit fields
    private static void addEditFields(GridPane root) {
        Label claimIdLabel = createLabel("Claim ID:");
        TextField claimIdField = createTextField("Claim ID");
        claimIdField.setEditable(false);
        root.add(claimIdLabel, 0, 5);
        root.add(claimIdField, 1, 5);

        Label patientNameLabel = createLabel("Patient Name:");
        TextField patientNameField = createTextField("");
        root.add(patientNameLabel, 0, 6);
        root.add(patientNameField, 1, 6);

        Label claimAmountLabel = createLabel("Claim Amount:");
        TextField claimAmountField = createTextField("");
        root.add(claimAmountLabel, 0, 7);
        root.add(claimAmountField, 1, 7);

        Label claimDescriptionLabel = createLabel("Description:");
        TextArea claimDescriptionArea = createTextArea("Enter description");
        root.add(claimDescriptionLabel, 0, 8);
        root.add(claimDescriptionArea, 1, 8);
    }

    // Create and add save changes button
    private static void addSaveChangesButton(GridPane root) {
        Button saveChangesButton = createButton("Save Changes", "#2196F3");
        root.add(saveChangesButton, 1, 9);
    }

    // Helper methods to create UI components
    private static Label createSectionHeader(String text) {
        Label label = new Label(text);
        label.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-text-fill: #004080;");
        return label;
    }

    private static Label createLabel(String text) {
        Label label = new Label(text);
        label.setStyle("-fx-font-size: 12px; -fx-text-fill: #333333;");
        return label;
    }

    private static TextField createTextField(String promptText) {
        TextField textField = new TextField();
        textField.setFont(Font.font("Arial", 12));
        textField.setPromptText(promptText);
        return textField;
    }

    private static TextArea createTextArea(String promptText) {
        TextArea textArea = new TextArea();
        textArea.setFont(Font.font("Arial", 12));
        textArea.setPromptText(promptText);
        textArea.setPrefHeight(70);
        return textArea;
    }

    private static Button createButton(String text, String color) {
        Button button = new Button(text);
        button.setStyle("-fx-background-color: " + color + "; -fx-text-fill: white; -fx-font-weight: bold;");
        return button;
    }
    // Add the track claim section
    private static void addTrackClaimSection(GridPane root) {
        String sectionHeaderStyle = "-fx-font-size: 14px; -fx-font-weight: bold; -fx-text-fill: #004080;";
        String labelStyle = "-fx-font-size: 12px; -fx-text-fill: #333333;";
        Font textFieldFont = Font.font("Arial", 12);

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
    }

    // Add actions to the buttons
    private static void addButtonsActions(GridPane root) {
        Button searchButton = (Button) root.getChildren().get(7);
        Button saveChangesButton = (Button) root.getChildren().get(19);
        Button updateStatusButton = (Button) root.getChildren().get(25);

        searchButton.setOnAction(event -> showAlert(Alert.AlertType.INFORMATION, "Search", "Searching..."));
        saveChangesButton.setOnAction(event -> showAlert(Alert.AlertType.INFORMATION, "Save", "Changes saved."));
        updateStatusButton.setOnAction(event -> showAlert(Alert.AlertType.INFORMATION, "Update Status", "Status updated."));
    }

    // Show an alert with the specified message
    private static void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}