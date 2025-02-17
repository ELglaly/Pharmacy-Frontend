package org.example.pharmacymanagmentfrontend.Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Prescription {
    private Patient patient;
    private String meditation;
    private List<String> drugs;
    private String dosage;
    private String duration;
    private String details;
    private float totalPrice;
    private Map<String, Map<Integer, Float>> drugAndQuantity;

    // Private constructor to initialize Prescription object using Builder
    public Prescription(Builder builder) {
        this.patient = builder.patient;
        this.drugs = builder.drugs;
        this.dosage = builder.dosage;
        this.duration = builder.duration;
        this.details = builder.details;
        this.totalPrice = builder.totalPrice;
        this.drugAndQuantity = builder.drugAndQuantity;
        this.meditation =builder.medication;
    }

    // Static nested Builder class
    public static class Builder {
        private Patient patient;
        private List<String> drugs = new ArrayList<>();
        private String dosage;
        private String duration;
        private String details;
        private float totalPrice;
        private String medication;
        private Map<String, Map<Integer, Float>> drugAndQuantity = new HashMap<>();


        // Method to set patient
        public Builder setPatient(Patient patient) {
            this.patient = patient;
            return this;
        }

        // Method to set drugs from a string
        public Builder setDrugs(String drugsString) {
            String[] drugArray = drugsString.trim().split("-");
            for (String drug : drugArray) {
                this.drugs.add(drug.trim());
            }
            return this;
        }

        // Method to set dosage
        public Builder setDosage(String dosage) {
            this.dosage = dosage;
            return this;
        }

        // Method to set duration
        public Builder setDuration(String duration) {
            this.duration = duration;
            return this;
        }

        // Method to set additional details
        public Builder setDetails(String details) {
            this.details = details;
            return this;
        }

        // Method to set drug and quantity map and calculate total price
        public Builder setDrugAndQuantity() {
            for (String drug : drugs) {
                Inventory inventory = UserGenerator.findMedicine(drug);
                if (inventory != null) {
                    float price = inventory.getPrice();
                    int nextQuantity = 1;
                    if (drugAndQuantity.containsKey(inventory.getName())) {
                        Map<Integer, Float> existingMap = drugAndQuantity.get(inventory.getName());
                        nextQuantity = existingMap.keySet().stream().max(Integer::compareTo).orElse(0) + 1;
                        existingMap.put(nextQuantity, price * nextQuantity);
                    } else {
                        Map<Integer, Float> tempMap = new HashMap<>();
                        tempMap.put(1, price);
                        drugAndQuantity.put(inventory.getName(), tempMap);
                    }
                    totalPrice += price * nextQuantity;
                }
            }
            return this;
        }
        public Builder setMedication(String medication) {
            this.medication = medication;
            return this;
        }

        // Method to build and return Prescription object
        public Prescription build() {
            return new Prescription(this);
        }
    }

    // Getters for Prescription properties
    public Patient getPatient() {
        return patient;
    }

    public List<String> getDrugs() {
        return drugs;
    }

    public String getDosage() {
        return dosage;
    }

    public String getDuration() {
        return duration;
    }


    public String getDetails() {
        return details;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public void setMeditation(String meditation) {
        this.meditation = meditation;
    }

    public void setDrugs(List<String> drugs) {
        this.drugs = drugs;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setDrugAndQuantity(Map<String, Map<Integer, Float>> drugAndQuantity) {
        this.drugAndQuantity = drugAndQuantity;
    }

    public Map<String, Map<Integer, Float>> getDrugAndQuantity() {
        return drugAndQuantity;
    }

    // Override toString method to provide a string representation of the prescription
    @Override
    public String toString() {
        return "Prescription{" +
                "drugs=" + drugs +
                ", dosage='" + dosage + '\'' +
                ", duration='" + duration + '\'' +
                ", details='" + details + '\'' +
                '}';
    }
}