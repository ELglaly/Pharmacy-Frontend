// InventoryView.java
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

import static org.example.pharmacymanagmentfrontend.View.SharedView.alterMessage;

public class InventoryView extends Stage {
    private static Button searchButton;
    private static Button resetButton;
    private static TableView<org.example.pharmacymanagmentfrontend.Model.Inventory> stocksTable;
    private static TextField searchField;
    private static String totalStockNumber;
    private static Label totalStockLabel;

    // Create the main view for the inventory
    public static VBox createInventoryView() {
        VBox root = new VBox(20);
        root.setStyle("-fx-background-color: #f8f8f8; -fx-padding: 20px;");
        root.getChildren().add(createHeader());
        root.getChildren().add(createSearchPanel());
        root.getChildren().add(createTable());
        root.getChildren().add(createBottomPanel());
        addActionsToButtons();
        updateLowStockNotification();
        return root;
    }

    // Create the header section
    private static Label createHeader() {
        Label headerText = new Label("Inventory Stocks");
        headerText.setFont(new Font("Arial", 24));
        headerText.setStyle("-fx-font-weight: bold; -fx-text-fill: #2c3e50;");
        return headerText;
    }

    // Create the search panel
    private static VBox createSearchPanel() {
        VBox searchPanel = new VBox(10);
        searchPanel.setAlignment(Pos.CENTER);
        searchField = new TextField();
        searchField.setPromptText("Search by Name");
        searchButton = new Button("Search");
        resetButton = new Button("Reset");
        styleButtons();
        HBox headpanel = new HBox(10);
        headpanel.setAlignment(Pos.CENTER_LEFT);
        headpanel.getChildren().addAll(searchField, searchButton, resetButton);
        searchPanel.getChildren().addAll(headpanel);
        return searchPanel;
    }

    // Style the search and reset buttons
    private static void styleButtons() {
        searchButton.setStyle("-fx-background-color: #f39c12; -fx-text-fill: white; -fx-font-size: 14px;");
        resetButton.setStyle("-fx-background-color: #e74c3c; -fx-text-fill: white; -fx-font-size: 14px;");
        searchButton.setMaxWidth(150);
        resetButton.setMaxWidth(150);
        searchButton.setOnMouseEntered(e -> searchButton.setStyle("-fx-background-color: #f1c40f; -fx-text-fill: white; -fx-font-size: 14px;"));
        searchButton.setOnMouseExited(e -> searchButton.setStyle("-fx-background-color: #f39c12; -fx-text-fill: white; -fx-font-size: 14px;"));
        resetButton.setOnMouseEntered(e -> resetButton.setStyle("-fx-background-color: #e74c3c; -fx-text-fill: white; -fx-font-size: 14px;"));
        resetButton.setOnMouseExited(e -> resetButton.setStyle("-fx-background-color: #e74c3c; -fx-text-fill: white; -fx-font-size: 14px;"));
    }

    // Create the table for displaying inventory
    private static TableView<org.example.pharmacymanagmentfrontend.Model.Inventory> createTable() {
        stocksTable = new TableView<>();
        stocksTable.getColumns().addAll(createNameColumn(), createQuantityColumn(), createAvailableColumn(), createLowStockColumn());
        populateTable();
        stocksTable.setStyle("-fx-font-size: 14px; -fx-font-family: 'Arial'; -fx-padding: 10px; -fx-background-color: white; -fx-border-color: #e0e0e0;");
        PharmacyPersonnelController.resetTimeUp(stocksTable);
        return stocksTable;
    }

    // Create the name column
    private static TableColumn<org.example.pharmacymanagmentfrontend.Model.Inventory, String> createNameColumn() {
        TableColumn<org.example.pharmacymanagmentfrontend.Model.Inventory, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        return nameColumn;
    }

    // Create the quantity column
    private static TableColumn<org.example.pharmacymanagmentfrontend.Model.Inventory, Boolean> createQuantityColumn() {
        TableColumn<org.example.pharmacymanagmentfrontend.Model.Inventory, Boolean> quantityColumn = new TableColumn<>("Quantity");
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        return quantityColumn;
    }

    // Create the available column
    private static TableColumn<org.example.pharmacymanagmentfrontend.Model.Inventory, String> createAvailableColumn() {
        TableColumn<org.example.pharmacymanagmentfrontend.Model.Inventory, String> availableColumn = new TableColumn<>("Available");
        availableColumn.setCellValueFactory(new PropertyValueFactory<>("available"));
        return availableColumn;
    }

    // Create the low stock column
    private static TableColumn<org.example.pharmacymanagmentfrontend.Model.Inventory, String> createLowStockColumn() {
        TableColumn<org.example.pharmacymanagmentfrontend.Model.Inventory, String> lowStockColumn = new TableColumn<>("Low");
        lowStockColumn.setCellValueFactory(new PropertyValueFactory<>("lowStock"));
        return lowStockColumn;
    }

    // Populate the table with inventory data
    private static void populateTable() {
        UserGenerator.generateRandomInventory();
        stocksTable.getItems().addAll(UserGenerator.getInventoryStock());
    }

    // Create the bottom panel to display total stock
    private static VBox createBottomPanel() {
        VBox bottomPanel = new VBox(10);
        bottomPanel.setAlignment(Pos.CENTER);
        Label totalStockTextLabel = new Label("Total Stock:");
        totalStockTextLabel.setFont(new Font("Arial", 14));
        totalStockTextLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: #2c3e50;");
        totalStockNumber = String.valueOf(UserGenerator.getLoginTracker().size());
        totalStockLabel = new Label(totalStockNumber);
        totalStockLabel.setFont(new Font("Arial", 14));
        totalStockLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: #2980b9;");
        bottomPanel.getChildren().addAll(totalStockTextLabel, totalStockLabel);
        return bottomPanel;
    }

    // Add actions to the search and reset buttons
    private static void addActionsToButtons() {
        searchButton.setOnAction(e -> performSearch());
        resetButton.setOnAction(e -> resetSearch());
    }

    // Perform search based on the search query
    private static void performSearch() {
        String searchQuery = searchField.getText().trim().toLowerCase();
        if (!searchQuery.isEmpty()) {
            stocksTable.getItems().clear();
            for (org.example.pharmacymanagmentfrontend.Model.Inventory inventoryStock : UserGenerator.getInventoryStock()) {
                if (!inventoryStock.getName().isEmpty() && inventoryStock.getName().toLowerCase().contains(searchQuery)) {
                    stocksTable.getItems().add(inventoryStock);
                }
            }
            totalStockNumber = String.valueOf(stocksTable.getItems().size());
            totalStockLabel.setText(totalStockNumber);
        }
    }

    // Reset the search and display all inventory items
    private static void resetSearch() {
        searchField.setText("");
        stocksTable.getItems().clear();
        stocksTable.getItems().addAll(UserGenerator.getInventoryStock());
        totalStockNumber = String.valueOf(stocksTable.getItems().size());
        totalStockLabel.setText(totalStockNumber);
    }

    // Update the low stock notification
    private static void updateLowStockNotification() {
        long lowStockCount = UserGenerator.getInventoryStock().stream().filter(med -> med.getQuantity() <= 10).count();
        if (lowStockCount > 0) {
            Stage alert = alterMessage("Attention: Low Stock Items Detected\nSome items have low stock. Please restock them soon.", "Low Stock Warning", "OK", null);
            alert.show();
        }
    }
}