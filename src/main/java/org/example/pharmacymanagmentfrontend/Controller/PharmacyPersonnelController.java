package org.example.pharmacymanagmentfrontend.Controller;

import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import org.example.pharmacymanagmentfrontend.Model.Prescription;
import org.example.pharmacymanagmentfrontend.View.PharmacyPersonnelDashboard;

import javax.swing.*;

public class PharmacyPersonnelController {


    public static VBox addPrescriptionView()
    {
        VBox prescriptionView = org.example.pharmacymanagmentfrontend.View.PrescriptionView.createPrescriptionView();
        resetTimeUp(prescriptionView);
        return prescriptionView;
    }

    public static VBox addPatientView()
    {
        VBox personView = org.example.pharmacymanagmentfrontend.View.AddPatientView.createAddPersonView();
        resetTimeUp(personView);
        return personView;
    }

    public static VBox updatePatientView()
    {
        VBox updatePersonView = org.example.pharmacymanagmentfrontend.View.UpdatePatientView.createUpdatePersonView();
        resetTimeUp(updatePersonView);
        return updatePersonView;
    }

    public static VBox showPrescriptionHistroy()
    {
        VBox prescriptionView = org.example.pharmacymanagmentfrontend.View.PrescriptionView.createPrescriptionView();
        resetTimeUp(prescriptionView);
        return prescriptionView;
    }

    public static ScrollPane showInsurancClaim(Prescription prescription) {
        ScrollPane insuranceView = org.example.pharmacymanagmentfrontend.View.InsuranceClaim.createInsuranceClaimView(prescription);
        resetTimeUp(insuranceView);
        return insuranceView;
    }

    public static ScrollPane showInsuranceInterface() {
        ScrollPane insuranceInterfaceView = org.example.pharmacymanagmentfrontend.View.InsuranceInterface.createInsuranceInterfaceView();
        resetTimeUp(insuranceInterfaceView);
        return insuranceInterfaceView;
    }
    public static VBox showPatientHistroyVIew()
    {
        VBox  showPatientHistroy= org.example.pharmacymanagmentfrontend.View.PatientHistoryView.AddPatientHistoryView();
        resetTimeUp(showPatientHistroy);
        return showPatientHistroy;
    }

    public static void resetTimeUp(Node root) {

        root.addEventFilter(MouseEvent.MOUSE_MOVED, event -> {
            PharmacyPersonnelDashboard.addTimeUp();
        });

        root.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
            PharmacyPersonnelDashboard.addTimeUp();
        });

        root.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            PharmacyPersonnelDashboard.addTimeUp();
        });

        root.addEventFilter(KeyEvent.KEY_RELEASED, event -> {
            PharmacyPersonnelDashboard.addTimeUp();
        });


    }
}
