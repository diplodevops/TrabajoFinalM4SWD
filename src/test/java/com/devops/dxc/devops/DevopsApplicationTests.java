package com.devops.dxc.devops;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.devops.dxc.devops.mi.MiIndicadorClient;
import com.devops.dxc.devops.mi.MiIndicadorService;
import com.devops.dxc.devops.mi.UnidadFomento;
import com.devops.dxc.devops.mi.UnidadFomento.Serie;
import com.devops.dxc.devops.model.Dxc;
import com.devops.dxc.devops.model.Util;

import retrofit2.Call;
import retrofit2.mock.Calls;

// docker run --rm -ti -v $(pwd):/code -w /code -e TZ=America/Santiago -p 8080:8080 maven bash

@SpringBootTest
class DevopsApplicationTests {

	private static final double UF_MOCK_VALUE = 31000.00;
	static MockedStatic<MiIndicadorClient> miMockedStatic;

	@BeforeAll
	static void mockUfService() {
		miMockedStatic = Mockito.mockStatic(MiIndicadorClient.class);
		UnidadFomento uf = new UnidadFomento();
		Serie serie = new UnidadFomento.Serie();
		serie.setValor(UF_MOCK_VALUE);
		uf.setSerie(Arrays.asList(serie));
		miMockedStatic.when(MiIndicadorClient::getClient).thenReturn(new MiIndicadorService() {
			@Override
			public Call<UnidadFomento> getUfValue(String fecha) {
				return Calls.response(uf);
			}
		});
	}

	@Test
	public void withdrawal_with_savings_less_than_35_uf() throws IOException {
		int sueldo = 500000;
		int ahorro = (int) (Util.getUf() * (Util.MIN_UF - 1));
		int dxc = Util.getDxc(ahorro, sueldo);
		assertEquals(ahorro, dxc);
	}

	@Test
	public void withdrawal_with_savings_between_35_and_350_uf() {
		int sueldo = 500000;
		int ahorro = (int) (Util.getUf() * 200);
		Dxc dxc = new Dxc(ahorro, sueldo, Util.getUf());
		assertEquals(Util.getUf() * Util.MIN_UF, dxc.getDxc());
	}

	@Test
	public void withdrawal_with_savings_between_350_and_1500_uf() {
		int sueldo = 500000;
		int ahorro = (int) (Util.getUf() * 925);
		Dxc dxc = new Dxc(ahorro, sueldo, Util.getUf());
		assertEquals(ahorro * 0.1, dxc.getDxc());
	}

	@Test
	public void withdrawal_with_savings_greater_than_1500_uf() {
		int sueldo = 500000;
		int ahorro = (int) (Util.getUf() * 1501);
		Dxc dxc = new Dxc(ahorro, sueldo, Util.getUf());
		double expectedResult = Util.getUf() * Util.MAX_UF;
		assertEquals(expectedResult, dxc.getDxc());
	}

	@Test
	public void balance_with_savings_less_than_35_uf() {
		int sueldo = 500000;
		int ahorro = (int) (Util.getUf() * (Util.MIN_UF - 1));
		Dxc dxc = new Dxc(ahorro, sueldo, Util.getUf());
		assertEquals(0, dxc.getSaldo());
	}

	@Test
	public void balance_with_savings_between_35_and_350_uf() {
		int sueldo = 500000;
		int ahorro = (int) (Util.getUf() * 200);
		Dxc dxc = new Dxc(ahorro, sueldo, Util.getUf());
		assertEquals(ahorro - dxc.getDxc(), dxc.getSaldo());
	}

	@Test
	public void balance_with_savings_between_350_and_1500_uf() {
		int sueldo = 500000;
		int ahorro = (int) (Util.getUf() * 925);
		Dxc dxc = new Dxc(ahorro, sueldo, Util.getUf());
		assertEquals(ahorro - dxc.getDxc(), dxc.getSaldo());
	}

	@Test
	public void balance_with_savings_greater_than_1500_uf() {
		int sueldo = 500000;
		int ahorro = (int) (Util.getUf() * 1501);
		Dxc dxc = new Dxc(ahorro, sueldo, Util.getUf());
		assertEquals(ahorro - dxc.getDxc(), dxc.getSaldo());
	}

	@Test
	public void taxes_with_salary_less_than_1500000() {
		int sueldo = 1499999;
		int ahorro = (int) (Util.getUf() * 1500);
		Dxc dxc = new Dxc(ahorro, sueldo, Util.getUf());
		assertEquals(0, dxc.getImpuesto());
	}

	@Test
	public void taxes_with_salary_tramo_1() {
		int sueldo = 1500000;
		int ahorro = (int) (Util.getUf() * 1500);
		Dxc dxc = new Dxc(ahorro, sueldo, Util.getUf());
		int expectedResult = (int) (dxc.getDxc() * 2.20 / 100);
		assertEquals(expectedResult, dxc.getImpuesto());
	}

	@Test
	public void taxes_with_salary_tramo_2() {
		int sueldo = 1800000;
		int ahorro = (int) (Util.getUf() * 1500);
		Dxc dxc = new Dxc(ahorro, sueldo, Util.getUf());
		int expectedResult = (int) (dxc.getDxc() * 4.52 / 100);
		assertEquals(expectedResult, dxc.getImpuesto());
	}

	@Test
	public void taxes_with_salary_tramo_3() {
		int sueldo = 3000000;
		int ahorro = (int) (Util.getUf() * 1500);
		Dxc dxc = new Dxc(ahorro, sueldo, Util.getUf());
		int expectedResult = (int) (dxc.getDxc() * 7.09 / 100);
		assertEquals(expectedResult, dxc.getImpuesto());
	}

	@Test
	public void taxes_with_salary_tramo_4() {
		int sueldo = 4500000;
		int ahorro = (int) (Util.getUf() * 1500);
		Dxc dxc = new Dxc(ahorro, sueldo, Util.getUf());
		int expectedResult = (int) (dxc.getDxc() * 10.62 / 100);
		assertEquals(expectedResult, dxc.getImpuesto());
	}

	@Test
	public void taxes_with_salary_tramo_5() {
		int sueldo = 6000000;
		int ahorro = (int) (Util.getUf() * 1500);
		Dxc dxc = new Dxc(ahorro, sueldo, Util.getUf());
		int expectedResult = (int) (dxc.getDxc() * 15.57 / 100);
		assertEquals(expectedResult, dxc.getImpuesto());
	}

	@Test
	public void taxes_with_salary_tramo_6() {
		int sueldo = 10000000;
		int ahorro = (int) (Util.getUf() * 1500);
		Dxc dxc = new Dxc(ahorro, sueldo, Util.getUf());
		int expectedResult = (int) (dxc.getDxc() * 27.48 / 100);
		assertEquals(expectedResult, dxc.getImpuesto());
	}

	@Test
	public void withdrawal_with_no_salary() {
		Dxc dxc = new Dxc(10000000, 0, Util.getUf());
		assertEquals(0, dxc.getImpuesto());
	}
}
