package org.example.pharmacymanagmentfrontend.Model;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.pharmacymanagmentfrontend.HelloApplication;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.*;

import static org.example.pharmacymanagmentfrontend.View.SharedView.alterMessage;

public class UserGenerator {
    private static List<PharmacyManager> pharmacyManagers = new ArrayList<>();
    private static List<Pharmacist> pharmacists = new ArrayList<>();
    private static List<PharmacyTechnician> pharmacyTechnicians = new ArrayList<>();
    private static List<Cashier> cashiers = new ArrayList<>();
    private static List<Patient> patients = new ArrayList<>();
    private static List<UserLogs> loginTracker = new ArrayList<>();
    public static List<Inventory> inventoryStock = new ArrayList<>();

    private static String[] managerNames = {"Sherif Shawashen"};
    private static String[] pharmacistNames = {"Emma Harris", "Liam Moore", "Ava Taylor", "Noah Anderson", "Isabella Clark"};
    private static String[] technicianNames = {"Mason White", "Mia Walker", "Ethan Hall", "Amelia Lewis", "Lucas Allen"};
    private static String[] cashierNames = {"Benjamin Scott", "Charlotte Young", "Elijah Adams", "Harper King", "Logan Wright"};
    private static String[] patientNames = {"Henry Hill", "Emily Carter", "Jackson Baker", "Abigail Green", "Sebastian Nelson"};

    public UserGenerator() {

        // Create Pharmacy Managers
        for (int i = 0; i < managerNames.length; i++) {
            PharmacyManager manager = new PharmacyManager();
            manager.setName(managerNames[i]);
            manager.setUsername("manager" + (i + 1));
            manager.setPassword("password" + (i + 1));
            manager.setEmail(manager.getUsername() + "@gmail.com");
            manager.setPhone("555-123-" + (1000 + i));
            manager.setLicenseNumber("PM" + (10000 + i));
            manager.setAddress("Manager Address " + (i + 1));
            manager.setBirthDate(new Date(1980 + i, 5, 15));
            manager.setType();// Example birth date
            pharmacyManagers.add(manager);
        }

        // Create Pharmacists
        for (int i = 0; i < pharmacistNames.length; i++) {
            Pharmacist pharmacist = new Pharmacist();
            pharmacist.setName(pharmacistNames[i]);
            pharmacist.setUsername("pharmacist" + (i + 1));
            pharmacist.setPassword("password" + (i + 1));
            pharmacist.setEmail(pharmacist.getUsername() + "@pharmacy.com");
            pharmacist.setPhone("555-456-" + (1000 + i));
            pharmacist.setLicenseNumber("PH" + (20000 + i));
            pharmacist.setAddress("Pharmacist Address " + (i + 1));
            pharmacist.setBirthDate(new Date(1990 + i, 7, 20));
            pharmacist.setType();// Example birth date
            pharmacists.add(pharmacist);
        }

        // Create Pharmacy Technicians
        for (int i = 0; i < technicianNames.length; i++) {
            PharmacyTechnician technician = new PharmacyTechnician();
            technician.setName(technicianNames[i]);
            technician.setUsername("technician" + (i + 1));
            technician.setPassword("password" + (i + 1));
            technician.setEmail(technician.getUsername() + "@pharmacy.com");
            technician.setPhone("555-789-" + (1000 + i));
            technician.setLicenseNumber("PT" + (30000 + i));
            technician.setAddress("Technician Address " + (i + 1));
            technician.setBirthDate(new Date(1995 + i, 3, 10));
            technician.setType();// Example birth date
            pharmacyTechnicians.add(technician);
        }

        // Create Cashiers
        for (int i = 0; i < cashierNames.length; i++) {
            Cashier cashier = new Cashier();
            cashier.setName(cashierNames[i]);
            cashier.setUsername("cashier" + (i + 1));
            cashier.setPassword("password" + (i + 1));
            cashier.setEmail(cashier.getUsername() + "@pharmacy.com");
            cashier.setPhone("555-321-" + (1000 + i));
            cashier.setAddress("Cashier Address " + (i + 1));
            cashier.setBirthDate(new Date(2000 + i, 11, 5)); // Example birth date
            cashier.setType();
            cashiers.add(cashier);
        }

        // Create Patients
        for (int i = 0; i < patientNames.length; i++) {
            Patient patient = new Patient();
            patient.setName(patientNames[i]);
            patient.setUsername("patient" + (i + 1));
            patient.setPassword("password" + (i + 1));
            patient.setEmail(patient.getUsername() + "@pharmacy.com");
            patient.setPhone("555-654-" + (1000 + i));
            patient.setAddress("Patient Address " + (i + 1));
            patient.setBirthDate(new Date(2005 + i, 6, 25));
            patient.setType();// Example birth date
            patients.add(patient);
        }

        generateRandomInventory();
    }

    public static void updateUser(Person person, String name, String email, String phone, String licenseNumber, String address, LocalDate birthDate, String userType, Date birthDateAsDate) {
        person.setName(name);
        person.setEmail(email);
        person.setPhone(phone);
        person.setLicenseNumber(licenseNumber);
        person.setAddress(address);
        person.setType(Person.userTypes.valueOf(userType));
        person.setBirthDate(birthDateAsDate);
    }

    // Getters for the lists
    public List<PharmacyManager> getPharmacyManagers() {
        return pharmacyManagers;
    }

    public List<Pharmacist> getPharmacists() {
        return pharmacists;
    }

    public List<PharmacyTechnician> getPharmacyTechnicians() {
        return pharmacyTechnicians;
    }

    public List<Cashier> getCashiers() {
        return cashiers;
    }

