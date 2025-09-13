package com.testSpring.Tests.model;

import java.time.LocalDate;

public class Incomes {

    // Properties;
    private long idIncomes;
    private boolean fixOrNot;
    private LocalDate datePaymentIncomes;
    private double valueIncomes;
    private String typeIncomes;
    private int frequency;
    private Elderly elderly;

    // Empty constructor; 
    public Incomes() {}

    // Incomes constructor;
    public Incomes(boolean fixOrNot, LocalDate datePaymentIncomes, double valueIncomes, String typeIncomes, int frequency,Elderly elderly) {
        
        // Defining the properties values;
        this.fixOrNot = fixOrNot;
        this.datePaymentIncomes = datePaymentIncomes; 
        this.valueIncomes = valueIncomes;
        this.typeIncomes = typeIncomes;
        this.frequency = frequency;
        this.elderly = elderly;
    }
    // ...existing code...

    // IdIncomes getter and setter;
    public long getIdIncomes() {
        return idIncomes;
    }

    public void setIdIncomes(long idIncomes) {
        this.idIncomes = idIncomes;
    }

    public boolean isFixOrNot() {
        return fixOrNot;
    }

    public void setFixOrNot(boolean fixOrNot) {
        this.fixOrNot = fixOrNot;
    }

    public LocalDate getDatePaymentIncomes() {
        return datePaymentIncomes;
    }

    public void setDatePaymentIncomes(LocalDate datePaymentIncomes) {
        this.datePaymentIncomes = datePaymentIncomes;
    }

    public double getValueIncomes() {
        return valueIncomes;
    }

    public void setValueIncomes(double valueIncomes) {
        this.valueIncomes = valueIncomes;
    }

    public String getTypeIncomes() {
        return typeIncomes;
    }

    public void setTypeIncomes(String typeIncomes) {
        this.typeIncomes = typeIncomes;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public Elderly getElderly() {
        return elderly;
    }

    public void setElderly(Elderly elderly) {
        this.elderly = elderly;
    }
    
}
