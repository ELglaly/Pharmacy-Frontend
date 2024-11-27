package org.example.pharmacymanagmentfrontend.Model;

public class Patient extends Person {
    @Override
    public void setType() {
        this.Type=userTypes.Patient;
    }

}