    public static List<Patient> getPatients() {
        return patients;
    }

    public static void incrementAttemptedLogins(Date loginTime, Person person) {
        UserLogs userLogs=new UserLogs(loginTime,true,person,person.getUsername());
        loginTracker.add(userLogs);
    }
    public static void incrementFailedAttempts(Date loginTime, String username) {
        UserLogs userLogs=new UserLogs(loginTime,false,null,username);
        loginTracker.add(userLogs);
    }

    public static List<UserLogs> getLoginTracker() {
        return loginTracker;
    }

    public static List<Inventory> getInventoryStock() {
        return inventoryStock;
    }


    public static Person login(String username, String password) {
        // Check Pharmacy Managers
        Person person =checkUsername(username);
        if(person!=null)
        {
               if(person.getPassword().equals(password) && HelloApplication.louckout<5) {
                   HelloApplication.louckout=0;
                incrementAttemptedLogins(new Date(),person);
                return person;
            }
               else {
                   if(HelloApplication.louckout>=5)
                   {
                       Stage stage=alterMessage( "More than 5 failed login attempts. Your account is now locked.","Account Locked","Ok",null);
                       stage.show();
                   }
                   HelloApplication.louckout++;
               }
        }

        // Check Pharmacists

        incrementFailedAttempts(new Date(),username);
        return null;
    }

    public static Person checkUsername(String username) {
        for (PharmacyManager manager : pharmacyManagers) {
            if (manager.getUsername().equalsIgnoreCase(username)) {
                return manager;
            }
        }
        for (Pharmacist pharmacist : pharmacists) {
            if (pharmacist.getUsername().equalsIgnoreCase(username)) {
                return pharmacist;
            }
        }

        // Check Pharmacy Technicians
        for (PharmacyTechnician technician : pharmacyTechnicians) {
            if (technician.getUsername().equalsIgnoreCase(username)) {
                incrementAttemptedLogins(new Date(),technician);
                return technician;
            }
        }

        // Check Cashiers
        for (Cashier cashier : cashiers) {
            if (cashier.getUsername().equalsIgnoreCase(username) ) {
                incrementAttemptedLogins(new Date(),cashier);
                return cashier;
            }
        }
        for (Patient patient : patients) {
            if (patient.getUsername().equalsIgnoreCase(username) ) {
                incrementAttemptedLogins(new Date(),patient);
                return patient;
            }
        }
        return null;
    }

    public static void Addlogs() {
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            // Randomly pick a user type
            int userType = random.nextInt(4);
            Person user = null;
            switch (userType) {
                case 0 ->
                        user = pharmacyManagers.isEmpty() ? null : pharmacyManagers.get(random.nextInt(pharmacyManagers.size()));
                case 1 -> user = pharmacists.isEmpty() ? null : pharmacists.get(random.nextInt(pharmacists.size()));
                case 2 ->
                        user = pharmacyTechnicians.isEmpty() ? null : pharmacyTechnicians.get(random.nextInt(pharmacyTechnicians.size()));
                case 3 -> user = cashiers.isEmpty() ? null : cashiers.get(random.nextInt(cashiers.size()));
            }

            if (user != null) {
                // Simulate a 70% success rate
                boolean isSuccessfulLogin = random.nextDouble() < 0.7;

                if (isSuccessfulLogin) {
                    // Use the correct credentials for a successful login
                    UserGenerator.login(user.getUsername(), user.getPassword());
                } else {
                    // Use incorrect credentials for a failed login
                    UserGenerator.login("wrong_email@gmail.com", "wrong_password");
                }
            }
        }
    }

    public static String generateRandomPrescription() {
        String[] medications = {
                "Paracetamol", "Ibuprofen", "Amoxicillin", "Cough Syrup", "Vitamin C",
                "Aspirin", "Antibiotics", "Metformin", "Insulin", "Morphine"
        };

        String[] dosages = {"250mg", "500mg", "1000mg", "10ml", "20ml"};
        String[] durations = {"5 days", "7 days", "10 days", "2 weeks", "1 month"};
        String[] additionalDetails = {
                "Take after meals",
                "Take before bed",
                "Avoid alcohol",
                "Consult doctor if symptoms persist",
                "Store in a cool, dry place"
        };

        Random random = new Random();

        // Randomly select from each array
        String medication = medications[random.nextInt(medications.length)];
        String dosage = dosages[random.nextInt(dosages.length)];
        String duration = durations[random.nextInt(durations.length)];
        String details = additionalDetails[random.nextInt(additionalDetails.length)];
        float price =random.nextFloat(200);

        // Format the prescription
        return String.format(
                "Medication: %s\nDosage: %s\nDuration: %s\nDetails: %s",
                medication, dosage, duration, details
        );
    }

    //generate Inventory Stock
    public static void generateRandomInventory() {
        Random random = new Random();
        // Define some possible medication names
        String[] medications = {
                "Paracetamol", "Ibuprofen", "Amoxicillin", "Cough Syrup", "Vitamin C",
                "Aspirin", "Antibiotics", "Metformin", "Insulin", "Morphine"
        };

        // Generate random inventory data
        for (int i = 0; i < 50; i++) {
            String medicationName = medications[random.nextInt(medications.length)];
            int quantity = random.nextInt(101); // Random quantity between 0 and 100
            float price =random.nextFloat(200);
            inventoryStock.add(new Inventory(medicationName, quantity,price));
        }
    }

    public static Inventory findMedicine(String medicationName) {
        for(Inventory medicine: UserGenerator.inventoryStock)
            if(medicine.getName().equalsIgnoreCase(medicationName))
            {
                return medicine;
            }
        return null;
    }

}
