package com.greengrowapps.workshoptest;

public class Owner {

    private final String firstName;
    private final String lastName;
    private final String phoneNumber;

    public Owner(String firstName, String lastName, String phoneNumber) {
        this.firstName=firstName;
        this.lastName=lastName;
        this.phoneNumber=phoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
