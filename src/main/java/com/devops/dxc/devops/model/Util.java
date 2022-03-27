package com.devops.dxc.devops.model;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.devops.dxc.devops.mi.MiIndicadorClient;
import com.devops.dxc.devops.mi.UnidadFomento;

import retrofit2.Response;

public class Util {

	static Double valorUf;
	public static final double MIN_UF = 35;
	public static final double MAX_UF = 150;
	private final static Logger LOGGER = LoggerFactory.getLogger(Util.class);

	/**
	 * Método para cacular el 10% del ahorro en la AFP. Las reglas de negocio se
	 * pueden conocer en
	 * https://www.previsionsocial.gob.cl/sps/preguntas-frecuentes-nuevo-retiro-seguro-10/
	 * 
	 * @param ahorro
	 * @param sueldo
	 * @return
	 */
	public static int getDxc(int ahorro, int sueldo) {
		double currentUFValue = getUf();
		System.out.println("currentUFValue: " + currentUFValue);
		double dxc = ahorro * 0.1;
		int totalRetiro;
		if ((dxc / currentUFValue) > MAX_UF) {
			totalRetiro = (int) (MAX_UF * currentUFValue);
		} else if (dxc <= (currentUFValue * MIN_UF) && ahorro >= (currentUFValue * MIN_UF)) {
			totalRetiro = (int) (currentUFValue * MIN_UF);
		} else if (ahorro <= (currentUFValue * MIN_UF)) {
			totalRetiro = (int) ahorro;
		} else {
			totalRetiro = (int) (ahorro * 0.1);
		}
		return Math.round(totalRetiro);
	}

	/**
	 * Método que retorna el valor de la UF. Este método debe ser refactorizado por
	 * una integración a un servicio que retorne la UF en tiempo real. Por ejemplo
	 * mindicador.cl
	 * 
	 * @return
	 * @throws IOException
	 */
	public static double getUf() {
		try {
			String fecha = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
			LOGGER.info("Fecha : " + fecha);
			Response<UnidadFomento> response = MiIndicadorClient.getClient().getUfValue(fecha)
					.execute();
			if (response.isSuccessful())
				valorUf = response.body().getSerie().get(0).getValor();
		} catch (Exception e) {
			LOGGER.info("Error getting UF value from 'miindicador.cl', using cached value.\n" + e.getMessage());
		}
		return valorUf;
	}

	enum TramoImpuestoRenta {
		TRAMO_EXENTO(0, 752004, 0),
		TRAMO_1(752004, 1671120, 2.20),
		TRAMO_2(1671120, 2785200, 4.52),
		TRAMO_3(2785200, 3899280, 7.09), 
		TRAMO_4(3899280, 5013360, 10.62), 
		TRAMO_5(5013360, 6684480, 15.57),
		TRAMO_6(6684480, 17268240, 27.48);

		int desde;
		int hasta;
		double impuesto;

		private TramoImpuestoRenta(int desde, int hasta, double impuesto) {
			this.desde = desde;
			this.hasta = hasta;
			this.impuesto = impuesto;
		}

		public static TramoImpuestoRenta findSectionBySalary(int sueldo) {
			return Arrays.asList(TramoImpuestoRenta.values()).stream().filter(t -> t.desde < sueldo && sueldo < t.hasta)
					.findFirst().orElse(TRAMO_6);
		}
	}

	public static int calcularImpuesto(int sueldoImponible, int dxc) {
		if (sueldoImponible < 0)
			throw new RuntimeException("Sueldo debe ser mayor a cero '0'.");
		else if (sueldoImponible < 1500000)
			return 0;
		else
			return (int) (dxc * TramoImpuestoRenta.findSectionBySalary(sueldoImponible).impuesto / 100);
	}
}
