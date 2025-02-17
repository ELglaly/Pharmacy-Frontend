package org.example.pharmacymanagmentfrontend.Model;

public class PharmacyTechnician extends Person {

    private PharmacyTechnician(Builder builder) {
        super(builder);
        setType();
    }

    @Override
    public void setType() {
        this.type = UserType.PharmacyTechnician;
    }

    public static class Builder extends Person.Builder<Builder> {
        @Override
        protected Builder self() {
            return this;
        }

        @Override
        public PharmacyTechnician build() {
            return new PharmacyTechnician(this);
        }
    }
}