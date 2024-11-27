package org.example.pharmacymanagmentfrontend.Model;

public class Pharmacist extends Person {
    @Override
    public void setType() {
        this.Type=userTypes.Pharmacist;
    }
}
