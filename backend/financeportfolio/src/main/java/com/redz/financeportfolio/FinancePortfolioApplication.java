package com.redz.financeportfolio;

import com.redz.financeportfolio.service.YahooFinanceService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@SpringBootApplication
public class FinancePortfolioApplication {

	public static void main(String[] args) {

		SpringApplication.run(FinancePortfolioApplication.class, args);
		RestTemplate restTemplate = new RestTemplate();

		YahooFinanceService yahooFinanceService = new YahooFinanceService();

		//Map<String, Object> responseBody = yahooFinanceService.getStockData("TSLA", "5d", "1d");
		//String url = "http://localhost:5000/stocks/TSLA";

		//ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
		//System.out.println(responseBody.get("data"));
		//System.out.println("Received JSON: " + response.getBody());

	}
}
