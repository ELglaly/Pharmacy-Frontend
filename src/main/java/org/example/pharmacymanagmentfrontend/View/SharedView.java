package org.example.pharmacymanagmentfrontend.View;


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

import java.util.function.Function;

import static org.example.pharmacymanagmentfrontend.HelloApplication.*;


public class SharedView extends javax.swing.JFrame {

    public static Stage alterMessage(String message, String title, String buttonText, Runnable function)
    {
        inactivityTimer.stop();
        Stage dialogStage = new Stage();
        dialogStage.initModality(Modality.APPLICATION_MODAL); // Block interaction with other windows
        dialogStage.initStyle(StageStyle.UTILITY); // Minimal window style
        dialogStage.setTitle(title);

        dialogStage.setOnCloseRequest(event -> event.consume());
        // Create the content of the dialog
        VBox content = new VBox(10);
        content.setAlignment(Pos.CENTER);
        content.setPadding(new Insets(20));

        // Add a title label
        Label titleLabel = new Label(title);
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        titleLabel.setTextFill(Color.DARKRED);

        // Add a message label
        Label messageLabel = new Label(message);
        messageLabel.setFont(Font.font("Arial", 14));
        messageLabel.setTextFill(Color.DARKGRAY);
        messageLabel.setWrapText(true);
        messageLabel.setTextAlignment(TextAlignment.CENTER);

        // Add an "OK" button
        Button okButton = ManagementDashboard.createStyledButton(buttonText,"#27ae60","#27ae60");
        okButton.setOnAction(event -> {
            dialogStage.close();
            if(function!=null)// Close the dialog
            {
                inactivityTimer.stop();
                function.run();
                dialogStage.close();

            }
        });

        // Assemble the content
        content.getChildren().addAll( titleLabel, messageLabel, okButton);

        // Set the Scene and show the dialog
        primaryScene = new Scene(content);
        primaryScene.setOnMouseMoved(event -> resetTimer());
        primaryScene.setOnKeyPressed(event -> resetTimer());
        dialogStage.setScene(primaryScene);
        dialogStage.centerOnScreen();
        return dialogStage;
    }
}
