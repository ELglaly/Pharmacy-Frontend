package org.example.pharmacymanagmentfrontend.Model;

import java.util.Date;

public class PersonFactory {

    public static Person createPerson(Person.UserType userType, String name, String username, String password, String email, String phone, String licenseNumber, String address, Date birthDate) {
        switch (userType) {
            case PharmacyManager:
                return createPharmacyManager(name, username, password, email, phone, licenseNumber, address, birthDate);
            case Pharmacist:
                return createPharmacist(name, username, password, email, phone, licenseNumber, address, birthDate);
            case PharmacyTechnician:
                return createPharmacyTechnician(name, username, password, email, phone, licenseNumber, address, birthDate);
            case Cashier:
                return createCashier(name, username, password, email, phone, licenseNumber, address, birthDate);
            case Patient:
                return createPatient(name, username, password, email, phone, licenseNumber, address, birthDate);
            default:
                throw new IllegalArgumentException("Invalid user type: " + userType);
        }
    }

    private static PharmacyManager createPharmacyManager(String name, String username, String password, String email, String phone, String licenseNumber, String address, Date birthDate) {
        return new PharmacyManager.Builder()
                .setName(name)
                .setUsername(username)
                .setPassword(password)
                .setEmail(email)
                .setPhone(phone)
                .setLicenseNumber(licenseNumber)
                .setAddress(address)
                .setBirthDate(birthDate)
                .build();
    }

    private static Pharmacist createPharmacist(String name, String username, String password, String email, String phone, String licenseNumber, String address, Date birthDate) {
        return new Pharmacist.Builder()
                .setName(name)
                .setUsername(username)
                .setPassword(password)
                .setEmail(email)
                .setPhone(phone)
                .setLicenseNumber(licenseNumber)
                .setAddress(address)
                .setBirthDate(birthDate)
                .build();
    }

    private static PharmacyTechnician createPharmacyTechnician(String name, String username, String password, String email, String phone, String licenseNumber, String address, Date birthDate) {
        return new PharmacyTechnician.Builder()
                .setName(name)
                .setUsername(username)
                .setPassword(password)
                .setEmail(email)
                .setPhone(phone)
                .setLicenseNumber(licenseNumber)
                .setAddress(address)
                .setBirthDate(birthDate)
                .build();
    }

    private static Cashier createCashier(String name, String username, String password, String email, String phone, String licenseNumber, String address, Date birthDate) {
        return new Cashier.Builder()
                .setName(name)
                .setUsername(username)
                .setPassword(password)
                .setEmail(email)
                .setPhone(phone)
                .setLicenseNumber(licenseNumber)
                .setAddress(address)
                .setBirthDate(birthDate)
                .build();
    }

    private static Patient createPatient(String name, String username, String password, String email, String phone, String licenseNumber, String address, Date birthDate) {
        return new Patient.Builder()
                .setName(name)
                .setUsername(username)
                .setPassword(password)
                .setEmail(email)
                .setPhone(phone)
                .setLicenseNumber(licenseNumber)
                .setAddress(address)
                .setBirthDate(birthDate)
                .build();
    }
}