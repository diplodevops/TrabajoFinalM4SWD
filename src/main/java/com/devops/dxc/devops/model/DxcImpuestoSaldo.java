package com.devops.dxc.devops.model;

import java.io.Serializable;

public class DxcImpuestoSaldo implements Serializable {

	private long dxc;
	private long saldo;
	private long impuesto;
	private long sueldo;
	private long ahorro;
	private int uf;

	public DxcImpuestoSaldo(long sueldo, long ahorro, int uf, long dxc, long impuesto, long saldo) {
		this.sueldo = sueldo;
		this.ahorro = ahorro;
		this.uf = uf;
		this.dxc = dxc;
		this.saldo = saldo;
		this.impuesto = impuesto;
	}

	public int getUf() {
		return uf;
	}

	public long getDxc() {
		return this.dxc;
	}

	public long getSaldo() {
		return saldo;
	}

	public long getImpuesto() {
		return impuesto;
	}

	public long getSueldo() {
		return sueldo;
	}

	public long getAhorro() {
		return ahorro;
	}

}
