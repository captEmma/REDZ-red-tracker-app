package com.redz.financeportfolio.service;

import com.redz.financeportfolio.exception.InsufficientFundsException;
import com.redz.financeportfolio.exception.InsufficientSharesException;
import com.redz.financeportfolio.exception.StockSymbolNotFoundException;
import com.redz.financeportfolio.model.PortfolioItem;
import com.redz.financeportfolio.model.StockData;
import com.redz.financeportfolio.model.Transaction;
import com.redz.financeportfolio.model.User;
import com.redz.financeportfolio.repository.PortfolioRepository;
import com.redz.financeportfolio.repository.TransactionRepository;
import com.redz.financeportfolio.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PortfolioService {
    private final PortfolioRepository repository;
    private final UserRepository userRepository;
    private final TransactionRepository transactionRepository;

    private User currentUser;
    private int netWorth;
    //TODO: add fields to represent performance

    public PortfolioService(PortfolioRepository repository, UserRepository userRepository, TransactionRepository transactionRepository){
        this.repository = repository;
        this.userRepository = userRepository;
        currentUser = userRepository.findAll().getFirst();
        this.transactionRepository = transactionRepository;
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
            throw new InsufficientFundsException();
        }
        currentUser.buyStock(cost);
        userRepository.save(currentUser);

        double purchasePrice;
        try {
            StockData stockData = new YahooFinanceService().getStockData(symbol, "1d", "1d");
            purchasePrice = stockData.getCurrentPrice();

        } catch (Exception e){
            throw new StockSymbolNotFoundException();
        }

        double shares =  cost/purchasePrice;

        Optional<PortfolioItem> existingStockOptional = repository.findById(symbol);
        transactionRepository.save(new Transaction(symbol, shares, purchasePrice));

        if (existingStockOptional.isPresent()) {
            PortfolioItem existingStock = existingStockOptional.get();

            existingStock.buyStock(shares, cost);
            return repository.save(existingStock);
        }

        return repository.save(new PortfolioItem(symbol, shares, cost));
    }

    public PortfolioItem sellShares(String symbol, double shares){
        double sellPrice;
        try {
            StockData stockData = new YahooFinanceService().getStockData(symbol, "1d", "1d");
            sellPrice = stockData.getCurrentPrice();
        } catch (Exception e){
            throw new StockSymbolNotFoundException();
        }

        Optional<PortfolioItem> existingStockOptional = repository.findById(symbol);
        transactionRepository.save(new Transaction(symbol, -shares, sellPrice));

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
        throw new InsufficientSharesException();
    }

    public List<PortfolioItem> getAllItems(){
        return repository.findAll();
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User getUser(Integer id){
        Optional<User> userOptional = userRepository.findById(id);
        return userOptional.orElse(null);
    }
}
