package com.redz.financeportfolio.service;

import com.redz.financeportfolio.model.PortfolioItem;
import com.redz.financeportfolio.model.StockData;
import com.redz.financeportfolio.repository.PortfolioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public PortfolioItem addItem(String symbol, double cost){
        if(cost>cash) {
            //TODO HANDLE not enough money
        }
        StockData stockData = new YahooFinanceService().getStockData(symbol, "1d", "1d");
        double purchasePrice = stockData.getCurrentPrice();
        double shares =  cost/purchasePrice;
        //PortfolioItem item = new PortfolioItem(symbol, shares, purchasePrice);
        PortfolioItem updatedItem;
        Optional<PortfolioItem> existingStockOptional = repository.findById(symbol);

        if (existingStockOptional.isPresent()) {
            PortfolioItem existingStock = existingStockOptional.get();
//            existingStock.setShares(shares);
//            existingStock.setSymbol(symbol);
//            existingStock.setPurchasePrice(purchasePrice);

            double oldShares = existingStock.getShares();
            double oldPrice = existingStock.getPurchasePrice();

            double newShares = oldShares + shares;
            double newPrice = oldPrice + cost;

            updatedItem = new PortfolioItem(symbol, newShares, newPrice);
        } else {
            updatedItem = new PortfolioItem(symbol, shares, cost);
        }

        return repository.save(updatedItem);
    }

    public void removeItem(String symbol, double shares){
        //TODO make this a PUT request as well
        //repository.deleteById(id);
    }

    public List<PortfolioItem> getAllItems(){
        return repository.findAll();
    }
}
