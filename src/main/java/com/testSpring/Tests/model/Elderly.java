// Package;
package com.testSpring.Tests.model;

import java.util.List;

// Class Elderly;
public class Elderly {
    
    // Elderly properties;
    private long idElderly;
    private String nameElderly;
    private String email;
    private String passwordElderly;
    private String cpf;
    private String birthYear;
    private String gender;
    private String phone;
    private List<Chat> chats;
    private List<Incomes> incomes;
    private List<Expenses> expenses;

    // Empty constructor
    public Elderly() {};

    // Contructor with parameters;
    public Elderly(String nameElderly, String email, String passwordElderly, 
        String cpf, String birthYear, String gender, String phone) {
    
        // Initialize properties;
        this.nameElderly = nameElderly;
        this.email = email;
        this.passwordElderly = passwordElderly;
        this.cpf = cpf;
        this.birthYear = birthYear;
        this.gender = gender;
        this.phone = phone;
    }

    // Getter and Setter idElderly;
    public long getIdElderly() {
        return idElderly;
    }

    public void setIdElderly(long idElderly) {
        this.idElderly = idElderly;
    }

    // Getter and Setter nameElderly;
    public String getNameElderly() {
        return nameElderly;
    }

    public void setNameElderly(String nameElderly) {
        this.nameElderly = nameElderly;
    }

    // Getter and Setter email;
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Getter and Setter passwordElderly;
    public String getPasswordElderly() {
        return passwordElderly;
    }

    public void setPasswordElderly(String passwordElderly) {
        this.passwordElderly = passwordElderly;
    }

    // Getter and Setter cpf;
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    // Getter and Setter birthYear;
    public String getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(String birthYear) {
        this.birthYear = birthYear;
    }

    // Getter and Setter gender;
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    // Getter and Setter phone;
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
