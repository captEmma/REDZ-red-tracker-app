package com.redz.financeportfolio.service;

import com.redz.financeportfolio.model.PortfolioItem;
import com.redz.financeportfolio.model.StockData;
import com.redz.financeportfolio.repository.PortfolioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PortfolioService {
    private final PortfolioRepository repository;

    private double cash = 500000;
    private int netWorth;
    //TODO: add fields to represent performance

    public PortfolioService(PortfolioRepository repository){
        this.repository = repository;
    }

    public int calculateNetWorth(){
        List<PortfolioItem> items = getAllItems();
        for(PortfolioItem item : items){
            String symbol = item.getSymbol();
            double shares = item.getShares();
            double currentPrice = new YahooFinanceService().getCurrentPrice(symbol);

            netWorth+=shares*currentPrice;
        }
        System.out.println(netWorth);
        return netWorth;
    }

    //TODO change signature to add item dynamically
    public PortfolioItem addItem(String symbol, double cost){
        if(cost>cash) {
            //TODO HANDLE ERROR
        }
        StockData stockData = new YahooFinanceService().getStockData(symbol, "1d", "1d");
        double purchasePrice = stockData.getCurrentPrice();
        double shares =  cost/purchasePrice;
        PortfolioItem item = new PortfolioItem(symbol, shares, purchasePrice);
        return repository.save(item);
    }

    public void removeItem(Integer id){
        repository.deleteById(id);
    }

    public List<PortfolioItem> getAllItems(){
        return repository.findAll();
    }
}
