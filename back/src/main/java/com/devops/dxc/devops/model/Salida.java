package com.devops.dxc.devops.model;

import java.io.Serializable;

public class Salida implements Serializable {

    private static final long serialVersionUID = -2988002029080131424L;
    private final int valor;
    private final String descripcion ;


    public Salida(int ahorro, int sueldo, String salida) {

        this.descripcion = salida;
        this.valor = evalua(ahorro, sueldo, salida);

    }

    private int evalua(int ahorro, int sueldo, String salida){
        if(salida.equals("saldo")){
            return Util.getSaldo(ahorro, sueldo);
        }else if(salida.equals("impuesto")){
            return Util.getImpuesto(ahorro, sueldo);
        }else if(salida.equals("10x100")){
            return Util.getDxc(ahorro, sueldo);
        }else{
            return 0;
        }
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getValor() {
        return valor;
    }


}