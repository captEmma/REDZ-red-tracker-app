package com.redz.financeportfolio.service;

import com.redz.financeportfolio.model.PortfolioItem;
import com.redz.financeportfolio.repository.PortfolioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PortfolioService {
    private final PortfolioRepository repository;

    private int netWorth;

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

    public PortfolioItem addItem(PortfolioItem item){
        return repository.save(item);
    }

    public void removeItem(Integer id){
        repository.deleteById(id);
    }

    public List<PortfolioItem> getAllItems(){
        return repository.findAll();
    }
}
