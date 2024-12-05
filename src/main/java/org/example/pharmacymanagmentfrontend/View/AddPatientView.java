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
import org.example.pharmacymanagmentfrontend.Controller.PharmacyPersonnelController;

import java.util.Date;

public class AddPatientView {

    private static TextField nameField=new TextField();
    private static TextField usernameField=new TextField();
    private static TextField emailField=new TextField();
    private static TextField phoneField=new TextField();
    private static TextField addressField=new TextField();
    private static SpinnerDateModel birthDateChooser=new SpinnerDateModel();
    private static Button submitButton;

    public static VBox createAddPersonView() {
        // Create the root VBox for the form
        VBox root = new VBox(20); // 20px spacing
        root.setPadding(new Insets(20));
        root.setStyle("-fx-background-color: #f8f8f8; -fx-border-color: #dcdcdc; -fx-border-width: 1px; -fx-border-radius: 8px;");

        // Header Section
        Label headerText = new Label("Add Patient");
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
        submitButton = ManagementDashboard.createStyledButton("Add Patient", "#2ecc71", "#27ae60");
        submitButton.setOnAction(event -> handleSubmit());

        HBox buttonContainer = new HBox(submitButton);
        buttonContainer.setAlignment(Pos.CENTER); // Center the button inside the HBox

        root.getChildren().add(buttonContainer);
        PharmacyPersonnelController.resetTimeUp(root);
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
          addLabeledFieldToGrid(formGrid, "Address:", new TextField(), 4);
        addLabeledFieldToGrid(formGrid, "Birth Date:", new DatePicker(), 5);
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
            String email = emailField.getText().trim();
            String phone = phoneField.getText().trim();
            String address = addressField.getText().trim();
            Date birthDate = birthDateChooser.getDate();
        }
    }

    private static boolean validateFields() {
        // Check if any of the fields are empty
        TextField[] fields = {nameField, usernameField, emailField, phoneField, addressField};
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
