package org.example.pharmacymanagmentfrontend.Model;
import java.sql.Timestamp;
import java.util.*;

public class UserGenerator {
    private static List<PharmacyManager> pharmacyManagers = new ArrayList<>();
    private static List<Pharmacist> pharmacists = new ArrayList<>();
    private static List<PharmacyTechnician> pharmacyTechnicians = new ArrayList<>();
    private static List<Cashier> cashiers = new ArrayList<>();
    private static List<Patient> patients = new ArrayList<>();
    private static List<UserLogs> loginTracker = new ArrayList<>();

    private String[] managerNames = {"Sherif Shawashen"};
    private String[] pharmacistNames = {"Emma Harris", "Liam Moore", "Ava Taylor", "Noah Anderson", "Isabella Clark"};
    private String[] technicianNames = {"Mason White", "Mia Walker", "Ethan Hall", "Amelia Lewis", "Lucas Allen"};
    private String[] cashierNames = {"Benjamin Scott", "Charlotte Young", "Elijah Adams", "Harper King", "Logan Wright"};
    private String[] patientNames = {"Henry Hill", "Emily Carter", "Jackson Baker", "Abigail Green", "Sebastian Nelson"};

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

    public List<Patient> getPatients() {
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


    public static Person login(String username, String password) {
        // Check Pharmacy Managers
        for (PharmacyManager manager : pharmacyManagers) {
            if (manager.getUsername().equalsIgnoreCase(username) && manager.getPassword().equals(password)) {
                System.out.println("Login successful as Pharmacy Manager.");
                incrementAttemptedLogins(new Date(),manager);
                return manager;
            }
        }

        // Check Pharmacists
        for (Pharmacist pharmacist : pharmacists) {
            if (pharmacist.getUsername().equalsIgnoreCase(username) && pharmacist.getPassword().equals(password)) {
                System.out.println("Login successful as Pharmacist.");
                incrementAttemptedLogins(new Date(),pharmacist);
                return pharmacist;
            }
        }

        // Check Pharmacy Technicians
        for (PharmacyTechnician technician : pharmacyTechnicians) {
            if (technician.getUsername().equalsIgnoreCase(username) && technician.getPassword().equals(password)) {
                System.out.println("Login successful as Pharmacy Technician.");
                incrementAttemptedLogins(new Date(),technician);
                return technician;
            }
        }

        // Check Cashiers
        for (Cashier cashier : cashiers) {
            if (cashier.getUsername().equalsIgnoreCase(username) && cashier.getPassword().equals(password)) {
                System.out.println("Login successful as Cashier.");
                incrementAttemptedLogins(new Date(),cashier);
                return cashier;
            }
        }

        // If no match found
        System.out.println("Login failed. Invalid username or password.");
        incrementFailedAttempts(new Date(),username);
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
}
