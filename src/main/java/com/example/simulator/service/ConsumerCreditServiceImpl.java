package com.example.simulator.service;

import com.example.simulator.entity.Response;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class ConsumerCreditServiceImpl implements ConsumerCreditService {

    private static final Logger LOG = LoggerFactory.getLogger(ConsumerCreditServiceImpl.class);

    private final int MAX_SALARY = Integer.MAX_VALUE;
    private final int MIN_CREDIT_AMMOUNT = 100000;
    private final int MAX_CREDIT_AMMOUNT = 100000000;
    private final int MIN_NUM_OF_MONTHLY_FEES = 4;
    private final int MAX_NUM_OF_MONTHLY_FEES = 100;

    @Override
    public Response rutValidate(String rut) {
        Response response = new Response();
        try {
            boolean validate = false;
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
                validate = true;
            }
            if (validate){
                response.setCode("OK");
                response.setMessage("Validación rut ok.");
            }else{
                response.setCode("NOK");
                response.setMessage("Rut ingresado no es valido.");
            }
        } catch (java.lang.NumberFormatException e) {
            LOG.info("Error: ", e);
        }
        return response;
    }

    @Override
    public Response rentValidate(String rent) {
        Response response = new Response();
        try {
            if (isNumeric(rent)){
                Integer salarioNum = Integer.parseInt(rent);
                if (salarioNum >= 400000 && salarioNum < MAX_SALARY){
                    response.setCode("OK");
                    response.setMessage("Validación rent ok.");
                } else {
                    response.setCode("NOK");
                    response.setMessage("El rent ingresado no es valido.");
                }
            }else{
                response.setCode("NOK");
                response.setMessage("El rent ingresado no es numerico.");
            }
        }catch (java.lang.NumberFormatException e) {
            LOG.info("Error: ", e);
        }
        return response;
    }

    @Override
    public Response creditValidate(String credit) {
        Response response = new Response();
        try {
            if (isNumeric(credit)){
                Integer creditoNum = Integer.parseInt(credit);
                if (creditoNum >= MIN_CREDIT_AMMOUNT && creditoNum <= MAX_CREDIT_AMMOUNT){
                    response.setCode("OK");
                    response.setMessage("Validación credit ok.");
                } else {
                    response.setCode("NOK");
                    response.setMessage("El credit ingresado no es valido");
                }
            }else{
                response.setCode("NOK");
                response.setMessage("El credit ingresado no es numerico.");
            }
        }catch (java.lang.NumberFormatException e) {
            LOG.info("Error: ", e);
        }
        return response;
    }

    @Override
    public Response numOfMonthlyFeesValidate(Integer numOfMonthlyFees) {
        Response response = new Response();
        try {
            if (numOfMonthlyFees >= MIN_NUM_OF_MONTHLY_FEES && numOfMonthlyFees <= MAX_NUM_OF_MONTHLY_FEES) {
                response.setCode("OK");
                response.setMessage("Validación cuotas ok.");
            } else {
                response.setCode("NOK");
                response.setMessage("La cantidad de cuotas ingresadas no es valida");
            }
        } catch (java.lang.NumberFormatException e) {
            LOG.info("Error: ", e);
        }
        return response;
    }

    /*public void setDateOfFirstFee(Calendar dateOfFirstFee) {

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
    }*/

    @Override
    public Response nonPaymentMonthsValidate(ArrayList<Integer> nonPaymentMonths) {
        Response response = new Response();
        try {
            if (nonPaymentMonths != null) {
                if (nonPaymentMonths.size() > 2) {
                    response.setCode("NOK");
                    response.setMessage("La cantidad de meses no puede ser mayor a 2");
                } else if (nonPaymentMonths.size() <= 2) {
                    if (nonPaymentMonths.get(0) + 1 == nonPaymentMonths.get(1)) {
                        response.setCode("NOK");
                        response.setMessage("Los meses no pueden ser consecutivos");
                    }
                    if (nonPaymentMonths.get(0) - 1 == -1 && nonPaymentMonths.get(1) == 11) {
                        response.setCode("NOK");
                        response.setMessage("Los meses no pueden ser consecutivos");
                    }
                    if (nonPaymentMonths.get(0) + 1 == 12 && nonPaymentMonths.get(1) == 0) {
                        response.setCode("NOK");
                        response.setMessage("Los meses no pueden ser consecutivos");
                    }
                    if (nonPaymentMonths.get(0) + 1 == nonPaymentMonths.get(1) - 1) {
                        response.setCode("NOK");
                        response.setMessage("Los meses no pueden ser consecutivos");
                    }
                } else {
                    response.setCode("OK");
                    response.setMessage("Validación meses ok.");
                }
            } else {
                response.setCode("NOK");
                response.setMessage("Meses de no pago es nulo");
            }
        } catch (java.lang.NumberFormatException e) {
            LOG.info("Error: ", e);
        }
        return response;
    }

    public boolean isNumeric(String s) {
        return s != null && s.matches("[-+]?\\d*\\.?\\d+");
    }
}