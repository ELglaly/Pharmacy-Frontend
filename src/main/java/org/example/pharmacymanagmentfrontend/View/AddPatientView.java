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

    private static TextField nameField = new TextField();
    private static TextField usernameField = new TextField();
    private static TextField emailField = new TextField();
    private static TextField phoneField = new TextField();
    private static TextField addressField = new TextField();
    private static DatePicker birthDateChooser = new DatePicker();

    public static VBox createAddPersonView() {
        VBox root = new VBox(20);
        root.setPadding(new Insets(20));
        root.setStyle("-fx-background-color: #f8f8f8; -fx-border-color: #dcdcdc; -fx-border-width: 1px; -fx-border-radius: 8px;");

        //The header of the window
        Label headerText = new Label("Add Patient");
        headerText.setFont(Font.font("Arial", 28));
        headerText.setStyle("-fx-font-weight: bold; -fx-text-fill: #2c3e50;");
        headerText.setUnderline(true);
        HBox headerTextContainer = new HBox(headerText);
        headerTextContainer.setAlignment(Pos.CENTER);
        root.getChildren().add(headerTextContainer);

        root.getChildren().add(createForm());

        Button submitButton = ManagementDashboard.createStyledButton("Add Patient", "#2ecc71", "#27ae60");
        submitButton.setOnAction(event -> {
            if (validateFields()) {
                showAlert(Alert.AlertType.INFORMATION, "Patient added successfully");
            } else {
                showAlert(Alert.AlertType.ERROR, "All fields should not be Null");
            }
        });

        HBox buttonContainer = new HBox(submitButton);
        buttonContainer.setAlignment(Pos.CENTER);
        root.getChildren().add(buttonContainer);

        PharmacyPersonnelController.resetTimeUp(root);
        return root;
    }

    //create the form and its components
    private static GridPane createForm() {
        GridPane formGrid = new GridPane();
        formGrid.setHgap(10);
        formGrid.setVgap(15);
        formGrid.setStyle("-fx-padding: 20px;");
        formGrid.setAlignment(Pos.CENTER_LEFT);

        addLabeledFieldToGrid(formGrid, "Name:", nameField, 0);
        addLabeledFieldToGrid(formGrid, "Username:", usernameField, 1);
        addLabeledFieldToGrid(formGrid, "Email:", emailField, 2);
        addLabeledFieldToGrid(formGrid, "Phone:", phoneField, 3);
        addLabeledFieldToGrid(formGrid, "Address:", addressField, 4);
        addLabeledFieldToGrid(formGrid, "Birth Date:", birthDateChooser, 5);

        return formGrid;
    }

    //function to add label
    private static void addLabeledFieldToGrid(GridPane grid, String labelText, Control inputField, int rowIndex) {
        Label label = new Label(labelText);
        label.setStyle("-fx-font-weight: bold; -fx-text-fill: #2c3e50;");

        GridPane.setHalignment(label, HPos.LEFT);
        grid.add(label, 0, rowIndex);
        grid.add(inputField, 1, rowIndex);
    }

    //validates the fields
    private static boolean validateFields() {
        TextField[] fields = {nameField, usernameField, emailField, phoneField, addressField};
        for (TextField field : fields) {
            if (field.getText().trim().isEmpty()) {
                return false;
            }
        }
        return birthDateChooser.getValue() != null;
    }

    private static void showAlert(Alert.AlertType alertType, String message) {
        Alert alert = new Alert(alertType);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
