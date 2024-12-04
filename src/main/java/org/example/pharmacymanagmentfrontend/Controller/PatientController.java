package org.example.pharmacymanagmentfrontend.Controller;

import javafx.scene.layout.VBox;
import org.example.pharmacymanagmentfrontend.Model.Prescription;

public class PatientController {


    public static void CheckoutView(Prescription prescription) {

        //VBox checkOutView = new VBox(10);
        org.example.pharmacymanagmentfrontend.View.CheckoutView.addCheckoutView(prescription);
    }
}
