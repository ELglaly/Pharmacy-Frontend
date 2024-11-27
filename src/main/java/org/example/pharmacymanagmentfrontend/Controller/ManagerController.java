package org.example.pharmacymanagmentfrontend.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.example.pharmacymanagmentfrontend.HelloApplication;
import org.example.pharmacymanagmentfrontend.Model.Person;
import org.example.pharmacymanagmentfrontend.Model.PharmacyManager;
import org.example.pharmacymanagmentfrontend.Model.UserGenerator;
import org.example.pharmacymanagmentfrontend.Model.UserLogs;
import org.example.pharmacymanagmentfrontend.View.ManagementDashboard;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.Objects;


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

    public void loginAction(ActionEvent event) throws IOException {
//        String username = usernamelogin.getText();
//        String password = passwordlogin.getText();
//        Person user =UserGenerator.login(username,password);
//        if(user!=null){
//           switch (user.getType())
//           {
//               case PharmacyManager: {
                   Stage stage =  (Stage)loginbutton.getScene().getWindow();
                   stage.close();
                    ManagementDashboard managementDashboard = new ManagementDashboard();
                   // managementDashboard.getUserLogs();

//                   break;
//               }
//               case Patient:
//                   loginerrormessage.setText("Patient Does not have the eligibality to login");
//                   break;
//               case Pharmacist:
//                   break;
//               case Cashier:
//                   break;
//               case PharmacyTechnician:
//                   break;
//           }
//        }
//        else
//        {
//            loginerrormessage.setText("Invalid username or password");
//            passwordlogin.setText("");
//        }

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
