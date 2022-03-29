package com.devops.dxc.devops.service;

import java.util.Date;

import com.devops.dxc.devops.excepcion.UFNoDisponibleException;
import com.devops.dxc.devops.model.DxcImpuestoSaldo;
import com.devops.dxc.devops.model.Util;

public class Calculadora {

    UFProvider ufProvider;

    public Calculadora(UFProvider ufProvider) {
        this.ufProvider = ufProvider;
    }

    public DxcImpuestoSaldo calcular(Date dia, long sueldo, long ahorro) throws UFNoDisponibleException {
        int uf = this.ufProvider.getPorDia(dia);
        long dxc = Util.getDxc(ahorro, sueldo, uf);
        long impuesto = Util.getImpuesto(sueldo, dxc);
        long saldo = Util.getSaldo(ahorro, dxc);
        DxcImpuestoSaldo response = new DxcImpuestoSaldo(sueldo, ahorro, uf, dxc, impuesto, saldo);
        return response;
    }
}