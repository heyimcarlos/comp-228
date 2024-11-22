package com.lab.lab5;

public class Player {
    private int id;
    private String firstName;
    private String lastName;
    private String address;
    private String postalCode;
    private String province;
    private String phoneNumber;

    public Player(int id, String firstName, String lastName, String address, String postalCode, String province,
            String phoneNumber) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.postalCode = postalCode;
        this.province = province;
        this.phoneNumber = phoneNumber;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getProvince() {
        return province;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String toString() {
        return firstName + " " + lastName;
    }
}
