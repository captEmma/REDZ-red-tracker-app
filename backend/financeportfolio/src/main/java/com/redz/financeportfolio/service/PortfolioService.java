package com.redz.financeportfolio.service;

import com.redz.financeportfolio.exception.*;
import com.redz.financeportfolio.model.*;
import com.redz.financeportfolio.repository.PortfolioRepository;
import com.redz.financeportfolio.repository.TransactionRepository;
import com.redz.financeportfolio.repository.UserRepository;
import com.redz.financeportfolio.util.Companies;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PortfolioService {
    private final PortfolioRepository repository;
    private final UserRepository userRepository;
    private final TransactionRepository transactionRepository;
    private static final YahooFinanceService yahooFinanceService = new YahooFinanceService();
    private User currentUser;
    private List<PortfolioItem> performanceSorted = new ArrayList<>();
    //TODO: add fields to represent performance

    public PortfolioService(PortfolioRepository repository, UserRepository userRepository, TransactionRepository transactionRepository){
        this.repository = repository;
        this.userRepository = userRepository;
        currentUser = userRepository.findAll().getFirst();
        this.transactionRepository = transactionRepository;
    }

    public List<PortfolioItem> getItemsSortedByPerformance(){
        performanceSorted = repository.findAll().stream()
                .sorted((first, second) -> {
                    try {
                        double secondPrice = yahooFinanceService.getCurrentPrice(second.getSymbol());
                        double firstPrice = yahooFinanceService.getCurrentPrice(first.getSymbol());

                        double secondValue = second.getShares() * secondPrice;
                        double firstValue = first.getShares() * firstPrice;

                        return Double.compare(secondValue/second.getPurchasePrice(), firstValue/first.getPurchasePrice());
                    } catch (YahooApiException e) {
                        throw new RuntimeException(e);
                    }
                })
                .toList();
        return performanceSorted;
    }

    public List<PortfolioItem> getTopNGainers(int n) throws Exception {
        if(n > repository.count())
            throw new InsufficientSharesException();
        if(performanceSorted.isEmpty())
            throw new Exception("Load performance first!");
        return performanceSorted.subList(0, n);
    }

    public List<PortfolioItem> getTopNLosers(int n) throws Exception {
        if(n > repository.count())
            throw new InsufficientSharesException();
        if(performanceSorted.isEmpty())
            throw new Exception("Load performance first!");
        return performanceSorted.reversed().subList(0, n);
    }

    public List<Transaction> getRecentInvestments() {
        List<Transaction> transactions = transactionRepository.findAll();
        Collections.reverse(transactions);
        return transactions.stream()
                .limit(10)
                .collect(Collectors.toList());
    }

    public double getNetworth() throws YahooApiException, EmptyPortfolioException {
        List<PortfolioItem> items = getAllItems();
        if (items.isEmpty()) {
            throw new EmptyPortfolioException();
        }
        double portfolioValue = 0;
        for(PortfolioItem item : items){
            String symbol = item.getSymbol();
            double shares = item.getShares();
            double currentPrice;
            currentPrice = yahooFinanceService.getCurrentPrice(symbol);

            portfolioValue+=shares*currentPrice;
        }
        return portfolioValue + currentUser.getCash();
    }

    public PortfolioItem buyShares(String symbol, double cost) throws InsufficientFundsException, StockSymbolNotFoundException {
        if(cost>currentUser.getCash()) {
            throw new InsufficientFundsException();
        }
        currentUser.buyStock(cost);
        userRepository.save(currentUser);

        double purchasePrice;
        try {
            StockData stockData =  yahooFinanceService.getStockData(symbol, "1d", "1d");
            purchasePrice = stockData.getCurrentPrice();

        } catch (Exception e){
            throw new StockSymbolNotFoundException();
        }

        double shares =  cost/purchasePrice;

        Optional<PortfolioItem> existingStockOptional = repository.findById(symbol);
        PortfolioItem savedItem;

        if (existingStockOptional.isPresent()) {
            PortfolioItem existingStock = existingStockOptional.get();

            existingStock.buyStock(shares, cost);
            savedItem = existingStock;
        }else {
            savedItem = new PortfolioItem(symbol, shares, cost);
        }
        repository.save(savedItem);
        transactionRepository.save(new Transaction(symbol, shares, purchasePrice));
        return savedItem;
    }

    public PortfolioItem sellShares(String symbol, double shares) throws StockSymbolNotFoundException, InsufficientSharesException {
        double sellPrice;
        try {
            StockData stockData = yahooFinanceService.getStockData(symbol, "1d", "1d");
            sellPrice = stockData.getCurrentPrice();
        } catch (Exception e){
            throw new StockSymbolNotFoundException();
        }

        Optional<PortfolioItem> existingStockOptional = repository.findById(symbol);

        if (existingStockOptional.isPresent()) {
            PortfolioItem existingStock = existingStockOptional.get();
            double oldShares = existingStock.getShares();

            if(oldShares >= shares) {
                double sellFor = shares * sellPrice;

                currentUser.sellStock(sellFor);
                userRepository.save(currentUser);

                existingStock.sellStock(shares, sellFor);
                PortfolioItem portfolioItem = repository.save(existingStock);
                transactionRepository.save(new Transaction(symbol, -shares, sellPrice));
                return portfolioItem;
            }
            else {
                throw new InsufficientSharesException();
            }
        } else {
            throw new StockSymbolNotFoundException();
        }
    }

    public List<PortfolioItem> getAllItems(){
        return repository.findAll();
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User getUser(){
        return userRepository.findAll().getFirst();
    }

    @Deprecated
    public double getDifference(String symbol) throws YahooApiException, StockSymbolNotFoundException {
        double currentPrice = yahooFinanceService.getCurrentPrice(symbol);
        Optional<PortfolioItem> itemOptional = repository.findById(symbol);
        if(itemOptional.isEmpty())
            throw new StockSymbolNotFoundException();
        return currentPrice - itemOptional.get().getPurchasePrice();
    }
}
