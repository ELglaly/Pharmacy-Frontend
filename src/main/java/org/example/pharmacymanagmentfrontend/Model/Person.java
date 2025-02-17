package org.example.pharmacymanagmentfrontend.Model;

import java.util.Date;

public abstract class Person {
    protected String name;
    protected String username;
    protected String password;
    protected String email;
    protected String phone;
    protected String licenseNumber;
    protected String address;
    protected Date birthDate;
    protected UserType type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }

    public enum UserType {
        PharmacyManager, Pharmacist, PharmacyTechnician, Cashier, Patient
    }

    protected Person(Builder<?> builder) {
        this.name = builder.name;
        this.username = builder.username;
        this.password = builder.password;
        this.email = builder.email;
        this.phone = builder.phone;
        this.licenseNumber = builder.licenseNumber;
        this.address = builder.address;
        this.birthDate = builder.birthDate;
    }

    public abstract void setType();

    public static abstract class Builder<T extends Builder<T>> {
        private String name;
        private String username;
        private String password;
        private String email;
        private String phone;
        private String licenseNumber;
        private String address;
        private Date birthDate;

        public T setName(String name) {
            this.name = name;
            return self();
        }

        public T setUsername(String username) {
            this.username = username;
            return self();
        }

        public T setPassword(String password) {
            this.password = password;
            return self();
        }

        public T setEmail(String email) {
            this.email = email;
            return self();
        }

        public T setPhone(String phone) {
            this.phone = phone;
            return self();
        }

        public T setLicenseNumber(String licenseNumber) {
            this.licenseNumber = licenseNumber;
            return self();
        }

        public T setAddress(String address) {
            this.address = address;
            return self();
        }

        public T setBirthDate(Date birthDate) {
            this.birthDate = birthDate;
            return self();
        }

        protected abstract T self();

        public abstract Person build();
    }
}