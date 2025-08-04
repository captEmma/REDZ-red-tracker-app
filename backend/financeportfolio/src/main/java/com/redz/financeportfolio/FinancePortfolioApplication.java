package com.redz.financeportfolio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class FinancePortfolioApplication {

	public static void main(String[] args) {

		SpringApplication.run(FinancePortfolioApplication.class, args);
		RestTemplate restTemplate = new RestTemplate();

		String url = "http://localhost:5000/stocks/TSLA";

		ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

		System.out.println("Received JSON: " + response.getBody());

	}
}
