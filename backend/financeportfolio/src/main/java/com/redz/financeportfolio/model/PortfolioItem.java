package com.redz.financeportfolio.model;

import jakarta.persistence.*;

@Entity
@Table(name = "portfolio_item")
public class PortfolioItem {
    @Id
    private String symbol;
    private double shares;
    private double purchasePrice;

    public PortfolioItem() {}

    public PortfolioItem(String symbol, double shares, double purchasePrice) {
        this.symbol = symbol;
        this.shares = shares;
        this.purchasePrice = purchasePrice;
    }

    public void buyStock(double sharesToBuy, double cost){
        shares += sharesToBuy;
        purchasePrice += cost;
    }

    public void sellStock(double shares, double sellPrice){
        this.shares -= shares;
        purchasePrice -= sellPrice;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public double getShares() {
        return shares;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}
