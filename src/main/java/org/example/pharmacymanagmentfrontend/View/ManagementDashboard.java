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
    private Button addPersonButton;
    private Button editPersonButton;
    private Button userLogsButton;
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
        //set the result for the button
        root.setCenter(ManagerController.UserLogsScreen());
    }

    // Create left navigation panel (buttons for switching between screens)
    private VBox createLeftPanel() {
        VBox leftPanel = new VBox(20);  // Reduced spacing to make buttons closer
        leftPanel.setStyle("-fx-background-color: #f1f1f1; -fx-pref-width: 200; -fx-padding: 20;");  // Lighter background with padding

        // Buttons with hover effect and styling
        userLogsButton = createStyledButton("User Logs","#f9bf29","#ff9933");
        addPersonButton = createStyledButton("Add Person","#f9bf29","#ff9933");
        editPersonButton = createStyledButton("Edit Person","#f9bf29","#ff9933");

        userLogsButton.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
            root.setCenter(ManagerController.UserLogsScreen());
        });

        addPersonButton.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
            root.setCenter(ManagerController.Addperson());
        });

        editPersonButton.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
            root.setCenter(ManagerController.UserLogsScreen());
        });

        // Add buttons to the panel
        leftPanel.getChildren().addAll(userLogsButton, addPersonButton, editPersonButton);

        // Make sure buttons are centered
        leftPanel.setAlignment(Pos.CENTER);

        return leftPanel;
    }

    // Helper method to create styled buttons
    public static Button createStyledButton(String text,String backgroundColor, String backgroundColorEdit) {
        Button button = new Button(text);
        button.setStyle(String.format(
                "-fx-background-color: %s; -fx-text-fill: white; -fx-font-size: 14px; " +
                        "-fx-padding: 10px 20px; -fx-background-radius: 25px; " +
                        "-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.3), 10, 0, 0, 2);",
                backgroundColor
        ));

        // Add hover effect for interactivity
        button.setOnMouseEntered(e -> button.setStyle(String.format(
                "-fx-background-color: %s; -fx-text-fill: white; -fx-font-size: 14px; " +
                        "-fx-padding: 10px 20px; -fx-background-radius: 25px; " +
                        "-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.3), 10, 0, 0, 2);",
                backgroundColorEdit
        )));
        button.setOnMouseExited(e -> button.setStyle(String.format(
                "-fx-background-color: %s; -fx-text-fill: white; -fx-font-size: 14px; " +
                        "-fx-padding: 10px 20px; -fx-background-radius: 25px; " +
                        "-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.3), 10, 0, 0, 2);",
                backgroundColor
        )));
        return button;
    }



    public void setUserLogs() {
        userLogs = new UserLogs();
    }
}
