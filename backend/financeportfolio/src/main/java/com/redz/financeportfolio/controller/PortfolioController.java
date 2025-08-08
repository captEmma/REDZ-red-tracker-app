package com.redz.financeportfolio.controller;

import com.redz.financeportfolio.exception.InsufficientSharesException;
import com.redz.financeportfolio.exception.YahooApiException;
import com.redz.financeportfolio.model.PortfolioItem;
import com.redz.financeportfolio.model.Transaction;
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

    @GetMapping("/user/history")
    public ResponseEntity<?> getNethworthHistory(){
        try {
            Map<Long, Double> history = portfolioService.getNethworthHistory();
            return ResponseEntity.ok(history);
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

    @GetMapping("/allstocks")
    public List<PortfolioItem> getAllStocks(){
        return portfolioService.getAllItems();
    }

    @GetMapping("/user")
    public User getUser(){
        return portfolioService.getUser();
    }

    @GetMapping("/loadperformance")
    public List<PortfolioItem> getSortedItems(){
        return portfolioService.getItemsSortedByPerformance();
    }

    @GetMapping("/gainers")
    public ResponseEntity<?> getGainers(@RequestParam(defaultValue = "3") int n){
        try {
            return ResponseEntity.ok(portfolioService.getTopNGainers(n));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/losers")
    public ResponseEntity<?> getLosers(@RequestParam(defaultValue = "3") int n){
        try {
            return ResponseEntity.ok(portfolioService.getTopNLosers(n));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/user/recent")
    public ResponseEntity<?> getRecentInvestments(){
        try {
            List<Transaction> recents = portfolioService.getRecentInvestments();
            return ResponseEntity.ok(recents);
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
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