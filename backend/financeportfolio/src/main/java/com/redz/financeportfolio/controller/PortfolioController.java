package com.redz.financeportfolio.controller;

import com.redz.financeportfolio.model.PortfolioItem;
import com.redz.financeportfolio.service.PortfolioService;
import com.redz.financeportfolio.model.StockData;
import com.redz.financeportfolio.service.YahooFinanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class PortfolioController {
    @Autowired
    private final YahooFinanceService yahooFinanceService;
    @Autowired
    private final PortfolioService portfolioService;

    public PortfolioController(YahooFinanceService yahooFinanceService, PortfolioService portfolioService){
        this.yahooFinanceService = yahooFinanceService;
        this.portfolioService = portfolioService;
    }

    @PostMapping("/add")
    public PortfolioItem addItem(@RequestBody PortfolioItem item){
        return portfolioService.addItem(item);
    }

    @DeleteMapping("/remove/{id}")
    public void removeItem(@PathVariable Integer id){
        portfolioService.removeItem(id);
    }

    @GetMapping("/all")
    public List<PortfolioItem> getAllItems(){
        return portfolioService.getAllItems();
    }

    @GetMapping("/yahoo/{stock}")
    public ResponseEntity<StockData> getStockData(
            @PathVariable String stock,
            @RequestParam(defaultValue = "1d") String period,
            @RequestParam(defaultValue = "1d") String interval) {
        StockData stockData = yahooFinanceService.getStockData(stock, period, interval);
        return ResponseEntity.ok(stockData);
    }
}