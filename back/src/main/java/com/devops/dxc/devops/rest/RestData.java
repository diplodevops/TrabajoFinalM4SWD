package com.devops.dxc.devops.rest;

import java.util.logging.Level;
import java.util.logging.Logger;
import com.devops.dxc.devops.model.Dxc;
import com.devops.dxc.devops.model.Salida;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import com.devops.dxc.devops.model.Util;
import com.devops.dxc.devops.model.InputModel;


@RestController
@RequestMapping(path = "/rest/msdxc")
public class RestData {
	
	private final static Logger LOGGER = Logger.getLogger("devops.subnivel.Control");

	@GetMapping(path = "/dxc", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Dxc getData(@RequestParam(name = "sueldo") String sueldo, @RequestParam(name = "ahorro") String ahorro){
		LOGGER.log(Level.INFO, "< Trabajo DevOps - DXC > <Consultado Diez por ciento>");
        Dxc response = new Dxc(Integer.parseInt(ahorro), Integer.parseInt(sueldo));
		return response;
	}

	@RequestMapping("/saldo")
	public Salida getSaldo(@RequestParam(name = "sueldo") String sueldo, @RequestParam(name = "ahorro") String ahorro){
		return new Salida(Integer.parseInt(ahorro), Integer.parseInt(sueldo), "saldo");
	}

	@RequestMapping("/impuesto")
	public Salida getImpuesto(@RequestParam(name = "sueldo") String sueldo, @RequestParam(name = "ahorro") String ahorro){
		return new Salida(Integer.parseInt(ahorro), Integer.parseInt(sueldo), "impuesto");
	}

	@RequestMapping("/10x100")
	public Salida get10x100(@RequestParam(name = "sueldo") String sueldo, @RequestParam(name = "ahorro") String ahorro){
		return new Salida(Integer.parseInt(ahorro), Integer.parseInt(sueldo), "10x100");
	}

	@GetMapping(path="/get_uf", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody int test(){
		int uf = Util.getUf();
		return uf;
	}

	@PostMapping(path="/10", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Dxc getTen(@RequestBody Dxc inputDxc){
		int sueldo = inputDxc.getSueldo();
		int ahorro = inputDxc.getAhorro();

		System.out.println(sueldo);
		System.out.println(ahorro);

		Dxc response = new Dxc(sueldo, ahorro);
		return response;
	}

}
