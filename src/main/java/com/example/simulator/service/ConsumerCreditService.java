package com.example.simulator.service;

import com.example.simulator.entity.Response;

import java.util.ArrayList;
import java.util.Date;

public interface ConsumerCreditService {
    public Response rutValidate(String rut);
    public Response rentValidate(String rent);
    public Response creditValidate(String credit);
    public Response numOfMonthlyFeesValidate(Integer numOfMonthlyFees);
    public Response nonPaymentMonthsValidate(ArrayList<Integer> nonPaymentMonths);
    public Response dateOfFirstFeeValidate(Date date);
}
