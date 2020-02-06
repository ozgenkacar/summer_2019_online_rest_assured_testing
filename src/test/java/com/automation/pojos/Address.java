package com.automation.pojos;

public class Address {
    private int addressId;
    private String city;
    private String state;
    private String Street;
    private int zipCode;

    public Address() {
    }

    public Address(String city, String state, String street, int zipCode) {
        this.city = city;
        this.state = state;
        Street = street;
        this.zipCode = zipCode;
    }

    public int getAddressId() {
        return addressId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStreet() {
        return Street;
    }

    public void setStreet(String street) {
        Street = street;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    @Override
    public String toString() {
        return "Address{" +
                "addressId=" + addressId +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", Street='" + Street + '\'' +
                ", zipCode=" + zipCode +
                '}';
    }
}
