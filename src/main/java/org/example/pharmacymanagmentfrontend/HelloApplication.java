// HelloApplication.java
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

    private static final double TIMEOUT_SECONDS = 0.1 * 60; // 1 minute
    public static Timeline inactivityTimer;
    public static Stage primaryStage;
    public static Scene primaryScene;
    public static Boolean loginPage = true;
    private UserGenerator database;
    public static int lockOut = 0;

    @Override
    public void start(Stage stage) throws IOException {
        initializeDatabase();


        setupPrimaryStage(stage);
        setupInactivityTimer();
        resetTimer();
    }

    public static void main(String[] args) {
        launch();
    }

    // Initialize the database if not already initialized
    private void initializeDatabase() {
        if (database == null) {
            database = new UserGenerator();
        }
    }

    // Setup the primary stage with the login view
    private void setupPrimaryStage(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("view/login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Pharmacy Management System!");
        stage.setScene(scene);
        stage.show();
        loginPage = true;
    }

    // Setup the inactivity timer
    public void setupInactivityTimer() {
        inactivityTimer = new Timeline(new KeyFrame(Duration.seconds(TIMEOUT_SECONDS), event -> handleTimeout()));
        inactivityTimer.setCycleCount(1); // Run once
    }

    // Reset the inactivity timer
    public static void resetTimer() {
        inactivityTimer.stop();
        inactivityTimer.playFromStart(); // Restart the timer
    }

    // Handle the timeout event
    private void handleTimeout() {
        if (!loginPage) {
            Stage alterStage = alterMessage(
                "Your session has timed out due to inactivity.\nPlease log in again to continue.",
                "Session Expired",
                "Log In",
                this::navigateToLoginPage
            );
            alterStage.show();
        }
    }

    // Navigate to the login page
    private void navigateToLoginPage() {
        try {
            primaryStage.close();
            start(new Stage()); // Restart the application
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}