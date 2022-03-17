package com.devops.dxc.devops;

import com.devops.dxc.devops.model.Dxc;
import com.devops.dxc.devops.model.Util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

@SpringBootTest
class DevopsApplicationTests {

	@Test
	void contextLoads() {
	}

	@DisplayName("Asegurar el calculo del 10%")
	void testDxC(){
		assertEquals(4752267, Util.getDxc(55000000, 15000000));
	}
}

// 	void ensureThatUserAPICallReturnStatusCode200() throws Exception {
//         HttpClient client = HttpClient.newBuilder().build();
//         HttpRequest request = HttpRequest.newBuilder().uri(URI.create("http://localhost:9000/rest/msdxc/dxc?ahorro=55000000&sueldo=1500000")).build();
//         HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
//         fail("FIXME");

//     }
	
// 	@Test
//     @DisplayName("Ensures that the content type starts with application/json")
//     void ensureThatJsonIsReturnedAsContentType() throws Exception {
//         HttpClient client = HttpClient.newBuilder().build();
//         HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://api.github.com/users/vogella")).build();
//         HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
//         fail("FIXME");
//         // HINT Use response.headers()

//     }
// }
