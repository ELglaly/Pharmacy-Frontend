// UpdatePerson.java
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
import javafx.stage.Stage;
import org.example.pharmacymanagmentfrontend.Model.Person;
import org.example.pharmacymanagmentfrontend.Model.UserGenerator;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import static org.example.pharmacymanagmentfrontend.View.SharedView.alterMessage;

public class UpdatePerson {
    private static TextField nameField = new TextField();
    private static TextField emailField = new TextField();
    private static TextField phoneField = new TextField();
    private static TextField licenseField = new TextField();
    private static TextField addressField = new TextField();
    private static TextField searchField = new TextField();
    private static Label feedbackLabel = new Label();
    static ComboBox<String> userTypeCombo = new ComboBox<>(FXCollections.observableArrayList(
            "PharmacyManager", "Pharmacist", "PharmacyTechnician", "Cashier", "Patient"
    ));
    private static DatePicker birthDatePicker = new DatePicker();
    private static Button submitButton;
    private static Person person;

    // Create the main view for updating a person
    public static VBox createUpdatePersonView() {
        VBox root = new VBox(20);
        root.setPadding(new Insets(20));
        root.setStyle("-fx-background-color: #f8f8f8; -fx-border-color: #dcdcdc; -fx-border-width: 1px; -fx-border-radius: 8px;");
        root.getChildren().addAll(createHeader(), createSearchPanel(), feedbackLabel, createForm(), createSubmitButton());
        return root;
    }

    // Create the header section
    private static HBox createHeader() {
        Label headerText = new Label("Update Person");
        headerText.setFont(Font.font(28));
        headerText.setStyle("-fx-font-weight: bold; -fx-text-fill: #2c3e50;");
        headerText.setUnderline(true);
        HBox headerTextContainer = new HBox(headerText);
        headerTextContainer.setAlignment(Pos.CENTER);
        return headerTextContainer;
    }

    // Create the search panel
    private static VBox createSearchPanel() {
        VBox searchPanel = new VBox(10);
        searchPanel.setAlignment(Pos.CENTER);
        searchField.setPromptText("Search by Username");
        Button searchButton = createStyledButton("Search", "#f39c12", "#f1c40f");
        Button resetButton = createStyledButton("Reset", "#e74c3c", "#e74c3c");
        HBox headpanel = new HBox(10, searchField, searchButton, resetButton);
        headpanel.setAlignment(Pos.CENTER_LEFT);
        searchButton.setOnAction(event -> searchByUsername());
        resetButton.setOnAction(event -> resetByUsername());
        searchPanel.getChildren().addAll(headpanel);
        return searchPanel;
    }

    // Create the form for updating person details
    private static GridPane createForm() {
        GridPane formGrid = new GridPane();
        formGrid.setHgap(10);
        formGrid.setVgap(15);
        formGrid.setPadding(new Insets(20));
        formGrid.setAlignment(Pos.CENTER_LEFT);
        addLabeledFieldToGrid(formGrid, "Name:", nameField, 0);
        addLabeledFieldToGrid(formGrid, "Email:", emailField, 1);
        addLabeledFieldToGrid(formGrid, "Phone:", phoneField, 2);
        addLabeledFieldToGrid(formGrid, "License Number:", licenseField, 3);
        addLabeledFieldToGrid(formGrid, "Address:", addressField, 4);
        addLabeledFieldToGrid(formGrid, "Birth Date:", birthDatePicker, 5);
        addLabeledFieldToGrid(formGrid, "User Type:", userTypeCombo, 6);
        return formGrid;
    }

    // Create the submit button
    private static HBox createSubmitButton() {
        submitButton = createStyledButton("Update Person", "#2ecc71", "#27ae60");
        submitButton.setOnAction(event -> handleUpdateSubmit());
        HBox buttonContainer = new HBox(submitButton);
        buttonContainer.setAlignment(Pos.CENTER);
        return buttonContainer;
    }

    // Search for a person by username
    private static void searchByUsername() {
        String searchFieldString = searchField.getText();
        Person foundPerson = UserGenerator.checkUsername(searchFieldString);
        if (foundPerson != null) {
            person = foundPerson;
            feedbackLabel.setText("");
            displayPersonData();
        } else {
            feedbackLabel.setText("No user found with this username");
            feedbackLabel.setStyle("-fx-text-fill: #e74c3c; -fx-font-weight: bold;");
        }
    }

    // Reset the form fields
    private static void resetByUsername() {
        searchField.setText("");
        nameField.setText("");
        emailField.setText("");
        phoneField.setText("");
        licenseField.setText("");
        addressField.setText("");
        birthDatePicker.setValue(null);
        userTypeCombo.setValue(null);
    }

    // Display the person data in the form fields
    private static void displayPersonData() {
        nameField.setText(person.getName());
        emailField.setText(person.getEmail());
        phoneField.setText(person.getPhone());
        licenseField.setText(person.getLicenseNumber());
        addressField.setText(person.getAddress());
        birthDatePicker.setValue(person.getBirthDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        userTypeCombo.setValue(person.getType().toString());
    }

    // Add a labeled field to the grid
    private static void addLabeledFieldToGrid(GridPane grid, String labelText, Control inputField, int rowIndex) {
        Label label = new Label(labelText);
        label.setStyle("-fx-font-weight: bold; -fx-text-fill: #2c3e50;");
        GridPane.setHalignment(label, HPos.LEFT);
        grid.add(label, 0, rowIndex);
        grid.add(inputField, 1, rowIndex);
    }

    // Handle the submit button click event
    private static void handleUpdateSubmit() {
        if (validateFields()) {
            updatePersonDetails();
            Stage alert = alterMessage("", "Successfully Updated", "OK", null);
            alert.show();
            resetByUsername();
        } else {
            feedbackLabel.setText("Some fields are empty or size is less than 3");
        }
    }

    // Validate the form fields
    private static boolean validateFields() {
        TextField[] fields = {nameField, emailField, phoneField, licenseField, addressField};
        for (TextField field : fields) {
            if (field.getText().trim().isEmpty() || field.getText().trim().length() < 3) {
                return false;
            }
        }
        return birthDatePicker.getValue() != null && userTypeCombo.getValue() != null;
    }

    // Update the person details
    private static void updatePersonDetails() {
        String name = nameField.getText().trim();
        String email = emailField.getText().trim();
        String phone = phoneField.getText().trim();
        String licenseNumber = licenseField.getText().trim();
        String address = addressField.getText().trim();
        LocalDate birthDate = birthDatePicker.getValue();
        String userType = userTypeCombo.getValue();
        Date birthDateAsDate = birthDate == null ? null : Date.from(birthDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        UserGenerator.updateUser(person, name, email, phone, licenseNumber, address, birthDate, userType, birthDateAsDate);
    }

    // Create a styled button with hover effect
    private static Button createStyledButton(String text, String backgroundColor, String hoverColor) {
        Button button = new Button(text);
        button.setStyle(String.format("-fx-background-color: %s; -fx-text-fill: white; -fx-font-size: 14px;", backgroundColor));
        button.setOnMouseEntered(e -> button.setStyle(String.format("-fx-background-color: %s; -fx-text-fill: white; -fx-font-size: 14px;", hoverColor)));
        button.setOnMouseExited(e -> button.setStyle(String.format("-fx-background-color: %s; -fx-text-fill: white; -fx-font-size: 14px;", backgroundColor)));
        return button;
    }

}