package com.testSpring.Tests.model;

import java.time.LocalDate;

// Class User;
public class Expenses {
    
    // Properties;
    private long idExpenses;
    private boolean fixOrNot;
    private LocalDate datePaymentExpenses;
    private double valueExpenses;
    private String typeExpenses;
    private int portion;
    private Elderly elderly;

    // Empty constructor; 
    public Expenses() {}

    // Expenses constructor;
    public Expenses(boolean fixOrNot, LocalDate datePaymentExpenses, double valueExpenses, String typeExpenses, int portion, Elderly elderly) {
        
        // Defining the properties values;
        this.fixOrNot = fixOrNot;
        this.datePaymentExpenses = datePaymentExpenses; 
        this.valueExpenses = valueExpenses;
        this.typeExpenses = typeExpenses;
        this.portion = portion;
        this.elderly = elderly;
    }
    // ...existing code...

    // IdExpenses getter and setter;
    public long getIdExpenses() {
        return idExpenses;
    }

    public void setIdExpenses(long idExpenses) {
        this.idExpenses = idExpenses;
    }

    public boolean isFixOrNot() {
        return fixOrNot;
    }

    public void setFixOrNot(boolean fixOrNot) {
        this.fixOrNot = fixOrNot;
    }

    public LocalDate getDatePaymentExpenses() {
        return datePaymentExpenses;
    }

    public void setDatePaymentExpenses(LocalDate datePaymentExpenses) {
        this.datePaymentExpenses = datePaymentExpenses;
    }

    public double getValueExpenses() {
        return valueExpenses;
    }

    public void setValueExpenses(double valueExpenses) {
        this.valueExpenses = valueExpenses;
    }

    public String getTypeExpenses() {
        return typeExpenses;
    }

    public void setTypeExpenses(String typeExpenses) {
        this.typeExpenses = typeExpenses;
    }

    public int getPortion() {
        return portion;
    }

    public void setPortion(int portion) {
        this.portion = portion;
    }

    public Elderly getElderly() {
        return elderly;
    }

    public void setElderly(Elderly elderly) {
        this.elderly = elderly;
    }

}