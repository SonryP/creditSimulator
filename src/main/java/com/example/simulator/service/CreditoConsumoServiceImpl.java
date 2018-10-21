package com.example.simulator.service;

import com.example.simulator.entity.Respuesta;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class CreditoConsumoServiceImpl implements CreditoConsumoService {

    private static final Logger LOG = LoggerFactory.getLogger(CreditoConsumoServiceImpl.class);

    private final int MAX_SALARY = Integer.MAX_VALUE;
    private final int MIN_CREDIT_AMMOUNT = 100000;
    private final int MAX_CREDIT_AMMOUNT = 100000000;
    //private final int MIN_NUM_OF_MONTHLY_FEES = 4;
    //private final int MAX_NUM_OF_MONTHLY_FEES = 100;

    @Override
    public Respuesta validarRut(String rut) {
        Respuesta respuesta = new Respuesta();
        try {
            boolean validacion = false;
            rut =  rut.toUpperCase();
            rut = rut.replace(".", "");
            rut = rut.replace("-", "");
            int rutAux = Integer.parseInt(rut.substring(0, rut.length() - 1));

            char dv = rut.charAt(rut.length() - 1);

            int m = 0;
            int s =1;
            for (; rutAux != 0; rutAux /= 10) {
                s = (s + rutAux % 10 * (9 - m++ % 6)) % 11;
            }
            if (dv == (char) (s != 0 ? s + 47 : 75)) {
                validacion = true;
            }
            if (validacion){
                respuesta.setCodigo("OK");
                respuesta.setMensaje("Validación rut ok.");
            }else{
                respuesta.setCodigo("NOK");
                respuesta.setMensaje("Rut ingresado no es valido.");
            }
        } catch (java.lang.NumberFormatException e) {
            LOG.info("Error: ", e);
        }
        return respuesta;
    }

    @Override
    public Respuesta validaSalario(String salario) {
        Respuesta respuesta = new Respuesta();
        try {
            if (isNumeric(salario)){
                Integer salarioNum = Integer.parseInt(salario);
                if (salarioNum >= 400000 && salarioNum < MAX_SALARY){
                    respuesta.setCodigo("OK");
                    respuesta.setMensaje("Validación salario ok.");
                } else {
                    respuesta.setCodigo("NOK");
                    respuesta.setMensaje("El salario ingresado no es valido.");
                }
            }else{
                respuesta.setCodigo("NOK");
                respuesta.setMensaje("El salario ingresado no es numerico.");
            }
        }catch (java.lang.NumberFormatException e) {
            LOG.info("Error: ", e);
        }
        return respuesta;
    }

    @Override
    public Respuesta validaCredito(String credito) {
        Respuesta respuesta = new Respuesta();
        try {
            if (isNumeric(credito)){
                Integer creditoNum = Integer.parseInt(credito);
                if (creditoNum >= MIN_CREDIT_AMMOUNT && creditoNum <= MAX_CREDIT_AMMOUNT){
                    respuesta.setCodigo("OK");
                    respuesta.setMensaje("Validación credito ok.");
                } else {
                    respuesta.setCodigo("NOK");
                    respuesta.setMensaje("El credito ingresado no es valido");
                }
            }else{
                respuesta.setCodigo("NOK");
                respuesta.setMensaje("El credito ingresado no es numerico.");
            }
        }catch (java.lang.NumberFormatException e) {
            LOG.info("Error: ", e);
        }
        return respuesta;
    }

/*
    public void setNumOfMonthlyFees(int numOfMonthlyFees) {
        if (numOfMonthlyFees >= MIN_NUM_OF_MONTHLY_FEES && numOfMonthlyFees <= MAX_NUM_OF_MONTHLY_FEES){
            this.numOfMonthlyFees = numOfMonthlyFees;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public void setDateOfFirstFee(Calendar dateOfFirstFee) {

        Calendar today = Calendar.getInstance();
        Calendar maxMonth = today;
        Calendar maxDayMonth = today;
        Calendar minDayMonth = today;
        maxMonth.add(Calendar.MONTH, 1);
        maxDayMonth.add(Calendar.DAY_OF_MONTH, -2);
        minDayMonth.add(Calendar.DAY_OF_MONTH, -10);

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

        Date mesMaximo = maxMonth.getTime();
        Date hoy = today.getTime();

        if (mesMaximo.compareTo(hoy)>0
                ) {
            this.dateOfFirstFee = dateOfFirstFee;
        } else {
            throw new IllegalArgumentException();
        }

    }

    public int validarNonPaymentMonthVacio( ArrayList<Integer> nonPaymentMonths){
        if (nonPaymentMonth == null){
            nonPaymentMonth = nonPaymentMonths;
            return nonPaymentMonth.size();
        }else {
            return nonPaymentMonth.size();
        }
    }

    public boolean validarNonPaymentMonths(ArrayList<Integer> nonPaymentMonths){
        if (validarNonPaymentMonthVacio(nonPaymentMonths)<=0){
            return false;
        }else if (validarNonPaymentMonthVacio(nonPaymentMonths)>2){
            return false;
        }else if(validarNonPaymentMonthVacio(nonPaymentMonths)==2){
            if(nonPaymentMonths.get(0)+1==nonPaymentMonths.get(1)){
                return false;
            }
            if(nonPaymentMonths.get(0)-1==-1&&nonPaymentMonths.get(1)==11){
                return false;
            }
            if(nonPaymentMonths.get(0)+1==12&&nonPaymentMonths.get(1)==0) {
                return true;
            }
            if(nonPaymentMonths.get(0)+1==nonPaymentMonths.get(1)-1){
                return true;
            }
        }else{
            return true;
        }
        return false;
    }*/

    public boolean isNumeric(String s) {
        return s != null && s.matches("[-+]?\\d*\\.?\\d+");
    }
}