package com.example.simulator.service;

import java.util.Date;

public interface UtilsService {
    public boolean isNumeric(String s);
    public Date fechaActual();
    public Date addRestFecha(Date date, int daysQuantity);
    public Date addMonthRestFecha(Date date, int monthsQuantity);
}
