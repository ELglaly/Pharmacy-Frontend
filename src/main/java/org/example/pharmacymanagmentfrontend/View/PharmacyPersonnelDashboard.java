// PharmacyPersonnelDashboard.java
package org.example.pharmacymanagmentfrontend.View;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.pharmacymanagmentfrontend.Controller.PharmacyPersonnelController;
import org.example.pharmacymanagmentfrontend.HelloApplication;
import org.example.pharmacymanagmentfrontend.Model.Patient;
import org.example.pharmacymanagmentfrontend.Model.Prescription;

import static org.example.pharmacymanagmentfrontend.HelloApplication.primaryScene;
import static org.example.pharmacymanagmentfrontend.HelloApplication.resetTimer;
import static org.example.pharmacymanagmentfrontend.View.ManagementDashboard.createStyledButton;

public class PharmacyPersonnelDashboard {

    private static BorderPane root;

    // Create the main dashboard for pharmacy personnel
    public static void createPharmacyPersonnelDashboard() {
        Stage primaryStage = setupPrimaryStage();
        root = new BorderPane();
        primaryScene = new Scene(root, 900, 600);
        addTimeUp();
        primaryStage.setTitle("Pharmacy Personnel Dashboard");
        primaryStage.setScene(primaryScene);
        primaryStage.show();
        VBox leftPanel = createLeftPanel();
        root.setLeft(leftPanel);
        root.setCenter(PharmacyPersonnelController.showPrescriptionHistory());
        addTimeUp();
    }

    // Setup the primary stage
    private static Stage setupPrimaryStage() {
        Stage primaryStage = new Stage();
        HelloApplication.primaryStage = primaryStage;
        return primaryStage;
    }

    // Add time-up event handlers
    public static void addTimeUp() {
        primaryScene.setOnMouseMoved(event -> resetTimer());
        primaryScene.setOnKeyPressed(event -> resetTimer());
    }

    // Create the left navigation panel
    private static VBox createLeftPanel() {
        VBox leftPanel = new VBox(20);
        leftPanel.setStyle("-fx-background-color: #f1f1f1; -fx-pref-width: 200; -fx-padding: 20;");
        addButtonsToPanel(leftPanel);
        leftPanel.setAlignment(Pos.CENTER);
        return leftPanel;
    }

    // Add buttons to the left panel
    private static void addButtonsToPanel(VBox leftPanel) {
        javafx.scene.control.Button updatePresceiptionButton = createStyledButton("Add Prescription", "#f9bf29", "#ff9933");
        javafx.scene.control.Button addPatientButton = createStyledButton("Add Patient", "#f9bf29", "#ff9933");
        javafx.scene.control.Button updatePatientButton = createStyledButton("Update Patient", "#f9bf29", "#ff9933");
        javafx.scene.control.Button showhistoryButton = createStyledButton("Show history", "#f9bf29", "#ff9933");
        javafx.scene.control.Button insuranceClaimButton = createStyledButton("Insurance Claim", "#f9bf29", "#ff9933");
        javafx.scene.control.Button insuranceinterfaceButton = createStyledButton("Insurance", "#f9bf29", "#ff9933");

        addEventHandlers(updatePresceiptionButton, addPatientButton, updatePatientButton, showhistoryButton, insuranceClaimButton, insuranceinterfaceButton);

        leftPanel.getChildren().addAll(updatePresceiptionButton, addPatientButton, insuranceClaimButton, insuranceinterfaceButton, updatePatientButton, showhistoryButton);
    }

    // Add event handlers to buttons
    private static void addEventHandlers(javafx.scene.control.Button updatePresceiptionButton, javafx.scene.control.Button addPatientButton, javafx.scene.control.Button updatePatientButton, javafx.scene.control.Button showhistoryButton, javafx.scene.control.Button insuranceClaimButton, javafx.scene.control.Button insuranceinterfaceButton) {
        updatePresceiptionButton.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> root.setCenter(PharmacyPersonnelController.addPrescriptionView()));
        addPatientButton.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> root.setCenter(PharmacyPersonnelController.addPatientView()));
        updatePatientButton.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> root.setCenter(PharmacyPersonnelController.updatePatientView()));
        showhistoryButton.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> root.setCenter(PharmacyPersonnelController.showPrescriptionHistory()));
        insuranceClaimButton.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
            root.setCenter(PharmacyPersonnelController.showInsuranceClaim(new Prescription
                    .Builder().setPatient(
                            new Patient.Builder().build()
                    )
                    .build()));
        });
        insuranceinterfaceButton.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> root.setCenter(PharmacyPersonnelController.showInsuranceInterface()));
    }
}