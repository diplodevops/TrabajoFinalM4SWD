package com.devops.dxc.devops.service;

import java.util.Date;

import com.devops.dxc.devops.excepcion.UFNoDisponibleException;
import com.devops.dxc.devops.model.Saldo;
import com.devops.dxc.devops.model.Util;

public class CalculadoraSaldo {

    UFProvider ufProvider;

    public CalculadoraSaldo(UFProvider ufProvider) {
        this.ufProvider = ufProvider;
    }

    public Saldo calcular(Date dia, long sueldo, long ahorro) throws UFNoDisponibleException {
        int uf = this.ufProvider.getPorDia(dia);
        long dxc = Util.getDxc(ahorro, sueldo, uf);
        long saldo = Util.getSaldo(ahorro, dxc);
        Saldo response = new Saldo(sueldo, ahorro, uf, saldo);
        return response;
    }
}