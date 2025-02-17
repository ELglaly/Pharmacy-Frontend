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

    private static UserGenerator userGenerator = null;

    public UserGenerator() {
        if (userGenerator == null) {
            generateUserData();
            userGenerator = this;
        }
    }

    private void generateUserData() {
        createUsers(managerNames, pharmacyManagers, Person.UserType.PharmacyManager);
        createUsers(pharmacistNames, pharmacists, Person.UserType.Pharmacist);
        createUsers(technicianNames, pharmacyTechnicians, Person.UserType.PharmacyTechnician);
        createUsers(cashierNames, cashiers, Person.UserType.Cashier);
        createUsers(patientNames, patients, Person.UserType.Patient);
        generateRandomInventory();
    }

    private <T extends Person> void createUsers(String[] names, List<T> list, Person.UserType userType) {
        for (int i = 0; i < names.length; i++) {
            list.add((T) PersonFactory.createPerson(
                    userType,
                    names[i],
                    userType.name().toLowerCase() + (1000 + i),
                    "password" + (i + 1),
                    userType.name().toLowerCase() + (i + 1) + "@gmail.com",
                    "555-123-" + (1000 + i),
                    "License" + (i + 1),
                    userType.name() + " Address" + (i + 1),
                    new Date(1980 + i, 5, 15)
            ));
        }
    }

    public static void updateUser(Person person, String name, String email, String phone, String licenseNumber, String address, LocalDate birthDate, String userType, Date birthDateAsDate) {
        person.setName(name);
        person.setEmail(email);
        person.setPhone(phone);
        person.setAddress(address);
        person.setType(Person.UserType.valueOf(userType));
        person.setBirthDate(birthDateAsDate);
    }

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
        UserLogs userLogs = new UserLogs(loginTime, true, person, person.getUsername());
        loginTracker.add(userLogs);
    }

    public static void incrementFailedAttempts(Date loginTime, String username) {
        UserLogs userLogs = new UserLogs(loginTime, false, null, username);
        loginTracker.add(userLogs);
    }

    public static List<UserLogs> getLoginTracker() {
        return loginTracker;
    }

    public static List<Inventory> getInventoryStock() {
        return inventoryStock;
    }

    public static Person login(String username, String password) {
        Person person = checkUsername(username);
        if (person != null) {
            if (person.getPassword().equals(password) && HelloApplication.louckout < 5) {
                HelloApplication.louckout = 0;
                incrementAttemptedLogins(new Date(), person);
                return person;
            } else {
                handleFailedLogin();
            }
        }
        incrementFailedAttempts(new Date(), username);
        return null;
    }

    private static void handleFailedLogin() {
        if (HelloApplication.louckout >= 5) {
            Stage stage = alterMessage("More than 5 failed login attempts. Your account is now locked.", "Account Locked", "Ok", null);
            stage.show();
            HelloApplication.louckout = 0;
        }
        HelloApplication.louckout++;
    }

    public static Person checkUsername(String username) {
        Person person = findUserByUsername(username, pharmacyManagers);
        if (person == null) person = findUserByUsername(username, pharmacists);
        if (person == null) person = findUserByUsername(username, pharmacyTechnicians);
        if (person == null) person = findUserByUsername(username, cashiers);
        if (person == null) person = findUserByUsername(username, patients);
        return person;
    }

    private static <T extends Person> T findUserByUsername(String username, List<T> list) {
        for (T user : list) {
            if (user.getUsername().equalsIgnoreCase(username)) {
                incrementAttemptedLogins(new Date(), user);
                return user;
            }
        }
        return null;
    }

    public static void addLogs() {
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            Person user = getRandomUser(random);
            if (user != null) {
                boolean isSuccessfulLogin = random.nextDouble() < 0.7;
                if (isSuccessfulLogin) {
                    login(user.getUsername(), user.getPassword());
                } else {
                    login("wrong_email@gmail.com", "wrong_password");
                }
            }
        }
    }

    private static Person getRandomUser(Random random) {
        int userType = random.nextInt(4);
        return switch (userType) {
            case 0 -> pharmacyManagers.isEmpty() ? null : pharmacyManagers.get(random.nextInt(pharmacyManagers.size()));
            case 1 -> pharmacists.isEmpty() ? null : pharmacists.get(random.nextInt(pharmacists.size()));
            case 2 -> pharmacyTechnicians.isEmpty() ? null : pharmacyTechnicians.get(random.nextInt(pharmacyTechnicians.size()));
            case 3 -> cashiers.isEmpty() ? null : cashiers.get(random.nextInt(cashiers.size()));
            default -> null;
        };
    }

    public static String generateRandomPrescription() {
        String[] medications = {"Paracetamol", "Ibuprofen", "Amoxicillin", "Cough Syrup", "Vitamin C", "Aspirin", "Antibiotics", "Metformin", "Insulin", "Morphine"};
        String[] dosages = {"250mg", "500mg", "1000mg", "10ml", "20ml"};
        String[] durations = {"5 days", "7 days", "10 days", "2 weeks", "1 month"};
        String[] additionalDetails = {"Take after meals", "Take before bed", "Avoid alcohol", "Consult doctor if symptoms persist", "Store in a cool, dry place"};

        Random random = new Random();
        String medication = medications[random.nextInt(medications.length)];
        String dosage = dosages[random.nextInt(dosages.length)];
        String duration = durations[random.nextInt(durations.length)];
        String details = additionalDetails[random.nextInt(additionalDetails.length)];

        return String.format("Medication: %s\nDosage: %s\nDuration: %s\nDetails: %s", medication, dosage, duration, details);
    }

    public static void generateRandomInventory() {
        Random random = new Random();
        String[] medications = {"Paracetamol", "Ibuprofen", "Amoxicillin", "Cough Syrup", "Vitamin C", "Aspirin", "Antibiotics", "Metformin", "Insulin", "Morphine"};

        for (int i = 0; i < 50; i++) {
            String medicationName = medications[random.nextInt(medications.length)];
            int quantity = random.nextInt(101);
            float price = random.nextFloat() * 200;
            inventoryStock.add(new Inventory(medicationName, quantity, price));
        }
    }

    public static Inventory findMedicine(String medicationName) {
        for (Inventory medicine : inventoryStock) {
            if (medicine.getName().equalsIgnoreCase(medicationName)) {
                return medicine;
            }
        }
        return null;
    }
}