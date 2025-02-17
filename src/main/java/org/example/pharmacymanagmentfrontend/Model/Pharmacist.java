package org.example.pharmacymanagmentfrontend.Model;

public class Pharmacist extends Person {

    private Pharmacist(Builder builder) {
        super(builder);
        setType();
    }

    @Override
    public void setType() {
        this.type = UserType.Pharmacist;
    }

    public static class Builder extends Person.Builder<Builder> {
        @Override
        protected Builder self() {
            return this;
        }

        @Override
        public Pharmacist build() {
            return new Pharmacist(this);
        }
    }
}