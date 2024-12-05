package org.example.pharmacymanagmentfrontend.Model;

import java.util.ArrayList;
import java.util.List;

public class Patient extends Person {
    private ArrayList<Prescription> prescriptionsPerson=new ArrayList<>();
    private String prescriptionString="";
    private Boolean emptyPrescription=true;
    @Override
    public void setType() {
        this.Type=userTypes.Patient;
    }

    public Boolean getPrescriptionsstatus() {
        return emptyPrescription;
    }

    public void addPrescriptions(Prescription prescriptions) {
        emptyPrescription=false;
        prescriptionsPerson.add(prescriptions);
        prescriptionString=getPrescriptionString();
    }
    public String getPrescriptionString() {
        prescriptionString="";
        for(int i=0; i<prescriptionsPerson.size();i++)
        {
            prescriptionString+=prescriptionsPerson.get(i).toString()+"\n";
        }
        return prescriptionString;
    }
}
