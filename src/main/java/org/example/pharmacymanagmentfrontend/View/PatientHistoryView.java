package org.example.pharmacymanagmentfrontend.View;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.pharmacymanagmentfrontend.Model.Patient;
import org.example.pharmacymanagmentfrontend.Model.UserGenerator;

public class PatientHistoryView {
     public static VBox AddPatientHistoryView() {
         Stage dataStage = new Stage();
         dataStage.setTitle("Patient Data");

         VBox dataBox = new VBox(10);
         dataBox.setPadding(new Insets(20));
         dataBox.setStyle("-fx-background-color: #f8f8f8");

         Label dataLabel = new Label("Saved Patient Data:");
         dataLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

         TableView<Patient> tableView = new TableView<>();

// Username Column
         TableColumn<Patient, String> usernameColumn = new TableColumn<>("Username");
         usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));

         // Prescription Info Column
         TableColumn<Patient, String> prescriptionColumn = new TableColumn<>("Prescription Info");
         prescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("prescriptionString")); // Ensure this matches your Patient class property
         prescriptionColumn.setCellFactory(TextFieldTableCell.forTableColumn());

// Add Columns to TableView
         tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
         tableView.getColumns().addAll(usernameColumn, prescriptionColumn);
         tableView.getItems().addAll(UserGenerator.getPatients());

         dataBox.getChildren().addAll(dataLabel, tableView);

         return dataBox;
     }
}
