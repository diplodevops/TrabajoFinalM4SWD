package com.devops.dxc.devops;

import com.devops.dxc.devops.model.Util;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.beans.Transient;

import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DevopsApplicationTests {

	@Test
	void contextLoads() {

	}

	
	@Test
	void get10xc(){
		assertEquals(100000, Util.getDxc(100000, 100000));
		assertEquals(200000, Util.getDxc(200000, 20000));
	}

	@Test
	void getSaldo(){
		assertEquals(0, Util.getSaldo(100000, 100000));
		assertEquals(0, Util.getSaldo(350000, 100000));
	}


	@Test
	void getImpusto(){
		assertEquals(0, Util.getImpuesto(100000, 100000));
		assertEquals((100000*0.04) , Util.getImpuesto(100000, 1500001));
	}

	@Test 
	void getUf(){
		assertNotEquals(29000, Util.getUf());
		assertEquals(Util.getUf(), Util.getUf());
	}
	

}