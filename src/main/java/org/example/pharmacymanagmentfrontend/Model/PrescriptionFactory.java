// PrescriptionFactory.java
package org.example.pharmacymanagmentfrontend.Model;

import java.util.Random;

public class PrescriptionFactory {
    private static final String[] MEDICATIONS = {
            "Paracetamol", "Ibuprofen", "Amoxicillin", "Cough Syrup", "Vitamin C",
            "Aspirin", "Antibiotics", "Metformin", "Insulin", "Morphine"
    };
    private static final String[] DOSAGES = {"250mg", "500mg", "1000mg", "10ml", "20ml"};
    private static final String[] DURATIONS = {"5 days", "7 days", "10 days", "2 weeks", "1 month"};
    private static final String[] DETAILS = {
            "Take after meals", "Take before bed", "Avoid alcohol", "Consult doctor if symptoms persist", "Store in a cool, dry place"
    };

    public static Prescription createRandomPrescription() {
        Random random = new Random();
        String medication = MEDICATIONS[random.nextInt(MEDICATIONS.length)];
        String dosage = DOSAGES[random.nextInt(DOSAGES.length)];
        String duration = DURATIONS[random.nextInt(DURATIONS.length)];
        String details = DETAILS[random.nextInt(DETAILS.length)];
        return new Prescription.Builder()
                .setDrugs(medication)
                .setDosage(dosage)
                .setDuration(duration)
                .setDetails(details)
                .setMedication(medication)
                .build();
    }
}