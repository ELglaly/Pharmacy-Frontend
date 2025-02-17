// UserLogs.java
package org.example.pharmacymanagmentfrontend.View;

import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.example.pharmacymanagmentfrontend.Controller.PharmacyPersonnelController;
import org.example.pharmacymanagmentfrontend.Model.UserGenerator;

public class UserLogs extends Stage {
    private static Button searchButton;
    private static Button resetButton;
    private static TableView<org.example.pharmacymanagmentfrontend.Model.UserLogs> logsTable;
    private static Label totalLogsLabel;
    private static TextField searchField;
    private static String totalLogsNumber;

    public static VBox AddUserLogsView() {
        VBox root = new VBox(20);
        root.setStyle("-fx-background-color: #f8f8f8; -fx-padding: 20px;");
        root.getChildren().addAll(createHeader(), createSearchPanel(), addTable(), createBottomPanel());
        return root;
    }

    // Create the header section
    private static Label createHeader() {
        Label headerText = new Label("User Logs");
        headerText.setFont(new Font(24));
        headerText.setStyle("-fx-font-weight: bold; -fx-text-fill: #2c3e50;");
        return headerText;
    }

    // Create the search panel
    private static VBox createSearchPanel() {
        VBox searchPanel = new VBox(10);
        searchPanel.setAlignment(Pos.CENTER);
        searchField = new TextField();
        searchField.setPromptText("Search by Username");
        searchButton = createStyledButton("Search", "#f39c12", "#f1c40f");
        resetButton = createStyledButton("Reset", "#e74c3c", "#e74c3c");
        HBox headpanel = new HBox(10, searchField, searchButton, resetButton);
        headpanel.setAlignment(Pos.CENTER_LEFT);
        searchPanel.getChildren().addAll(headpanel);
        searchButton.setOnAction(e -> performSearch());
        resetButton.setOnAction(e -> resetSearch());
        return searchPanel;
    }

    // Create the table for displaying logs
    private static TableView<org.example.pharmacymanagmentfrontend.Model.UserLogs> addTable() {
        logsTable = new TableView<>();
        logsTable.getColumns().addAll(createLoginTimeColumn(), createStatusColumn(), createUsernameColumn(), createUserTypeColumn());
        populateTable();
        logsTable.setStyle("-fx-font-size: 14px; -fx-font-family: 'Arial'; -fx-padding: 10px; -fx-background-color: white; -fx-border-color: #e0e0e0;");
        PharmacyPersonnelController.resetTimeUp(logsTable);
        return logsTable;
    }

    // Create the bottom panel for displaying total logs
    private static VBox createBottomPanel() {
        VBox bottomPanel = new VBox(10);
        bottomPanel.setAlignment(Pos.CENTER);
        Label totalLogsTextLabel = new Label("Total Logs:");
        totalLogsTextLabel.setFont(new Font("Arial", 14));
        totalLogsTextLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: #2c3e50;");
        totalLogsNumber = String.valueOf(UserGenerator.getLoginTracker().size());
        totalLogsLabel = new Label(totalLogsNumber);
        totalLogsLabel.setFont(new Font("Arial", 14));
        totalLogsLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: #2980b9;");
        bottomPanel.getChildren().addAll(totalLogsTextLabel, totalLogsLabel);
        return bottomPanel;
    }

    // Create the login time column
    private static TableColumn<org.example.pharmacymanagmentfrontend.Model.UserLogs, String> createLoginTimeColumn() {
        TableColumn<org.example.pharmacymanagmentfrontend.Model.UserLogs, String> loginTimeColumn = new TableColumn<>("Login Time");
        loginTimeColumn.setCellValueFactory(new PropertyValueFactory<>("loginTime"));
        return loginTimeColumn;
    }

    // Create the status column
    private static TableColumn<org.example.pharmacymanagmentfrontend.Model.UserLogs, Boolean> createStatusColumn() {
        TableColumn<org.example.pharmacymanagmentfrontend.Model.UserLogs, Boolean> successfulLoginColumn = new TableColumn<>("Status");
        successfulLoginColumn.setCellValueFactory(new PropertyValueFactory<>("successfulLogin"));
        return successfulLoginColumn;
    }

    // Create the username column
    private static TableColumn<org.example.pharmacymanagmentfrontend.Model.UserLogs, String> createUsernameColumn() {
        TableColumn<org.example.pharmacymanagmentfrontend.Model.UserLogs, String> usernameColumn = new TableColumn<>("Username");
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        return usernameColumn;
    }

    // Create the user type column
    private static TableColumn<org.example.pharmacymanagmentfrontend.Model.UserLogs, String> createUserTypeColumn() {
        TableColumn<org.example.pharmacymanagmentfrontend.Model.UserLogs, String> userTypeColumn = new TableColumn<>("User Type");
        userTypeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        return userTypeColumn;
    }

    // Populate the table with data
    private static void populateTable() {
        for (org.example.pharmacymanagmentfrontend.Model.UserLogs userLog : UserGenerator.getLoginTracker()) {
            logsTable.getItems().add(userLog);
        }
    }

    // Perform search based on the search field input
    private static void performSearch() {
        String searchQuery = searchField.getText().trim().toLowerCase();
        if (!searchQuery.isEmpty()) {
            logsTable.getItems().clear();
            for (org.example.pharmacymanagmentfrontend.Model.UserLogs userlog : UserGenerator.getLoginTracker()) {
                if (!userlog.getUsername().isEmpty() && userlog.getUsername().toLowerCase().contains(searchQuery)) {
                    logsTable.getItems().add(userlog);
                }
            }
            totalLogsNumber = String.valueOf(logsTable.getItems().size());
            totalLogsLabel.setText(totalLogsNumber);
        }
    }

    // Reset the search and display all logs
    private static void resetSearch() {
        searchField.setText("");
        logsTable.getItems().clear();
        populateTable();
        totalLogsNumber = String.valueOf(logsTable.getItems().size());
        totalLogsLabel.setText(totalLogsNumber);
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