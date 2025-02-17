// SharedView.java
package org.example.pharmacymanagmentfrontend.View;

import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import static org.example.pharmacymanagmentfrontend.HelloApplication.inactivityTimer;
import static org.example.pharmacymanagmentfrontend.HelloApplication.primaryScene;
import static org.example.pharmacymanagmentfrontend.View.ManagementDashboard.createStyledButton;
import static org.example.pharmacymanagmentfrontend.View.PharmacyPersonnelDashboard.addTimeUp;

public class SharedView extends javax.swing.JFrame {

    // Create and display an alert message dialog
    public static Stage alterMessage(String message, String title, String buttonText, Runnable function) {
        inactivityTimer.stop();
        Stage dialogStage = createDialogStage(title);
        VBox content = createDialogContent(message, title, buttonText, function, dialogStage);
        setupAndShowDialog(dialogStage, content);
        return dialogStage;
    }

    // Create and configure the dialog stage
    private static Stage createDialogStage(String title) {
        Stage dialogStage = new Stage();
        dialogStage.initModality(Modality.APPLICATION_MODAL); // Block interaction with other windows
        dialogStage.initStyle(StageStyle.UTILITY); // Minimal window style
        dialogStage.setTitle(title);
        dialogStage.setOnCloseRequest(Event::consume); // Prevent closing the dialog
        return dialogStage;
    }

    // Create and configure the dialog content
    private static VBox createDialogContent(String message, String title, String buttonText, Runnable function, Stage dialogStage) {
        VBox content = new VBox(10);
        content.setAlignment(Pos.CENTER);
        content.setPadding(new Insets(20));
        Label titleLabel = createTitleLabel(title);
        Label messageLabel = createMessageLabel(message);
        Button okButton = createOkButton(buttonText, function, dialogStage);
        content.getChildren().addAll(titleLabel, messageLabel, okButton);
        return content;
    }

    // Create and configure the title label
    private static Label createTitleLabel(String title) {
        Label titleLabel = new Label(title);
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        titleLabel.setTextFill(Color.DARKRED);
        return titleLabel;
    }

    // Create and configure the message label
    private static Label createMessageLabel(String message) {
        Label messageLabel = new Label(message);
        messageLabel.setFont(Font.font("Arial", 14));
        messageLabel.setTextFill(Color.DARKGRAY);
        messageLabel.setWrapText(true);
        messageLabel.setTextAlignment(TextAlignment.CENTER);
        return messageLabel;
    }

    // Create and configure the OK button
    private static Button createOkButton(String buttonText, Runnable function, Stage dialogStage) {
        Button okButton = createStyledButton(buttonText, "#27ae60", "#27ae60");
        okButton.setOnAction(event -> handleOkButtonClick(function, dialogStage));
        return okButton;
    }

    // Handle the OK button click event
    private static void handleOkButtonClick(Runnable function, Stage dialogStage) {
        dialogStage.close();
        if (function != null) {
            inactivityTimer.stop();
            function.run();
            dialogStage.close();
        }
    }

    // Setup and show the dialog
    private static void setupAndShowDialog(Stage dialogStage, VBox content) {
        primaryScene = new Scene(content);
        addTimeUp();
        dialogStage.setScene(primaryScene);
        dialogStage.centerOnScreen();
        dialogStage.show();
    }
}