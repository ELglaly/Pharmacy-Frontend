package org.example.pharmacymanagmentfrontend;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.example.pharmacymanagmentfrontend.Model.UserGenerator;

import java.io.IOException;

import static org.example.pharmacymanagmentfrontend.View.SharedView.alterMessage;

public class HelloApplication extends Application {
    private static final double TIMEOUT_SECONDS = 15 * 60; // 1 minutes
    public static Timeline inactivityTimer;
    public static Stage primaryStage;
    public static Scene primaryScene;
    @Override
    public void start(Stage stage) throws IOException {
        UserGenerator database = new UserGenerator();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("view/login-view.fxml"));
         primaryScene = new Scene(fxmlLoader.load());
        setupInactivityTimer(stage);
        // Listen for user activity
        primaryScene.setOnMouseMoved(event -> resetTimer());
        primaryScene.setOnKeyPressed(event -> resetTimer());
        stage.setTitle("Pharmacy Management System!");
        stage.setScene(primaryScene);
        stage.show();
        resetTimer();
    }

    public static void main(String[] args) {
        launch();
    }

    private void setupInactivityTimer(Stage primaryStage) {
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
        Stage alterStage = alterMessage(
                "Your session has timed out due to inactivity.\nPlease log in again to continue.",
                "Session Expired",
                "Log In",
                () -> navigateToLoginPage() // Pass the navigation logic as a lambda
        );

        alterStage.show(); // Show the alert dialog
    }

    private void navigateToLoginPage() {
        try {
            // Close the current stage
            primaryStage.close();
            // Navigate to the login screen
            start(new Stage()); // `start` should be your application entry method
        } catch (Exception e) {
            System.err.println("Failed to navigate to the login page: " + e.getMessage());
        }
    }





}

