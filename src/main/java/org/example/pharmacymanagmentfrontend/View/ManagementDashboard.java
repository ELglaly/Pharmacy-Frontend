package org.example.pharmacymanagmentfrontend.View;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.swing.*;
import java.awt.*;
import javafx.scene.control.Button;
import org.example.pharmacymanagmentfrontend.Controller.ManagerController;

public class ManagementDashboard extends JFrame {

    private UserLogs userLogs;
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private Scene scene;
    BorderPane root;

    public ManagementDashboard() {

        Stage primaryStage = new Stage();
        // Left Navigation Panel
        root = new BorderPane();

        // Set up the Scene and Stage
        Scene scene = new Scene(root, 900, 600);
        primaryStage.setTitle("Management Dashboard");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Left Panel in JavaFX (VBox layout)
        VBox leftPanel = createLeftPanel();


        // Add the left panel to the root layout
        root.setLeft(leftPanel);
        HBox rightPanel = new HBox(20);
        rightPanel.getChildren().add(ManagerController.UserLogsScreen());
        root.setCenter(ManagerController.UserLogsScreen());
    }

    // Create left navigation panel (buttons for switching between screens)
    private VBox createLeftPanel() {
        VBox leftPanel = new VBox(20);  // Reduced spacing to make buttons closer
        leftPanel.setStyle("-fx-background-color: #f1f1f1; -fx-pref-width: 200; -fx-padding: 20;");  // Lighter background with padding

        // Buttons with hover effect and styling
        Button userLogsButton = createStyledButton("User Logs");
        Button addPersonButton = createStyledButton("Add Person");
        Button settingsButton = createStyledButton("Settings");

        userLogsButton.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {

        });




        // Add buttons to the panel
        leftPanel.getChildren().addAll(userLogsButton, addPersonButton, settingsButton);

        // Make sure buttons are centered
        leftPanel.setAlignment(Pos.CENTER);

        return leftPanel;
    }

    // Helper method to create styled buttons
    private Button createStyledButton(String text) {
        Button button = new Button(text);
        button.setStyle("-fx-background-color: #f9bf29; -fx-text-fill: white; -fx-font-size: 14px; " +
                "-fx-padding: 10px 20px; -fx-background-radius: 25px; -fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.3), 10, 0, 0, 2);");

        // Add hover effect for interactivity
        button.setOnMouseEntered(e -> button.setStyle("-fx-background-color: #ff9933; -fx-text-fill: white; -fx-font-size: 14px; " +
                "-fx-padding: 10px 20px; -fx-background-radius: 25px; -fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.3), 10, 0, 0, 2);"));
        button.setOnMouseExited(e -> button.setStyle("-fx-background-color: #f9bf29; -fx-text-fill: white; -fx-font-size: 14px; " +
                "-fx-padding: 10px 20px; -fx-background-radius: 25px; -fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.3), 10, 0, 0, 2);"));
        return button;
    }



    public void setUserLogs() {
        userLogs = new UserLogs();
    }
}
