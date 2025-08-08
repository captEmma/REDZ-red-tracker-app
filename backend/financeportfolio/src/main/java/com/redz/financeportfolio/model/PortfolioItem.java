package com.redz.financeportfolio.model;

import com.redz.financeportfolio.util.Companies;
import jakarta.persistence.*;

@Entity
@Table(name = "portfolio_item")
public class PortfolioItem {
    @Id
    @Column(length = 10)
    private String symbol;
    private String companyName;
    private double shares;
    private double purchasePrice;

    public PortfolioItem() {}

    public PortfolioItem(String symbol, double shares, double purchasePrice) {
        this.symbol = symbol;
        this.companyName = Companies.COMPANYNAMES.get(symbol);
        this.shares = shares;
        this.purchasePrice = purchasePrice;
        //TODO look up the company name from the list (column CAN be null, so no error if we can't find it)
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

    public String getSymbol() {
        return symbol;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
