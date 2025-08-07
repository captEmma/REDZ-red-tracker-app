package com.redz.financeportfolio.controller;

import com.redz.financeportfolio.exception.YahooApiException;
import com.redz.financeportfolio.model.PortfolioItem;
import com.redz.financeportfolio.model.User;
import com.redz.financeportfolio.service.PortfolioService;
import com.redz.financeportfolio.model.StockData;
import com.redz.financeportfolio.service.YahooFinanceService;
import com.redz.financeportfolio.util.Companies;
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

    @PutMapping("/buy/{symbol}/{cost}")
    public ResponseEntity<?> buyShares(@PathVariable String symbol, @PathVariable double cost){
        try {
            PortfolioItem portfolioItem = portfolioService.buyShares(symbol, cost);
            return ResponseEntity.ok(portfolioItem);
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/sell/{symbol}/{numberOfShares}")
    public ResponseEntity<?> sellShares(@PathVariable String symbol, @PathVariable double numberOfShares){
        try {
            PortfolioItem portfolioItem = portfolioService.sellShares(symbol, numberOfShares);
            return ResponseEntity.ok(portfolioItem);
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/user/networth")
    public ResponseEntity<?> getNetworth(){
        try {
            double value = portfolioService.getNetworth();
            return ResponseEntity.ok(value);
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/allcompanies")
    public List<Map<String, String>> getAllCompanies() {
        return Companies.getAll();
    }

    @GetMapping("/user")
    public User getUser(){
        return portfolioService.getUser();
    }

    @GetMapping("/user/all")
    public List<User> getUsers(){
        return portfolioService.getAllUsers();
    }

    @GetMapping("/yahoo/{stock}")
    public ResponseEntity<?> getStockData(
            @PathVariable String stock,
            @RequestParam(defaultValue = "1d") String period,
            @RequestParam(defaultValue = "1d") String interval) {
        try {
            StockData stockData = yahooFinanceService.getStockData(stock, period, interval);
            return ResponseEntity.ok(stockData);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}