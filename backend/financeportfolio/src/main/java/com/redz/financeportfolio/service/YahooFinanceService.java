package com.redz.financeportfolio.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.redz.financeportfolio.model.StockData;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class YahooFinanceService {
    private final RestTemplate restTemplate = new RestTemplate();
    public StockData getStockData(String stock, String period, String interval){
        String url = "http://localhost:5000/stocks/" + period + "/" + interval + "/" + stock;

        try{
            /**Whole HTTP response as Map (data and actions) requested from flask app (GET request)**/
            ResponseEntity<Map> response = restTemplate.getForEntity(url, Map.class);

            if(response.getStatusCode().is2xxSuccessful() && response.getBody()!=null){
                String dataJson = (String) response.getBody().get("data");
                String actionJson = (String) response.getBody().get("actions");

                ObjectMapper mapper = new ObjectMapper();
                Map<String, Map<Long, Double>> data = mapper.readValue(dataJson, new TypeReference<>() {});
                Map<String, Map<Long, Double>> actions = mapper.readValue(actionJson, new TypeReference<>() {});

                Map<Long, Double> open = data.get("Open");
                Map<Long, Double> high = data.get("High");
                Map<Long, Double> low = data.get("Low");
                Map<Long, Double> close = data.get("Close");
                Map<Long, Double> volume = data.get("Volume");
                Map<Long, Double> dividends = actions.get("Dividends");
                Map<Long, Double> stockSplits = actions.get("Stock Splits");

                StockData stockData = new StockData();
                stockData.setOpen(open);
                stockData.setHigh(high);
                stockData.setLow(low);
                stockData.setClose(close);
                stockData.setVolume(volume);
                stockData.setDividends(dividends);
                stockData.setStockSplits(stockSplits);
                return stockData;
            } else{
                throw new RuntimeException("Bad request from python - flask service");
            }
        } catch (Exception e){
            throw new RuntimeException("Failed to connect to flask server");
        }
    }

    public double getCurrentPrice(String symbol) {
        for(Double value : getStockData(symbol, "1d", "1d").getClose().values())
            return value;
        return 0;
    }
}
