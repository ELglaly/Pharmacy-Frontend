// PatientHistoryView.java
package org.example.pharmacymanagmentfrontend.View;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.pharmacymanagmentfrontend.Model.Patient;
import org.example.pharmacymanagmentfrontend.Model.UserGenerator;

import java.util.Collection;
import java.util.List;

public class PatientHistoryView {

//    private static Collection<? extends Node> tableView;
//    private static int dataLabel = 0;
    // Create the main view for patient history
    public static VBox AddPatientHistoryView() {
        Stage dataStage = createDataStage();
        VBox dataBox = createDataBox();
        int dataLabel = 0;
        Collection<? extends Node> tableView = List.of();
        dataBox.getChildren().addAll(dataLabel, tableView);
        return dataBox;
    }

    // Create and configure the data stage
    private static Stage createDataStage() {
        Stage dataStage = new Stage();
        dataStage.setTitle("Patient Data");
        return dataStage;
    }

    // Create and configure the data box
    private static VBox createDataBox() {
        VBox dataBox = new VBox(10);
        dataBox.setPadding(new Insets(20));
        dataBox.setStyle("-fx-background-color: #f8f8f8");
        return dataBox;
    }

    // Create and configure the data label
    private static Label createDataLabel() {
        Label dataLabel = new Label("Saved Patient Data:");
        dataLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        return dataLabel;
    }

    // Create and configure the table view
    private static TableView<Patient> createTableView() {
        TableView<Patient> tableView = new TableView<>();
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableView.getColumns().addAll(createUsernameColumn(), createPrescriptionColumn());
        tableView.getItems().addAll(UserGenerator.getPatients());
        return tableView;
    }

    // Create and configure the username column
    private static TableColumn<Patient, String> createUsernameColumn() {
        TableColumn<Patient, String> usernameColumn = new TableColumn<>("Username");
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        return usernameColumn;
    }

    // Create and configure the prescription column
    private static TableColumn<Patient, String> createPrescriptionColumn() {
        TableColumn<Patient, String> prescriptionColumn = new TableColumn<>("Prescription Info");
        prescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("prescriptionString"));
        prescriptionColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        return prescriptionColumn;
    }
}