package com.devops.dxc.devops.cmf;

import java.util.List;

@lombok.Data
public class CMFResponse {
	private List<Uf> uFs;

	@lombok.Data
	public class Uf {
		private String valor;
		private String fecha;
	}

}