package org.example.pharmacymanagmentfrontend.Model;

public class PharmacyManager extends Person {

    private PharmacyManager(Builder builder) {
        super(builder);
        setType();
    }

    @Override
    public void setType() {
        this.type = UserType.PharmacyManager;
    }

    public static class Builder extends Person.Builder<Builder> {
        @Override
        protected Builder self() {
            return this;
        }

        @Override
        public PharmacyManager build() {
            return new PharmacyManager(this);
        }
    }
}