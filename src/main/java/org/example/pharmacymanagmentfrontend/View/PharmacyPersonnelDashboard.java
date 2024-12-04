
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
import org.example.pharmacymanagmentfrontend.Controller.PharmacyPersonnelController;
import org.example.pharmacymanagmentfrontend.HelloApplication;
import static org.example.pharmacymanagmentfrontend.HelloApplication.primaryScene;

import static org.example.pharmacymanagmentfrontend.HelloApplication.resetTimer;
import static org.example.pharmacymanagmentfrontend.View.ManagementDashboard.createStyledButton;

public class PharmacyPersonnelDashboard {
    
    private static javafx.scene.control.Button addPatientButton;
    private static javafx.scene.control.Button UpdatePatientButton;
    private static javafx.scene.control.Button UpdatePresceiptionButton;
    private static javafx.scene.control.Button showhistoryButton;
    private static Stage primaryStage;
    private  static BorderPane root;

    public static Scene createPharmacyPersonnelDashboard() {

        primaryStage = new Stage();
        HelloApplication.primaryStage = primaryStage;
        // Left Navigation Panel
        root = new BorderPane();
        // Set up the Scene and Stage
        primaryScene = new Scene(root, 900, 600);
        primaryScene.setOnMouseMoved(event -> resetTimer());
        primaryScene.setOnKeyPressed(event -> resetTimer());
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
        UpdatePresceiptionButton = createStyledButton("Add Prescription","#f9bf29","#ff9933");
        addPatientButton = createStyledButton("Add Patient","#f9bf29","#ff9933");
        UpdatePatientButton = createStyledButton("Update Patient","#f9bf29","#ff9933");
        showhistoryButton=createStyledButton("Show history", "#f9bf29", "#ff9933");

        UpdatePresceiptionButton.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
            root.setCenter(PharmacyPersonnelController.addPrescriptionView());
        });

        addPatientButton.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
            root.setCenter(PharmacyPersonnelController.addPatientView());
        });

        UpdatePatientButton.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
            root.setCenter(PharmacyPersonnelController.updatePatientView());
        });
        showhistoryButton.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
            root.setCenter(PharmacyPersonnelController.showPrescriptionHistroy());
        });

        // Add buttons to the panel
        leftPanel.getChildren().addAll(UpdatePresceiptionButton, addPatientButton, UpdatePatientButton,showhistoryButton);

        // Make sure buttons are centered
        leftPanel.setAlignment(Pos.CENTER);

        return leftPanel;
    }

    // Helper method to create styled buttons


    public static Stage getprimaryStage() {
        return primaryStage;
    }
}
