package org.example.pharmacymanagmentfrontend.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.pharmacymanagmentfrontend.Model.Person;
import org.example.pharmacymanagmentfrontend.Model.UserGenerator;
import org.example.pharmacymanagmentfrontend.View.SharedView;

public class LoginController {

    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;

    private UserGenerator userGenerator;

    // No-argument constructor
    public LoginController() {
        // Initialize userGenerator or use dependency injection
        this.userGenerator = new UserGenerator();
    }

    @FXML
    public void loginAction(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();
        Person person = userGenerator.login(username, password);
        if (person != null) {
            navigateToDashboard();
        } else {
            showLoginError();
        }
    }

    private void navigateToDashboard() {
        // Logic to navigate to the dashboard
    }

    private void showLoginError() {
        Stage stage = SharedView.alterMessage("Invalid username or password.", "Login Failed", "Ok", null);
        stage.show();
    }
}