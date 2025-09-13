// Package;
package com.testSpring.Tests.model;

// Class Address
public class Address {
    
    // Attributes properties;
    private long idAddress;
    private String neighborhood;
    private String city;
    private int number;
    private String state;
    private String street;
    private Elderly elderly;

    // Empty constructor
    public Address() {}

    // Constructor with parameters
    public Address(String neighborhood, String city, int number, 
        String state, String street, Elderly elderly) {
        
        this.street = street;
        this.number = number;
        this.neighborhood = neighborhood;
        this.city = city;
        this.state = state;
        this.elderly = elderly;
    }

    // Getter and Setter idAddress
    public long getIdAddress() {
        return idAddress;
    }

    public void setIdAddress(long idAddress) {
        this.idAddress = idAddress;
    }

    // Getter and Setter street
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    // Getter and Setter number
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    // Getter and Setter neighborhood
    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    // Getter and Setter city
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    // Getter and Setter state
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    //Getter and Setter Elderly
    public Elderly getElderly() {
        return elderly;
    }

    public void setElderly(Elderly elderly) {
        this.elderly = elderly;
    }
}
