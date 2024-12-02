package org.example.pharmacymanagmentfrontend.Model;

public class Prescription {
    private Patient patient;
    private String drugs;
    private String dosage;
    private String duration;
    private String details;

    public Prescription(Patient patient, String drugs, String dosage, String duration, String details) {
        this.patient = patient;
        this.drugs = drugs;
        this.dosage = dosage;
        this.duration = duration;
        this.details = details;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public String getDrugs() {
        return drugs;
    }

    public void setDrugs(String drugs) {
        this.drugs = drugs;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    @Override
    public String toString() {
        return "Prescription{" +
                " drugs='" + drugs + "\n" +
                " dosage='" + dosage + "\n" +
                " duration='" + duration + "\n" +
                " details='" + details + "\n" +
                '}';
    }
}
