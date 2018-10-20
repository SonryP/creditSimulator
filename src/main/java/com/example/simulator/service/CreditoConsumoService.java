package com.example.simulator.service;

import com.example.simulator.entity.Respuesta;

public interface CreditoConsumoService {
    public Respuesta validarRut(String rut);
    public Respuesta validaSalario(String salario);
    public Respuesta validaCredito(String credito);
}
