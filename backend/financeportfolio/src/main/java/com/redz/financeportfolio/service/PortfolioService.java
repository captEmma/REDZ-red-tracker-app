package com.redz.financeportfolio.service;

import com.redz.financeportfolio.model.PortfolioItem;
import com.redz.financeportfolio.repository.PortfolioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PortfolioService {
    private final PortfolioRepository repository;

    public PortfolioService(PortfolioRepository repository){
        this.repository = repository;
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
