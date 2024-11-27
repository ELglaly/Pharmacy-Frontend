package org.example.pharmacymanagmentfrontend.View;

import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.example.pharmacymanagmentfrontend.Model.UserGenerator;

public class UserLogs extends Stage {
    private static Button searchButton;
    private static Button resetButton;
    private static TableView<org.example.pharmacymanagmentfrontend.Model.UserLogs> logsTable;
    private static Label totalLogsLabel;
    private static TextField searchField;
    private static String totalLogsNumber;



    // Top Sectio
    public static VBox AddUserLogsView() {
        VBox root = new VBox(20); // VBox with 20px spacing between elements
        root.setStyle("-fx-background-color: #f0f0f0; -fx-padding: 20px;");

        // Header Section
        Label headerText = new Label("User Logs");
        headerText.setFont(new Font("Arial", 24));
        headerText.setStyle("-fx-font-weight: bold; -fx-text-fill: #2c3e50;");
        root.getChildren().add(headerText);

        // Search Section
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

        searchPanel.getChildren().addAll(headpanel);
        root.getChildren().add(searchPanel);
        root.getChildren().add(addTable());

        // Bottom Section: Display Total Logs
        VBox bottomPanel = new VBox(10);
        bottomPanel.setAlignment(Pos.CENTER);

        Label totalLogsTextLabel = new Label("Total Logs:");
        totalLogsTextLabel.setFont(new Font("Arial", 14));
        totalLogsTextLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: #2c3e50;");

        totalLogsNumber=String.valueOf(UserGenerator.getLoginTracker().size());
        totalLogsLabel = new Label(totalLogsNumber);
        totalLogsLabel.setFont(new Font("Arial", 14));
        totalLogsLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: #2980b9;");

        bottomPanel.getChildren().addAll(totalLogsTextLabel, totalLogsLabel);
        root.getChildren().add(bottomPanel);

        searchButton.setOnAction(e -> performSearch());
        resetButton.setOnAction(e -> resetSearch());
        return root;

        // Button Actions



        // Set the stage visible
    }

    private static TableView<org.example.pharmacymanagmentfrontend.Model.UserLogs> addTable()
    {
        logsTable = new TableView<>();

        // Define columns for the table
        TableColumn<org.example.pharmacymanagmentfrontend.Model.UserLogs, String> loginTimeColumn = new TableColumn<>("Login Time");
        loginTimeColumn.setCellValueFactory(new PropertyValueFactory<>("loginTime"));  // Bind to loginTime property

        TableColumn<org.example.pharmacymanagmentfrontend.Model.UserLogs, Boolean> successfulLoginColumn = new TableColumn<>("=Status");
        successfulLoginColumn.setCellValueFactory(new PropertyValueFactory<>("successfulLogin"));  // Bind to successfulLogin property

        TableColumn<org.example.pharmacymanagmentfrontend.Model.UserLogs, String> usernameColumn = new TableColumn<>("Username");
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));  // Bind to username property

        TableColumn<org.example.pharmacymanagmentfrontend.Model.UserLogs, String> userTypeColumn = new TableColumn<>("User Type");
        userTypeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));  // Bind to type property

        // Add columns to the TableView
        logsTable.getColumns().addAll(loginTimeColumn, successfulLoginColumn, usernameColumn, userTypeColumn);


        UserGenerator.Addlogs();
        // Add logic to populate the table with data from UserGenerator
        for (org.example.pharmacymanagmentfrontend.Model.UserLogs userLog : UserGenerator.getLoginTracker()) {
            logsTable.getItems().add(userLog);  // Add each log entry to the table
        }

        // Apply modern styling to the table
        logsTable.setStyle("-fx-font-size: 14px; -fx-font-family: 'Arial'; -fx-padding: 10px; -fx-background-color: white; -fx-border-color: #e0e0e0;");

        // Create a ScrollPane for the TableView to make it scrollable
        ScrollPane tableScrollPane = new ScrollPane();
        tableScrollPane.setContent(logsTable); // Set the TableView as the content of the ScrollPane
        tableScrollPane.setFitToHeight(true);  // Make the ScrollPane fit the height of the content
        tableScrollPane.setFitToWidth(true);   // Make the ScrollPane fit the width of the content
      //  totalLogsLabel.setText(String.valueOf(logsTable.getItems().size())); // set the number of logs

        return logsTable;

    }




    private static void performSearch() {
        String searchQuery = searchField.getText().trim().toLowerCase(); // Make search case insensitive
        if (!searchQuery.isEmpty()) {
            logsTable.getItems().clear();

            // Loop through all the logs and add matching ones to the table
            for (org.example.pharmacymanagmentfrontend.Model.UserLogs userlog : UserGenerator.getLoginTracker()) {
                if (!userlog.getUsername().isEmpty() && userlog.getUsername().toLowerCase().contains(searchQuery.toLowerCase())) {
                    logsTable.getItems().add(userlog);
                }
            }
            totalLogsNumber=String.valueOf(logsTable.getItems().size());
           totalLogsLabel.setText(totalLogsNumber); // set the number of logs
        }
    }

    private static void resetSearch() {
        searchField.setText(""); // Clear search field
        logsTable.getItems().clear();
        for (org.example.pharmacymanagmentfrontend.Model.UserLogs userLog : UserGenerator.getLoginTracker()) {
            logsTable.getItems().add(userLog);  // Add each log entry to the table
        }
        totalLogsNumber=String.valueOf(logsTable.getItems().size());
        totalLogsLabel.setText(totalLogsNumber);
    }

}
