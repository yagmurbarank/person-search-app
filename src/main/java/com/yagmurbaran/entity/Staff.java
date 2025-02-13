package com.yagmurbaran.entity;

import jakarta.persistence.Entity;

@Entity
public class Staff extends AbstractEntity {

    private String identityNumber;
    private String firstName;
    private String lastName;


    public String getIdentityNumber() {

        return identityNumber;
    }

    public String getFirstName() {

        return firstName;
    }

    public String getLastName() {

        return lastName;
    }

    public void setFirstName(String firstName) {

        this.firstName = firstName;
    }

    public void setLastName(String lastName) {

        this.lastName = lastName;
    }

}
