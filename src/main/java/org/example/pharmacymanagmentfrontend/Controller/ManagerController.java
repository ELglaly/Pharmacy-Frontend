package org.example.pharmacymanagmentfrontend.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import org.example.pharmacymanagmentfrontend.Model.UserGenerator;
import org.example.pharmacymanagmentfrontend.Model.UserLogs;

public class ManagerController {

    public static VBox updatePersonView() {
        VBox AddpersonPanel = new VBox(10);
        AddpersonPanel.getChildren().add(org.example.pharmacymanagmentfrontend.View.UpdatePerson.createUpdatePersonView());
        return AddpersonPanel;
    }

    public static VBox UserLogsScreen() {
        // Create an instance of the UserLogs class
        //org.example.pharmacymanagmentfrontend.View.UserLogs userLogsFrame = new org.example.pharmacymanagmentfrontend.View.UserLogs();
        // Extract the content pane of the UserLogs frame
        VBox userLogsPanel = new VBox(10);
        userLogsPanel.getChildren().add(org.example.pharmacymanagmentfrontend.View.UserLogs.AddUserLogsView());

        return userLogsPanel;
    }


    public static VBox Addperson() {

        VBox AddpersonPanel = new VBox(10);
        AddpersonPanel.getChildren().add(org.example.pharmacymanagmentfrontend.View.AddPerson.createAddPersonView());

        return AddpersonPanel;
    }


    public static VBox InventoryView() {

        VBox InventoryViewPanel = new VBox(10);
        InventoryViewPanel.getChildren().add(org.example.pharmacymanagmentfrontend.View.InventoryView.createInventoryView());

        return InventoryViewPanel;
    }



    @FXML
    private TextField searchField;
    @FXML
    private TableView<UserLogs> logsTable;
    @FXML
    private Label totalLogsLabel;
    @FXML
    private ObservableList<UserLogs> userLogsData = FXCollections.observableArrayList();

    public void displayUserLogs()
    {
        userLogsData.addAll(UserGenerator.getLoginTracker());
        logsTable.setItems(userLogsData);
        updateTotalLogs();
    }
    private void updateTotalLogs() {
        totalLogsLabel.setText(String.valueOf(logsTable.getItems().size()));
    }

    private void resetLogs() {
        searchField.clear();
        logsTable.setItems(userLogsData); // Reset to all data
        updateTotalLogs();
    }

    private void searchLogs() {
        String searchText = searchField.getText().trim().toLowerCase();

        if (searchText.isEmpty()) {
            logsTable.setItems(userLogsData); // Reset to all data
        } else {
            ObservableList<UserLogs> filteredLogs = FXCollections.observableArrayList();
            for (UserLogs log : userLogsData) {
                if (log.getUsername().toLowerCase().contains(searchText)) {
                    filteredLogs.add(log);
                }
            }
            logsTable.setItems(filteredLogs);
        }

        // Update the total logs label after filtering
        updateTotalLogs();
    }



}
