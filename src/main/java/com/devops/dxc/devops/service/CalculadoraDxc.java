package com.devops.dxc.devops.service;

import java.util.Date;

import com.devops.dxc.devops.excepcion.UFNoDisponibleException;
import com.devops.dxc.devops.model.Dxc;
import com.devops.dxc.devops.model.Util;

public class CalculadoraDxc {

    UFProvider ufProvider;

    public CalculadoraDxc(UFProvider ufProvider) {
        this.ufProvider = ufProvider;
    }

    public Dxc calcular(Date dia, long sueldo, long ahorro) throws UFNoDisponibleException {
        int uf = this.ufProvider.getPorDia(dia);
        long dxc = Util.getDxc(ahorro, sueldo, uf);
        Dxc response = new Dxc(sueldo, ahorro, uf, dxc);
        return response;
    }
}