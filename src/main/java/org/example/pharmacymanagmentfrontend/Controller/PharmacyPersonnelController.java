package org.example.pharmacymanagmentfrontend.Controller;

import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import org.example.pharmacymanagmentfrontend.Model.Prescription;
import org.example.pharmacymanagmentfrontend.View.*;

public class PharmacyPersonnelController {


    public static VBox addPrescriptionView() {
        VBox prescriptionView = PrescriptionView.createPrescriptionView();
        resetTimeUp(prescriptionView);
        return prescriptionView;
    }

    public static VBox addPatientView() {
        VBox personView = AddPatientView.createAddPersonView();
        resetTimeUp(personView);
        return personView;
    }

    public static VBox updatePatientView() {
        VBox updatePersonView = UpdatePatientView.createUpdatePersonView();
        resetTimeUp(updatePersonView);
        return updatePersonView;
    }

    public static VBox showPrescriptionHistory() {
        return addPrescriptionView();
    }

    public static ScrollPane showInsuranceClaim(Prescription prescription) {
        ScrollPane insuranceView = InsuranceClaim.createInsuranceClaimView(prescription);
        resetTimeUp(insuranceView);
        return insuranceView;
    }

    public static ScrollPane showInsuranceInterface() {
        ScrollPane insuranceInterfaceView = InsuranceInterface.createInsuranceInterfaceView();
        resetTimeUp(insuranceInterfaceView);
        return insuranceInterfaceView;
    }

    public VBox showPatientHistoryView() {
        VBox showPatientHistory = PatientHistoryView.AddPatientHistoryView();
        resetTimeUp(showPatientHistory);
        return showPatientHistory;
    }

    public static void resetTimeUp(Node root) {
        root.addEventFilter(MouseEvent.MOUSE_MOVED, event -> PharmacyPersonnelDashboard.addTimeUp());
        root.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> PharmacyPersonnelDashboard.addTimeUp());
        root.addEventFilter(KeyEvent.KEY_PRESSED, event -> PharmacyPersonnelDashboard.addTimeUp());
        root.addEventFilter(KeyEvent.KEY_RELEASED, event -> PharmacyPersonnelDashboard.addTimeUp());
    }
}