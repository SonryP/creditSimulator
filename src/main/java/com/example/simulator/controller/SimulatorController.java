package com.example.simulator.controller;

import com.example.simulator.entity.Respuesta;
import com.example.simulator.service.CreditoConsumoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.google.gson.Gson;

@Controller
public class SimulatorController {

    private static final Logger LOG = LoggerFactory.getLogger(SimulatorController.class);

    @Autowired
    private CreditoConsumoService CreditoConsumoService;

    @RequestMapping(value = "index", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    public String consultaCredito() {
        try {
            LOG.info("Inicio consulta credito");
        } catch (Exception e) {
            LOG.error("Exception in the IframeFacturacionController", e);
        }
        return "index";
    }

    @RequestMapping(value = "/validaRut", method = RequestMethod.POST)
    @ResponseBody
    public String validaRut(@RequestParam String rut) {
        Respuesta respuesta = new Respuesta();
        try {
            LOG.info("Rut ingresado: {}", rut);
            respuesta = CreditoConsumoService.validarRut(rut);
            LOG.info("Response->{}", respuesta.getCodigo());
        } catch (Exception e) {
            LOG.error("Exception", e);
        }
        return new Gson().toJson(respuesta);
    }

    @RequestMapping(value = "/validaSalario", method = RequestMethod.POST)
    @ResponseBody
    public String validaSalario(@RequestParam String salario) {
        Respuesta respuesta = new Respuesta();
        try {
            LOG.info("Salario ingresado: {}", salario);
            respuesta = CreditoConsumoService.validaSalario(salario);
            LOG.info("Response->{}", respuesta.getCodigo());
        } catch (Exception e) {
            LOG.error("Exception", e);
        }
        return new Gson().toJson(respuesta);
    }

    @RequestMapping(value = "/validaCredito", method = RequestMethod.POST)
    @ResponseBody
    public String validaCredito(@RequestParam String credito) {
        Respuesta respuesta = new Respuesta();
        try {
            LOG.info("Credito ingresado: {}", credito);
            respuesta = CreditoConsumoService.validaCredito(credito);
            LOG.info("Response->{}", respuesta.getCodigo());
        } catch (Exception e) {
            LOG.error("Exception", e);
        }
        return new Gson().toJson(respuesta);
    }

}
