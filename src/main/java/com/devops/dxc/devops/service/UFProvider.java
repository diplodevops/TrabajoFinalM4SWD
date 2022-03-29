package com.devops.dxc.devops.service;

import java.util.Date;

import com.devops.dxc.devops.excepcion.UFNoDisponibleException;

public interface UFProvider {
    public int getPorDia(Date dia) throws UFNoDisponibleException;
}
