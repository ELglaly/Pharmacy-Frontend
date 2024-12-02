package org.example.pharmacymanagmentfrontend.Model;

import java.util.ArrayList;
import java.util.List;

public class Patient extends Person {
    private ArrayList<Prescription> prescriptionsPerson=new ArrayList<>();
    private String prescriptionString="";
    @Override
    public void setType() {
        this.Type=userTypes.Patient;
    }

    public ArrayList<Prescription> getPrescriptions() {
        return prescriptionsPerson;
    }

    public void addPrescriptions(Prescription prescriptions) {
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
