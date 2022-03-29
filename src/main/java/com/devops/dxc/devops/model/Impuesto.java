package com.devops.dxc.devops.model;

import java.io.Serializable;

public class Impuesto implements Serializable {

	private long impuesto;
	private long sueldo;
	private long ahorro;
	private int uf;

	public Impuesto(long sueldo, long ahorro, int uf, long impuesto) {
		this.sueldo = sueldo;
		this.ahorro = ahorro;
		this.uf = uf;
		this.impuesto = impuesto;
	}

	public int getUf() {
		return uf;
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
