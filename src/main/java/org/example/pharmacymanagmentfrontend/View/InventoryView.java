package org.example.pharmacymanagmentfrontend.View;

import javafx.animation.PauseTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Popup;
import javafx.util.Duration;
import org.example.pharmacymanagmentfrontend.Controller.PharmacyPersonnelController;
import org.example.pharmacymanagmentfrontend.Model.Inventory;
import org.example.pharmacymanagmentfrontend.Model.UserGenerator;
import javafx.stage.Stage;
import javax.swing.text.View;

import static org.example.pharmacymanagmentfrontend.View.SharedView.alterMessage;

//import static org.example.pharmacymanagmentfrontend.Model.UserGenerator.inventoryStock;

public class InventoryView extends Stage {
    private static Button searchButton;
    private static Button resetButton;
    private static TableView<org.example.pharmacymanagmentfrontend.Model.Inventory> stocksTable;
    private static Label totalStockLabel;
    private static TextField searchField;
    private static String totalStockNumber;

    public static VBox createInventoryView() {
        VBox root = new VBox(20); // VBox with 20px spacing between elements
        root.setStyle("-fx-background-color: #f8f8f8; -fx-padding: 20px;");
        // Header Section
        Label headerText = new Label("Inventory Stocks");
        headerText.setFont(new Font("Arial", 24));
        headerText.setStyle("-fx-font-weight: bold; -fx-text-fill: #2c3e50;");
        root.getChildren().add(headerText);
        // Search Section
        VBox searchPanel = new VBox(10);
        searchPanel.setAlignment(Pos.CENTER);
        searchField = new TextField();
        searchField.setPromptText("Search by Name");  // Set a prompt text
        searchButton = new Button("Search");
         resetButton = new Button("Reset");
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

        // Bottom Section: Display Total Stock
        VBox bottomPanel = new VBox(10);
        bottomPanel.setAlignment(Pos.CENTER);
        //add total loga label
        Label totalStockTextLabel = addTotalStockLabel();
        bottomPanel.getChildren().addAll(totalStockTextLabel, totalStockLabel);
        root.getChildren().add(bottomPanel);

        // add action to the buttons
        searchButton.setOnAction(e -> performSearch());
        resetButton.setOnAction(e -> resetSearch());
        updateLowStockNotification();
        return root;
    }

    private static Label addTotalStockLabel() {
        Label totalStockTextLabel = new Label("Total Stock:");
        totalStockTextLabel.setFont(new Font("Arial", 14));
        totalStockTextLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: #2c3e50;");
        totalStockNumber=String.valueOf(UserGenerator.getLoginTracker().size());
        totalStockLabel = new Label(totalStockNumber);
        totalStockLabel.setFont(new Font("Arial", 14));
        totalStockLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: #2980b9;");

        return totalStockTextLabel;
    }

    private static TableView<org.example.pharmacymanagmentfrontend.Model.Inventory> addTable()
    {
        stocksTable = new TableView<>();
        // Define columns for the table
        TableColumn<org.example.pharmacymanagmentfrontend.Model.Inventory, String> loginTimeColumn = new TableColumn<>("Name");
        loginTimeColumn.setCellValueFactory(new PropertyValueFactory<>("name"));  // Bind to loginTime property
        TableColumn<org.example.pharmacymanagmentfrontend.Model.Inventory, Boolean> successfulLoginColumn = new TableColumn<>("Quantity");
        successfulLoginColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));  // Bind to successfulLogin property
        TableColumn<org.example.pharmacymanagmentfrontend.Model.Inventory, String> usernameColumn = new TableColumn<>("Available");
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("available"));  // Bind to username property
        TableColumn<org.example.pharmacymanagmentfrontend.Model.Inventory, String> userTypeColumn = new TableColumn<>("Low");
        userTypeColumn.setCellValueFactory(new PropertyValueFactory<>("lowStock"));  // Bind to type property
        // Add columns to the TableView
        stocksTable.getColumns().addAll(loginTimeColumn, successfulLoginColumn, usernameColumn, userTypeColumn);
        // add lods to test the interface
        UserGenerator.generateRandomInventory();
       //  Add logic to populate the table with data from UserGenerator
        for (org.example.pharmacymanagmentfrontend.Model.Inventory inventoryStock : UserGenerator.getInventoryStock()) {
            stocksTable.getItems().add(inventoryStock);  // Add each log entry to the table
        }
       //  Apply modern styling to the table
        stocksTable.setStyle("-fx-font-size: 14px; -fx-font-family: 'Arial'; -fx-padding: 10px; -fx-background-color: white; -fx-border-color: #e0e0e0;");
        // Create a ScrollPane for the TableView to make it scrollable
        ScrollPane tableScrollPane = new ScrollPane();
        tableScrollPane.setContent(stocksTable); // Set the TableView as the content of the ScrollPane
        tableScrollPane.setFitToHeight(true);  // Make the ScrollPane fit the height of the content
        tableScrollPane.setFitToWidth(true);
        PharmacyPersonnelController.resetTimeUp(stocksTable);// Make the ScrollPane fit the width of the content
        return stocksTable;
    }




    private static void performSearch() {
        String searchQuery = searchField.getText().trim().toLowerCase(); // Make search case insensitive
        if (!searchQuery.isEmpty()) {
            stocksTable.getItems().clear();
            // Loop through all the logs and add matching ones to the table
            for (org.example.pharmacymanagmentfrontend.Model.Inventory inventoryStock : UserGenerator.getInventoryStock()) {
                  if (!inventoryStock.getName().isEmpty() && inventoryStock.getName().toLowerCase().contains(searchQuery.toLowerCase())) {
                    stocksTable.getItems().add(inventoryStock);
                }
            }
            totalStockNumber=String.valueOf(stocksTable.getItems().size());
            totalStockLabel.setText(totalStockNumber); // set the number of logs
        }
    }

    private static void resetSearch() {
        searchField.setText(""); // Clear search field
        stocksTable.getItems().clear();
        for (org.example.pharmacymanagmentfrontend.Model.Inventory inventoryStock : UserGenerator.getInventoryStock()) {
                 stocksTable.getItems().add(inventoryStock);
        }
        totalStockNumber=String.valueOf(stocksTable.getItems().size());
        totalStockLabel.setText(InventoryView.totalStockNumber);
    }

     private static void updateLowStockNotification() {
         long lowStockCount = UserGenerator.getInventoryStock().stream().filter(med -> med.getQuantity() <= 10).count();
         if (lowStockCount > 0) {
             Stage alert = alterMessage("attention: Low Stock Items Detected \nSome items have low stock. Please restock them soon.",
            "Low Stock Warning",
             "OK",null);
             alert.show();
         }
    }


}


