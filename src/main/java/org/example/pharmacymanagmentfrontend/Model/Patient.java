package org.example.pharmacymanagmentfrontend.Model;

public class Patient extends Person {

    private Patient(Builder builder) {
        super(builder);
        setType();
    }

    @Override
    public void setType() {
        this.type = UserType.Patient;
    }

    public static class Builder extends Person.Builder<Builder> {
        @Override
        protected Builder self() {
            return this;
        }

        @Override
        public Patient build() {
            return new Patient(this);
        }
    }
}