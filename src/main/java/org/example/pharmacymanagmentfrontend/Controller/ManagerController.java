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
import javafx.stage.Stage;
import org.example.pharmacymanagmentfrontend.Model.Person;
import org.example.pharmacymanagmentfrontend.Model.UserGenerator;
import org.example.pharmacymanagmentfrontend.Model.UserLogs;
import org.example.pharmacymanagmentfrontend.View.InsuranceClaim;
import org.example.pharmacymanagmentfrontend.View.ManagementDashboard;
import org.example.pharmacymanagmentfrontend.View.PharmacyPersonnelDashboard;
import org.example.pharmacymanagmentfrontend.View.PrescriptionView;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Date;

import static org.example.pharmacymanagmentfrontend.View.SharedView.alterMessage;


public class ManagerController {

    @FXML
    TextField usernamelogin;
    @FXML
    TextField passwordlogin;
    @FXML
    Label loginerrormessage;
    @FXML
    Button loginbutton;
  //  private Stage stage1;
  //  private Scene scene;
    private Parent root;

    public static VBox updatePersonView() {
        VBox AddpersonPanel = new VBox(10);
        AddpersonPanel.getChildren().add(org.example.pharmacymanagmentfrontend.View.UpdatePerson.createUpdatePersonView());
        return AddpersonPanel;
    }


    public void loginAction(ActionEvent event) throws IOException {
        String username = usernamelogin.getText();
        String password = passwordlogin.getText();
        Person user =UserGenerator.login(username,password);
        if(user!=null){
            Stage stage =  (Stage)loginbutton.getScene().getWindow();
           switch (user.getType())
           {

               case PharmacyManager: {
                   stage.close();
                     ManagementDashboard.createManagementDashboard();
                    break;
               }
               case Patient:
                   Stage stage1= alterMessage("Patient Does not have the eligibility to login","Error","OK",null);
                   stage1.show();
                   break;
               case Pharmacist:
                   stage.close();
                   PharmacyPersonnelDashboard.createPharmacyPersonnelDashboard();
                   break;
               case Cashier:
                   stage.close();
                   PharmacyPersonnelDashboard.createPharmacyPersonnelDashboard();
                   stage.close();
                   break;
               case PharmacyTechnician:
                   stage.close();
                   PharmacyPersonnelDashboard.createPharmacyPersonnelDashboard();
                   break;
           }
        }
        else
        {
            loginerrormessage.setText("Invalid username or password");
            passwordlogin.setText("");
        }

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
    private Button searchButton;
    @FXML
    private Button resetButton;
    @FXML
    private TableView<UserLogs> logsTable;
    @FXML
    private TableColumn<UserLogs, Date> loginTimeColumn;
    @FXML
    private TableColumn<UserLogs, Boolean> successfulLoginColumn;
    @FXML
    private TableColumn<UserLogs, String> usernameColumn;
    @FXML
    private TableColumn<UserLogs, String> userTypeColumn;
    @FXML
    private Label totalLogsLabel;
    @FXML
    private ObservableList<UserLogs> userLogsData = FXCollections.observableArrayList();

    public void displayUserLogs()
    {
        for(UserLogs userLogs: UserGenerator.getLoginTracker())
        {
            userLogsData.add(userLogs);

        }
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
