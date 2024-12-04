package org.example.pharmacymanagmentfrontend.Controller;

import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import org.example.pharmacymanagmentfrontend.Model.Prescription;

import javax.swing.*;

public class PharmacyPersonnelController {


    public static VBox addPrescriptionView()
    {
        VBox prescriptionView = org.example.pharmacymanagmentfrontend.View.PrescriptionView.createPrescriptionView();
        return prescriptionView;
    }

    public static VBox addPatientView()
    {
        VBox prescriptionView = org.example.pharmacymanagmentfrontend.View.AddPatientView.createAddPersonView();
        return prescriptionView;
    }

    public static VBox updatePatientView()
    {
        VBox updatePersonView = org.example.pharmacymanagmentfrontend.View.UpdatePatientView.createUpdatePersonView();
        return updatePersonView;
    }

    public static VBox showPrescriptionHistroy()
    {
        VBox prescriptionView = org.example.pharmacymanagmentfrontend.View.PrescriptionView.createPrescriptionView();
        return prescriptionView;
    }

    public static ScrollPane showInsurancClaim(Prescription prescription) {
        ScrollPane insuranceView = org.example.pharmacymanagmentfrontend.View.InsuranceClaim.createInsuranceClaimView(prescription);
        return insuranceView;
    }

    public static ScrollPane showInsuranceInterface() {
        ScrollPane insuranceInterfaceView = org.example.pharmacymanagmentfrontend.View.InsuranceInterface.createInsuranceInterfaceView();
        return insuranceInterfaceView;
    }
    public static VBox showPatientHistroyVIew()
    {
        VBox  showPatientHistroy= org.example.pharmacymanagmentfrontend.View.PatientHistoryView.AddPatientHistoryView();
        return showPatientHistroy;
    }
}
