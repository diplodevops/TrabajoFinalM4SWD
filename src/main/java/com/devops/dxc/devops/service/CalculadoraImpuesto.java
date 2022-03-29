package com.devops.dxc.devops.service;

import java.util.Date;

import com.devops.dxc.devops.excepcion.UFNoDisponibleException;
import com.devops.dxc.devops.model.Impuesto;
import com.devops.dxc.devops.model.Util;

public class CalculadoraImpuesto {

    UFProvider ufProvider;

    public CalculadoraImpuesto(UFProvider ufProvider) {
        this.ufProvider = ufProvider;
    }

    public Impuesto calcular(Date dia, long sueldo, long ahorro) throws UFNoDisponibleException {
        int uf = this.ufProvider.getPorDia(dia);
        long dxc = Util.getDxc(ahorro, sueldo, uf);
        long impuesto = Util.getImpuesto(sueldo, dxc);
        Impuesto response = new Impuesto(sueldo, ahorro, uf, impuesto);
        return response;
    }
}