package com.redz.financeportfolio.controller;

import com.redz.financeportfolio.model.StockData;
import com.redz.financeportfolio.service.YahooFinanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/stocks")
public class PortfolioController {
    @Autowired
    private YahooFinanceService yahooFinanceService;

    public PortfolioController(YahooFinanceService yahooFinanceService){
        this.yahooFinanceService = yahooFinanceService;
    }

    @GetMapping("/{stock}")
    public ResponseEntity<StockData> getStockData(
            @PathVariable String stock,
            @RequestParam(defaultValue = "1d") String period,
            @RequestParam(defaultValue = "1d") String interval) {
        StockData stockData = yahooFinanceService.getStockData(stock, period, interval);
        return ResponseEntity.ok(stockData);
    }
}