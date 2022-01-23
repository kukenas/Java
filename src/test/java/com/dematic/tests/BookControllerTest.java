package com.dematic.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import com.dematic.BookStorageApplication;
import com.dematic.controller.BookController;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = BookStorageApplication.class)
public class BookControllerTest {

	@LocalServerPort
	private int port;

	private String baseURI = "http://localhost:";

	@Autowired
	private BookController controller;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	void contextLoads() throws Exception {
		assertNotNull(controller);
	}

	@Test
	public void createBookNotNull() throws Exception {
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(baseURI + port).path("/books")
				.queryParam("author", "Jack London").queryParam("name", "Harry Potter").queryParam("barcode", 171717)
				.queryParam("quantity", 10).queryParam("price", 1.10);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> requestEntity = new HttpEntity<>(headers);
		ResponseEntity<String> response = restTemplate.exchange(builder.build().encode().toUri(), HttpMethod.POST,
				requestEntity, String.class);
		assertEquals(200, response.getStatusCodeValue());
	}

	@Test
	public void allBooksNotNull() throws Exception {
		assertNotNull(restTemplate.getForObject("http://localhost:" + port + "/books", String.class));
	}

}
