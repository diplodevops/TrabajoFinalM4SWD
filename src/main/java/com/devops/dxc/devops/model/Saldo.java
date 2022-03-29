package com.devops.dxc.devops.model;

import java.io.Serializable;

public class Saldo implements Serializable {

	private long saldo;
	private long sueldo;
	private long ahorro;
	private int uf;

	public Saldo(long sueldo, long ahorro, int uf, long saldo) {
		this.sueldo = sueldo;
		this.ahorro = ahorro;
		this.uf = uf;
		this.saldo = saldo;
	}

	public int getUf() {
		return uf;
	}

	public long getSaldo() {
		return saldo;
	}

	public long getSueldo() {
		return sueldo;
	}

	public long getAhorro() {
		return ahorro;
	}

}
