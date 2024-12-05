package org.example.pharmacymanagmentfrontend.View;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.swing.*;
import java.awt.*;
import javafx.scene.control.Button;
import org.example.pharmacymanagmentfrontend.Controller.ManagerController;
import org.example.pharmacymanagmentfrontend.HelloApplication;
import static org.example.pharmacymanagmentfrontend.HelloApplication.primaryScene;

import static org.example.pharmacymanagmentfrontend.HelloApplication.resetTimer;

public class ManagementDashboard extends JFrame {

    private UserLogs userLogs;
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private Scene scene;
    private static Button addPersonButton;
    private static Button inventoryButton;
    private static Button userLogsButton;
    private static Button updatePersonButton;
    private static Stage primaryStage;
    private  static BorderPane root;

    public static Scene createManagementDashboard() {

        primaryStage = new Stage();
        HelloApplication.primaryStage = primaryStage;
        // Left Navigation Panel
        root = new BorderPane();
        // Set up the Scene and Stage
         primaryScene = new Scene(root, 900, 600);
        PharmacyPersonnelDashboard.addTimeUp();
        primaryStage.setTitle("Management Dashboard");
        primaryStage.setScene(primaryScene);
        primaryStage.show();
        // Left Panel in JavaFX (VBox layout)
        VBox leftPanel = createLeftPanel();
        // Add the left panel to the root layout
        root.setLeft(leftPanel);
        //set the result for the button
        root.setCenter(ManagerController.UserLogsScreen());
        return primaryScene;
    }

    // Create left navigation panel (buttons for switching between screens)
    private static VBox createLeftPanel() {
        VBox leftPanel = new VBox(20);  // Reduced spacing to make buttons closer
        leftPanel.setStyle("-fx-background-color: #f1f1f1; -fx-pref-width: 200; -fx-padding: 20;");  // Lighter background with padding

        // Buttons with hover effect and styling
        userLogsButton = createStyledButton("User Logs","#f9bf29","#ff9933");
        addPersonButton = createStyledButton("Add Person","#f9bf29","#ff9933");
        inventoryButton = createStyledButton("Inventory","#f9bf29","#ff9933");
        updatePersonButton=createStyledButton("Update Person", "#f9bf29", "#ff9933");

        userLogsButton.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
            root.setCenter(ManagerController.UserLogsScreen());
        });

        addPersonButton.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
            root.setCenter(ManagerController.Addperson());
        });

        inventoryButton.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
            root.setCenter(ManagerController.InventoryView());
        });
        updatePersonButton.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
            root.setCenter(ManagerController.updatePersonView());
        });

        // Add buttons to the panel
        leftPanel.getChildren().addAll(userLogsButton, addPersonButton, inventoryButton,updatePersonButton);

        // Make sure buttons are centered
        leftPanel.setAlignment(Pos.CENTER);

        return leftPanel;
    }

    // Helper method to create styled buttons
    public static Button createStyledButton(String text,String backgroundColor, String backgroundColorEdit) {
        Button button = new Button(text);
        button.setPrefWidth(200);
        button.setMinWidth(200);
        button.setMaxWidth(200);

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



    public static Stage getprimaryStage() {
        return primaryStage;
    }
}
