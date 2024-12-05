package org.example.pharmacymanagmentfrontend;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.example.pharmacymanagmentfrontend.Controller.ManagerController;
import org.example.pharmacymanagmentfrontend.Model.UserGenerator;

import java.io.IOException;

import static org.example.pharmacymanagmentfrontend.View.SharedView.alterMessage;

public class HelloApplication extends Application {

    private static final double TIMEOUT_SECONDS = .05 * 60; // 1 minutes
    public static Timeline inactivityTimer;
    public static Stage primaryStage;
    public static Scene primaryScene;
    public static Boolean loginpage=true;
    UserGenerator database;


    @Override
    public void start(Stage stage) throws IOException {
        if(database==null) {
            database = new UserGenerator();
        }
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("view/login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        setupInactivityTimer();
        resetTimer();
        // Listen for user activity
        stage.setTitle("Pharmacy Management System!");
        stage.setScene(scene);
        stage.show();
        loginpage=true;
        resetTimer();
    }

    public static void main(String[] args) {
        launch();
    }

    public void setupInactivityTimer() {
        inactivityTimer = new Timeline(new KeyFrame(Duration.seconds(TIMEOUT_SECONDS), event -> {
            handleTimeout();
        }));
        inactivityTimer.setCycleCount(1); // Run once
    }

    public static void resetTimer() {
        inactivityTimer.stop();
        inactivityTimer.playFromStart(); // Restart the timer
    }

    private void handleTimeout() {
       if (!loginpage) {
    Stage alterStage = alterMessage(
            "Your session has timed out due to inactivity.\nPlease log in again to continue.",
            "Session Expired",
            "Log In",
            () -> navigateToLoginPage() // Pass the navigation logic as a lambda
    );

    alterStage.show();
}

        // Show the alert dialog
    }

    private void navigateToLoginPage() {

            try {
                primaryStage.close();
                // Close the current stage
                start(new Stage()); // `start` should be your application entry method
                // Navigate to the login screen
            } catch (IOException e) {
                throw new RuntimeException(e);
            }


    }
}

