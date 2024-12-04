package org.example.pharmacymanagmentfrontend.Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Prescription {
    private Patient patient;
    private ArrayList<String> drugs=new ArrayList<>();
    private String dosage;
    private String duration;
    private String details;
    private float totalPrice;
    private Map<String, Map<Integer,Float>> drugAndQuantity = new HashMap<String, Map<Integer, Float>>();



    public Prescription(Patient patient, String drugsString, String dosage, String duration, String details) {
        this.patient = patient;
        String []drugTest =drugsString.trim().split("-");
        for (String drug : drugTest)
        {
          //  System.out.println(drug);
            drugs.add(drug.trim());
        }
        setDrugAndQuantity();
        this.dosage = dosage;
        this.duration = duration;
        this.details = details;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Map<String, Map<Integer,Float>> getDrugAndQuantity() {
        return drugAndQuantity;
    }

    public void setDrugAndQuantity() {
        for(String drug : drugs)
        {
            // Assuming `UserGenerator.getInventoryStock()` returns a Stream or Collection of inventory items
            for (Inventory inventory : UserGenerator.getInventoryStock() )
            {
                int nextQuantity=1;
                if(inventory.getName().equalsIgnoreCase(drug))
                {
                    float price = inventory.getPrice(); // Corrected method call to `getPrice`
                    if(drugAndQuantity.containsKey(inventory.getName()))
                    {
                        Map<Integer, Float> existingMap = drugAndQuantity.get(inventory.getName());

                        nextQuantity= existingMap.keySet().stream().max(Integer::compareTo).orElse(0) + 1;
                        //this.totalPrice -= (price*(nextQuantity-1));
                        existingMap.put(nextQuantity, price*nextQuantity);
                        // Put the updated map back into the main drugAndQuantity map
                        drugAndQuantity.put(inventory.getName(), existingMap);
                        System.out.println("Price "+price+"  Quantity "+nextQuantity);
                    }
                    else {
                        Map<Integer, Float> tempMap = new HashMap<>();
                        tempMap.putIfAbsent(1, price);
                        drugAndQuantity.put(inventory.getName(), tempMap);
                        System.out.println("Price "+price+"  Quantity "+nextQuantity);
                    }
                    this.totalPrice += (price*nextQuantity);
                    break;

                }

            }
        }
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
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
