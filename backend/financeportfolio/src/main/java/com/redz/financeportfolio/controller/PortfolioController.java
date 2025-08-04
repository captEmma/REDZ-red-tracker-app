package com.redz.financeportfolio.controller;

import com.redz.financeportfolio.service.YahooFinanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/stocks")
public class PortfolioController {
    @Autowired
    private YahooFinanceService yahooFinanceService;

    public PortfolioController(YahooFinanceService yahooFinanceService){
        this.yahooFinanceService = yahooFinanceService;
    }

    @GetMapping("/{stock}")
    public Map<String, Object> getStockData(
            @PathVariable String stock,
            @RequestParam(defaultValue = "1d") String period,
            @RequestParam(defaultValue = "1d") String interval){
        return yahooFinanceService.getStockData(stock, period, interval);
    }
}
