package com.example.accessingdatamongodb;

import org.springframework.data.annotation.Id;


public class Customer {

    @Id
    public String id;

    public String firstName;
    public String lastName;
    public String contactNumber;
    public double creditLimit;

    public Customer() {}

    public Customer(String firstName, String lastName, String contactNumber, double creditLimit) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.contactNumber = contactNumber;
        this.creditLimit = creditLimit;
    }

    @Override
    public String toString() {
        return String.format(
                "Customer[id=%s, firstName='%s', lastName='%s', contactNumber='%s', creditLimit='%s']",
                id, firstName, lastName, contactNumber, creditLimit);
    }

}