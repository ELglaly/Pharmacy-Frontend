// AddPatientView.java
package org.example.pharmacymanagmentfrontend.View;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import org.example.pharmacymanagmentfrontend.Controller.PharmacyPersonnelController;

public class AddPatientView {

    // Define input fields
    private static final TextField nameField = new TextField();
    private static final TextField usernameField = new TextField();
    private static final TextField emailField = new TextField();
    private static final TextField phoneField = new TextField();
    private static final TextField addressField = new TextField();
    private static final DatePicker birthDateChooser = new DatePicker();

    public static VBox createAddPersonView() {
        VBox root = new VBox(20); // VBox with 20px spacing between elements
        root.setPadding(new Insets(20)); // Set padding around the VBox
        root.setStyle("-fx-background-color: #f8f8f8; -fx-border-color: #dcdcdc; -fx-border-width: 1px; -fx-border-radius: 8px;");

        // Header section
        Label headerText = new Label("Add Patient");
        headerText.setFont(Font.font("Arial", 28));
        headerText.setStyle("-fx-font-weight: bold; -fx-text-fill: #2c3e50;");
        HBox headerTextContainer = new HBox(headerText);
        headerTextContainer.setAlignment(Pos.CENTER);
        root.getChildren().add(headerTextContainer);

        // Add the form to the root
        root.getChildren().add(createForm());

        // Submit button
        Button submitButton = ManagementDashboard.createStyledButton("Add Patient", "#2ecc71", "#27ae60");
        submitButton.setOnAction(event -> {
            if (validateFields()) {
                showAlert(Alert.AlertType.INFORMATION, "Patient added successfully");
            } else {
                showAlert(Alert.AlertType.ERROR, "All fields should not be Null");
            }
        });

        // Button container
        HBox buttonContainer = new HBox(submitButton);
        buttonContainer.setAlignment(Pos.CENTER);
        root.getChildren().add(buttonContainer);

        // Reset the timer
        PharmacyPersonnelController.resetTimeUp(root);
        return root;
    }

    // Create the form with labeled fields
    private static GridPane createForm() {
        GridPane formGrid = new GridPane();
        formGrid.setHgap(10); // Horizontal gap between columns
        formGrid.setVgap(15); // Vertical gap between rows
        formGrid.setStyle("-fx-padding: 20px;");
        formGrid.setAlignment(Pos.CENTER_LEFT);

        // Add labeled fields to the form
        addLabeledFieldToGrid(formGrid, "Name:", nameField, 0);
        addLabeledFieldToGrid(formGrid, "Username:", usernameField, 1);
        addLabeledFieldToGrid(formGrid, "Email:", emailField, 2);
        addLabeledFieldToGrid(formGrid, "Phone:", phoneField, 3);
        addLabeledFieldToGrid(formGrid, "Address:", addressField, 4);
        addLabeledFieldToGrid(formGrid, "Birth Date:", birthDateChooser, 5);

        return formGrid;
    }

    // Add a labeled field to the grid
    private static void addLabeledFieldToGrid(GridPane grid, String labelText, Control inputField, int rowIndex) {
        Label label = new Label(labelText);
        label.setStyle("-fx-font-weight: bold; -fx-text-fill: #2c3e50;");
        GridPane.setHalignment(label, HPos.LEFT);
        grid.add(label, 0, rowIndex); // Add label to the first column
        grid.add(inputField, 1, rowIndex); // Add input field to the second column
    }

    // Validate the input fields
    private static boolean validateFields() {
        TextField[] fields = {nameField, usernameField, emailField, phoneField, addressField};
        for (TextField field : fields) {
            if (field.getText().trim().isEmpty()) {
                return false; // Return false if any field is empty
            }
        }
        return birthDateChooser.getValue() != null; // Ensure birth date is selected
    }

    // Show an alert with the specified message
    private static void showAlert(Alert.AlertType alertType, String message) {
        Alert alert = new Alert(alertType);
        alert.setContentText(message);
        alert.showAndWait();
    }
}