package com.devops.dxc.devops.rest;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.devops.dxc.devops.excepcion.UFNoDisponibleException;
import com.devops.dxc.devops.model.Dxc;
import com.devops.dxc.devops.model.DxcImpuestoSaldo;
import com.devops.dxc.devops.model.Impuesto;
import com.devops.dxc.devops.model.Saldo;
import com.devops.dxc.devops.service.Calculadora;
import com.devops.dxc.devops.service.CalculadoraDxc;
import com.devops.dxc.devops.service.CalculadoraImpuesto;
import com.devops.dxc.devops.service.CalculadoraSaldo;
import com.devops.dxc.devops.service.UFProvider;
import com.devops.dxc.devops.service.UFProviderMiIndicador;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/rest/msdxc")
public class RestData {

	private final static Logger LOGGER = Logger.getLogger("devops.subnivel.Control");

	@GetMapping(path = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody DxcImpuestoSaldo get(
			@RequestParam(name = "sueldo") long sueldo,
			@RequestParam(name = "ahorro") long ahorro) throws UFNoDisponibleException {

		LOGGER.log(Level.INFO, "<Trabajo DevOps - DXC> <Simula 10%>");

		Date dia = new Date();
		UFProvider ufProvider = new UFProviderMiIndicador();
		Calculadora calculadora = new Calculadora(ufProvider);
		DxcImpuestoSaldo response = calculadora.calcular(dia, sueldo, ahorro);
		return response;
	}

	@GetMapping(path = "/impuesto", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Impuesto getImpuesto(
			@RequestParam(name = "sueldo") long sueldo,
			@RequestParam(name = "ahorro") long ahorro) throws UFNoDisponibleException {

		LOGGER.log(Level.INFO, "<Trabajo DevOps - DXC> <Consulta Impuesto>");

		Date dia = new Date();
		UFProvider ufProvider = new UFProviderMiIndicador();
		CalculadoraImpuesto calculadora = new CalculadoraImpuesto(ufProvider);
		Impuesto response = calculadora.calcular(dia, sueldo, ahorro);
		return response;
	}

	@GetMapping(path = "/dxc", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Dxc getDxc(
			@RequestParam(name = "sueldo") long sueldo,
			@RequestParam(name = "ahorro") long ahorro) throws UFNoDisponibleException {

		LOGGER.log(Level.INFO, "<Trabajo DevOps - DXC> <Consulta 10%>");

		Date dia = new Date();
		UFProvider ufProvider = new UFProviderMiIndicador();
		CalculadoraDxc calculadora = new CalculadoraDxc(ufProvider);
		Dxc response = calculadora.calcular(dia, sueldo, ahorro);
		return response;
	}

	@GetMapping(path = "/saldo", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Saldo getSaldo(
			@RequestParam(name = "sueldo") long sueldo,
			@RequestParam(name = "ahorro") long ahorro) throws UFNoDisponibleException {

		LOGGER.log(Level.INFO, "<Trabajo DevOps - DXC> <Consulta Saldo>");

		Date dia = new Date();
		UFProvider ufProvider = new UFProviderMiIndicador();
		CalculadoraSaldo calculadora = new CalculadoraSaldo(ufProvider);
		Saldo response = calculadora.calcular(dia, sueldo, ahorro);
		return response;
	}
}