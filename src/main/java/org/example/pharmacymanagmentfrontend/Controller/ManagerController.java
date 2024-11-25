package org.example.pharmacymanagmentfrontend.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import org.example.pharmacymanagmentfrontend.Model.Person;
import org.example.pharmacymanagmentfrontend.Model.PharmacyManager;
import org.example.pharmacymanagmentfrontend.Model.UserGenerator;

public class ManagerController {

    @FXML
    TextField usernamelogin;
    @FXML
    TextField passwordlogin;
    @FXML
    Label loginerrormessage;
    public void loginAction(ActionEvent event) {
        String username = usernamelogin.getText();
        String password = passwordlogin.getText();
        Person user =UserGenerator.login(username,password);
        if(user!=null){
           switch (user.getType())
           {
               case PharmacyManager:
                   break;
               case Patient:
                   loginerrormessage.setText("Patient Does not have the eligibality to login");
                   break;
               case Pharmacist:
                   break;
               case Cashier:
                   break;
               case PharmacyTechnician:
                   break;
           }
        }
        else
        {
            loginerrormessage.setText("Invalid username or password");
            passwordlogin.setText("");
        }

    }
}
