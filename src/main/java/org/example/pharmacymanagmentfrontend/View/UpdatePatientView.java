package org.example.pharmacymanagmentfrontend.View;

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

public class UpdatePatientView {
    private static TextField nameField = new TextField();
    private static TextField usernameField = new TextField();
    private static TextField emailField = new TextField();
    private static TextField phoneField = new TextField();
    private static TextField addressField = new TextField();
    private static TextField searchField=new TextField();
    private static Label feedbackLabel = new Label();
    private static DatePicker birthDatePicker = new DatePicker();
    private static Button submitButton;
    private static Person person;

    public static VBox createUpdatePersonView() {
        VBox root = new VBox(20);
        root.setPadding(new Insets(20));
        root.setStyle("-fx-background-color: #f8f8f8; -fx-border-color: #dcdcdc; -fx-border-width: 1px; -fx-border-radius: 8px;");

        // Header
        Label headerText = new Label("Update Patient");
        headerText.setFont(Font.font( 28));
        headerText.setStyle("-fx-font-weight: bold; -fx-text-fill: #2c3e50;");
        headerText.setUnderline(true);

        HBox headerTextContainer = new HBox(headerText);
        headerTextContainer.setAlignment(Pos.CENTER);
        root.getChildren().add(headerTextContainer);

        VBox searchPanel = new VBox(10);
        searchPanel.setAlignment(Pos.CENTER);
        searchField = new TextField();
        searchField.setPromptText("Search by Username");  // Set a prompt text
        Button searchButton = new Button("Search");
        Button resetButton = new Button("Reset");
        // Create an HBox to hold the TextField and Buttons horizontally
        HBox headpanel = new HBox(10);  // 10px spacing between elements
        headpanel.setAlignment(Pos.CENTER_LEFT);  // Align elements to the left within the HBox
        headpanel.getChildren().addAll(searchField, searchButton, resetButton);
        searchButton.setStyle("-fx-background-color: #f39c12; -fx-text-fill: white; -fx-font-size: 14px;");
        resetButton.setStyle("-fx-background-color: #e74c3c; -fx-text-fill: white; -fx-font-size: 14px;");
        searchButton.setMaxWidth(150);
        resetButton.setMaxWidth(150);

        searchButton.setOnMouseEntered(e -> searchButton.setStyle("-fx-background-color: #f1c40f; -fx-text-fill: white; -fx-font-size: 14px;"));
        searchButton.setOnMouseExited(e -> searchButton.setStyle("-fx-background-color: #f39c12; -fx-text-fill: white; -fx-font-size: 14px;"));

        resetButton.setOnMouseEntered(e -> resetButton.setStyle("-fx-background-color: #e74c3c; -fx-text-fill: white; -fx-font-size: 14px;"));
        resetButton.setOnMouseExited(e -> resetButton.setStyle("-fx-background-color: #e74c3c; -fx-text-fill: white; -fx-font-size: 14px;"));

        searchButton.setOnAction(event-> searchByUsername());
        resetButton.setOnAction(event-> resetByUsername());

        searchPanel.getChildren().addAll(headpanel);
        root.getChildren().add(searchPanel);

        // feedbackLabel.setStyle("-fx-text-fill: #e74c3c; -fx-font-weight: bold;"); // Red text for errors
        root.getChildren().add(feedbackLabel);
        // Form
        root.getChildren().add(createForm());

        // Submit Button
        submitButton = ManagementDashboard.createStyledButton("Update Patient", "#2ecc71", "#27ae60");
        submitButton.setOnAction(event -> handleUpdateSubmit());

        HBox buttonContainer = new HBox(submitButton);
        buttonContainer.setAlignment(Pos.CENTER);
        root.getChildren().add(buttonContainer);

        return root;
    }

    private static GridPane createForm() {
        GridPane formGrid = new GridPane();
        formGrid.setHgap(10);
        formGrid.setVgap(15);
        formGrid.setPadding(new Insets(20));
        formGrid.setAlignment(Pos.CENTER_LEFT);

        addLabeledFieldToGrid(formGrid, "Name:", nameField, 0);
        addLabeledFieldToGrid(formGrid, "Email:", emailField, 1);
        addLabeledFieldToGrid(formGrid, "Phone:", phoneField, 2);
        addLabeledFieldToGrid(formGrid, "Address:", addressField, 3);
        addLabeledFieldToGrid(formGrid, "Birth Date:", birthDatePicker, 4);

        return formGrid;
    }

    private static void searchByUsername() {
        String searchFieldString = searchField.getText();
        Person foundPerson = UserGenerator.checkUsername(searchFieldString);
        if (foundPerson != null) {
            person = foundPerson;
            feedbackLabel.setText("");
            DisplayPersonData();
        } else {
            feedbackLabel.setText("No user found this username");
            feedbackLabel.setStyle("-fx-text-fill: #e74c3c; -fx-font-weight: bold;"); // Red text for errors
        }
    }
    private static void resetByUsername() {
        searchField.setText("");
        nameField.setText("");
        emailField.setText("");
        phoneField.setText("");
        addressField.setText("");
        birthDatePicker.setValue(person.getBirthDate().toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate());
    }

    private static void DisplayPersonData() {
        nameField.setText(person.getName());
        emailField.setText(person.getEmail());
        phoneField.setText(person.getPhone());
        addressField.setText(person.getAddress());
        birthDatePicker.setValue(person.getBirthDate().toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate());
    }

    private static void addLabeledFieldToGrid(GridPane grid, String labelText, Control inputField, int rowIndex) {
        Label label = new Label(labelText);
        label.setStyle("-fx-font-weight: bold; -fx-text-fill: #2c3e50;");
        GridPane.setHalignment(label, HPos.LEFT);
        grid.add(label, 0, rowIndex);
        grid.add(inputField, 1, rowIndex);
    }

    private static void handleUpdateSubmit() {
        if (validateFields()) {
            String name = nameField.getText().trim();
            String email = emailField.getText().trim();
            String phone = phoneField.getText().trim();
            String address = addressField.getText().trim();
            LocalDate birthDate = birthDatePicker.getValue();
            // Example: Convert LocalDate to Date if needed
            Date birthDateAsDate = birthDate == null ? null : Date.from(birthDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
            UserGenerator.updateUser(person,name,email,phone,"licenseNumber",address,birthDate,"Patient",birthDateAsDate);
            Stage alert = alterMessage("",
                    "Successfully Updated",
                    "OK",null);
            alert.show();
            resetByUsername();

        } else {
            feedbackLabel.setText("Some Fields are Empty or Size less than 3 ");
        }
    }

    private static boolean validateFields() {
        TextField[] fields = {nameField, emailField, phoneField, addressField};
        for (TextField field : fields) {
            if (field.getText().trim().isEmpty() || field.getText().trim().length()<3) {
                return false;
            }
        }
        return birthDatePicker.getValue() != null ;
    }
}

