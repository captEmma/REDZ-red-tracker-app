package com.redz.financeportfolio.model;

import com.redz.financeportfolio.util.Companies;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 10)
    private String symbol;
    private String companyName;
    private double shares;
    private double cash;
    private double purchasePrice;

    @CreationTimestamp
    private LocalDateTime purchaseDate;

    public Transaction(){}

    public Transaction(String symbol, double shares, double purchasePrice, double cash) {
        this.symbol = symbol;
        this.companyName = Companies.COMPANYNAMES.get(symbol);
        this.purchasePrice = purchasePrice;
        this.cash = cash;
        this.shares = shares;
    }

    public Integer getId() {
        return id;
    }

    public String getSymbol() {
        return symbol;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public double getShares() {
        return shares;
    }

    public String getCompanyName() {
        return companyName;
    }

    public LocalDateTime getPurchaseDate() {
        return purchaseDate;
    }
}
