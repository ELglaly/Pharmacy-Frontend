package org.example.pharmacymanagmentfrontend.Model;

public class Cashier extends Person {

    private Cashier(Builder builder) {
        super(builder);
        setType();
    }

    @Override
    public void setType() {
        this.type = UserType.Cashier;
    }

    public static class Builder extends Person.Builder<Builder> {
        @Override
        protected Builder self() {
            return this;
        }

        @Override
        public Cashier build() {
            return new Cashier(this);
        }
    }
}