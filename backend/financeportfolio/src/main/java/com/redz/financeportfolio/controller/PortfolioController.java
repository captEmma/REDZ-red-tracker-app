package com.redz.financeportfolio.controller;

import com.redz.financeportfolio.service.YahooFinanceService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/stocks")
public class PortfolioController {
    private YahooFinanceService yahooFinanceService;

    @GetMapping("/{stock}")
    public Map<String, Object> getStockData(
            @PathVariable String stock,
            @RequestParam String period,
            @RequestParam String interval){
        return yahooFinanceService.getStockData(stock, period, interval);
    }
}
