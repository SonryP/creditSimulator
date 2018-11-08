package com.example.simulator.controller;

import com.example.simulator.entity.Response;
import com.example.simulator.service.ConsumerCreditService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.google.gson.Gson;

import java.util.Date;

@Controller
public class SimulatorController {

    private static final Logger LOG = LoggerFactory.getLogger(SimulatorController.class);

    @Autowired
    private ConsumerCreditService consumerCreditService;

    @RequestMapping(value = "index", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    public String creditSimulator() {
        try {
            LOG.info("Inicio consulta credito");
        } catch (Exception e) {
            LOG.error("Exception in the IframeFacturacionController", e);
        }
        return "index";
    }

    @RequestMapping(value = "/rutValidate", method = RequestMethod.POST)
    @ResponseBody
    public String rutValidate(@RequestParam String rut) {
        Response response = new Response();
        try {
            LOG.info("Rut ingresado: {}", rut);
            response = consumerCreditService.rutValidate(rut);
            LOG.info("Response->{}", response.getCode());
        } catch (Exception e) {
            LOG.error("Exception", e);
        }
        return new Gson().toJson(response);
    }

    @RequestMapping(value = "/rentValidate", method = RequestMethod.POST)
    @ResponseBody
    public String rentValidate(@RequestParam String rent) {
        Response response = new Response();
        try {
            LOG.info("Salario ingresado: {}", rent);
            response = consumerCreditService.rentValidate(rent);
            LOG.info("Response->{}", response.getCode());
        } catch (Exception e) {
            LOG.error("Exception", e);
        }
        return new Gson().toJson(response);
    }

    @RequestMapping(value = "/creditValidate", method = RequestMethod.POST)
    @ResponseBody
    public String creditValidate(@RequestParam String credit) {
        Response response = new Response();
        try {
            LOG.info("Credito ingresado: {}", credit);
            response = consumerCreditService.creditValidate(credit);
            LOG.info("Response->{}", response.getCode());
        } catch (Exception e) {
            LOG.error("Exception", e);
        }
        return new Gson().toJson(response);
    }

    @RequestMapping(value = "/numOfMonthlyFeesValidate", method = RequestMethod.POST)
    @ResponseBody
    public String numOfMonthlyFeesValidate(@RequestParam Integer numOfMonthlyFees) {
        Response response = new Response();
        try {
            LOG.info("Numero de cuotas ingresado: {}", numOfMonthlyFees);
            response = consumerCreditService.numOfMonthlyFeesValidate(numOfMonthlyFees);
            LOG.info("Response->{}", response.getCode());
        } catch (Exception e) {
            LOG.error("Exception", e);
        }
        return new Gson().toJson(response);
    }

    @RequestMapping(value = "/dateOfFirstFeeValidate", method = RequestMethod.POST)
    @ResponseBody
    public String dateOfFirstFeeValidate(@RequestParam Date date) {
        Response response = new Response();
        try {
            LOG.info("Fecha ingresada: {}", date);
            response = consumerCreditService.dateOfFirstFeeValidate(date);
            LOG.info("Response->{}", response.getCode());
        } catch (Exception e) {
            LOG.error("Exception", e);
        }
        return new Gson().toJson(response);
    }

}
