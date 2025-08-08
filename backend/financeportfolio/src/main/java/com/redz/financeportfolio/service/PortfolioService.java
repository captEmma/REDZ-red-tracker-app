package com.redz.financeportfolio.service;

import com.redz.financeportfolio.exception.*;
import com.redz.financeportfolio.model.*;
import com.redz.financeportfolio.repository.PortfolioRepository;
import com.redz.financeportfolio.repository.TransactionRepository;
import com.redz.financeportfolio.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PortfolioService {
    private final PortfolioRepository repository;
    private final UserRepository userRepository;
    private final TransactionRepository transactionRepository;
    private static final YahooFinanceService yahooFinanceService = new YahooFinanceService();
    private User currentUser;
    private List<ItemPerformanceDTO> performanceSorted = new ArrayList<>();
    //TODO: add fields to represent performance

    public PortfolioService(PortfolioRepository repository, UserRepository userRepository, TransactionRepository transactionRepository){
        this.repository = repository;
        this.userRepository = userRepository;
        currentUser = userRepository.findAll().getFirst();
        this.transactionRepository = transactionRepository;
    }
    //TODO display changes (%?)
    public List<ItemPerformanceDTO> getItemsSortedByPerformance(){
        List<PortfolioItem> items = repository.findAll();
        Map<PortfolioItem, Double> currentPriceMap = new HashMap<>();
        Map<PortfolioItem, Double> evaluation = new HashMap<>();

        for(PortfolioItem item : items){
            try {
                double currentPrice = yahooFinanceService.getCurrentPrice(item.getSymbol());
                currentPriceMap.put(item, currentPrice);
            } catch (YahooApiException e) {
                throw new RuntimeException(e);
            }
        }

        return items.stream()
                .map(item ->{
                    double currentValue = item.getShares()*currentPriceMap.get(item);
                    double performance = (currentValue / item.getPurchasePrice()) - 1;

                    return new ItemPerformanceDTO(item, performance);
                })
                 .sorted((first, second) ->
                         Double.compare(second.performance(), first.performance()))
                 .toList();
    }

    public List<ItemPerformanceDTO> getTopNGainers(int n) throws Exception {
        if(n > repository.count())
            throw new InsufficientSharesException();
        if(performanceSorted.isEmpty())
            throw new Exception("Load performance first!");
        return performanceSorted.subList(0, n);
    }

    public List<ItemPerformanceDTO> getTopNLosers(int n) throws Exception {
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
        transactionRepository.save(new Transaction(symbol, shares, -purchasePrice));
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
