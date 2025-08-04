package com.redz.financeportfolio.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

public class YahooFinanceService {
    private final RestTemplate restTemplate = new RestTemplate();
    public Map<String, Object> getStockData(String stock, String period, String interval){
        String url = "http://localhost:5000/stocks/" + period + "/" + interval + "/" + stock;

        try{
            /**Whole HTTP response as Map (data and actions) requested from flask app (GET request)**/
            ResponseEntity<Map> response = restTemplate.getForEntity(url, Map.class);

            if(response.getStatusCode().is2xxSuccessful() && response.getBody()!=null){
                Map<String, Object> result = new HashMap<>();

                String dataJson = (String) response.getBody().get("data");
                String actionJson = (String) response.getBody().get("actions");

                result.put("symbol", stock);
                result.put("data", dataJson);
                result.put("actions", actionJson);

                return result;
            } else{
                throw new RuntimeException("Bad request from python - flask service");
            }
        } catch (Exception e){
            throw new RuntimeException("Failed to connect to flask server");
        }
    }
}
