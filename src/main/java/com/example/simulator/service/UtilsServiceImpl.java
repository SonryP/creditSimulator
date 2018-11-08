package com.example.simulator.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

@Service
public class UtilsServiceImpl implements UtilsService {

    private static final Logger LOG = LoggerFactory.getLogger(UtilsService.class);

    @Override
    public boolean isNumeric(String s) {
        return s != null && s.matches("[-+]?\\d*\\.?\\d+");
    }

    @Override
    public Date fechaActual() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getTimeZone("America/Santiago"));
        return calendar.getTime();
    }

    @Override
    public Date addRestFecha(Date date, int daysQuantity) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getTimeZone("America/Santiago"));
        calendar.setTime(date); // Configuramos la fecha que se recibe
        calendar.add(Calendar.DAY_OF_YEAR, daysQuantity);  // numero de días a añadir, o restar en caso de días<0
        return calendar.getTime(); // Devuelve el objeto Date con los nuevos días añadidos
    }

    @Override
    public Date addMonthRestFecha(Date date, int monthsQuantity) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getTimeZone("America/Santiago"));
        calendar.setTime(date); // Configuramos la fecha que se recibe
        calendar.add(Calendar.MONTH, monthsQuantity);  // numero de meses a añadir
        return calendar.getTime(); // Devuelve el objeto Date con los nuevos meses añadidos
    }


}