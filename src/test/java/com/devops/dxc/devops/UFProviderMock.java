package com.devops.dxc.devops;

import java.util.Date;

import com.devops.dxc.devops.excepcion.UFNoDisponibleException;
import com.devops.dxc.devops.service.UFProvider;

public class UFProviderMock implements UFProvider {

    private int uf;

    public UFProviderMock(int uf) {
        this.uf = uf;
    }

    @Override
    public int getPorDia(Date dia) throws UFNoDisponibleException {
        return uf;
    }

}
