package com.redz.financeportfolio.service;

import com.redz.financeportfolio.model.PortfolioItem;
import com.redz.financeportfolio.model.StockData;
import com.redz.financeportfolio.model.User;
import com.redz.financeportfolio.repository.PortfolioRepository;
import com.redz.financeportfolio.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PortfolioService {
    private final PortfolioRepository repository;
    private final UserRepository userRepository;

    private User currentUser;
    private int netWorth;
    //TODO: add fields to represent performance

    public PortfolioService(PortfolioRepository repository, UserRepository userRepository){
        this.repository = repository;
        this.userRepository = userRepository;
        currentUser = userRepository.findAll().getFirst();
    }

    public double calculateNetWorth(){
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

    public PortfolioItem buyShares(String symbol, double cost){
        if(cost>currentUser.getCash()) {
            //TODO figure out what to do with exceptions
            throw new RuntimeException("Not enough cash!");
        }
        currentUser.buyStock(cost);
        userRepository.save(currentUser);

        StockData stockData = new YahooFinanceService().getStockData(symbol, "1d", "1d");

        double purchasePrice = stockData.getCurrentPrice();
        double shares =  cost/purchasePrice;

        Optional<PortfolioItem> existingStockOptional = repository.findById(symbol);

        if (existingStockOptional.isPresent()) {
            PortfolioItem existingStock = existingStockOptional.get();

            existingStock.buyStock(shares, cost);
            return repository.save(existingStock);
        }

        return repository.save(new PortfolioItem(symbol, shares, cost));
    }

    public PortfolioItem sellShares(String symbol, double shares){
        StockData stockData = new YahooFinanceService().getStockData(symbol, "1d", "1d");
        double sellPrice = stockData.getCurrentPrice();

        Optional<PortfolioItem> existingStockOptional = repository.findById(symbol);

        if (existingStockOptional.isPresent()) {
            PortfolioItem existingStock = existingStockOptional.get();
            double oldShares = existingStock.getShares();

            if(oldShares >= shares) {
                double sellFor = shares * sellPrice;

                currentUser.sellStock(sellFor);
                userRepository.save(currentUser);

                existingStock.sellStock(shares, sellFor);
                return repository.save(existingStock);
            }
        }
        //TODO figure out what to do with exceptions
        throw new RuntimeException("Couldn't sell stocks");
    }

    public List<PortfolioItem> getAllItems(){
        return repository.findAll();
    }
}
