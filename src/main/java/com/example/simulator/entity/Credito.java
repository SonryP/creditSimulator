package com.example.simulator.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

public class Credito implements Serializable {

    private String rut;
    private Integer salary;
    private Integer creditAmmount;
    private Integer numOfMonthlyFees;
    private Calendar dateOfFirstFee;
    private ArrayList<Integer> nonPaymentMonth;

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public Integer getCreditAmmount() {
        return creditAmmount;
    }

    public void setCreditAmmount(Integer creditAmmount) {
        this.creditAmmount = creditAmmount;
    }

    public Integer getNumOfMonthlyFees() {
        return numOfMonthlyFees;
    }

    public void setNumOfMonthlyFees(Integer numOfMonthlyFees) {
        this.numOfMonthlyFees = numOfMonthlyFees;
    }

    public Calendar getDateOfFirstFee() {
        return dateOfFirstFee;
    }

    public void setDateOfFirstFee(Calendar dateOfFirstFee) {
        this.dateOfFirstFee = dateOfFirstFee;
    }

    public ArrayList<Integer> getNonPaymentMonth() {
        return nonPaymentMonth;
    }

    public void setNonPaymentMonth(ArrayList<Integer> nonPaymentMonth) {
        this.nonPaymentMonth = nonPaymentMonth;
    }
}