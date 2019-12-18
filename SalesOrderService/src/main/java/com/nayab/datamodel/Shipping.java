package com.nayab.datamodel;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "SHIPPING_ADDRESS")
public class Shipping {

    private String addressLine1;

    private String addressLine2;

    private String addressLine3;

    private String street;

    private String pin;

    private String state;

    private String country;

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public void setAddressLine3(String addressLine3) {
        this.addressLine3 = addressLine3;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public String getAddressLine3() {
        return addressLine3;
    }

    public String getStreet() {
        return street;
    }

    public String getPin() {
        return pin;
    }

    public String getState() {
        return state;
    }

    public String getCountry() {
        return country;
    }

    public Shipping(String addressLine1, String addressLine2, String addressLine3, String street, String pin, String state, String country) {
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.addressLine3 = addressLine3;
        this.street = street;
        this.pin = pin;
        this.state = state;
        this.country = country;
    }

    public Shipping() {
    }
}
