package com.redz.financeportfolio.model;

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
    private double purchasePrice;
    private double shares;

    @CreationTimestamp
    private LocalDateTime purchaseDate;

    public Transaction(){}

    public Transaction(String symbol, double shares, double purchasePrice) {
        this.symbol = symbol;
        this.purchasePrice = purchasePrice;
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

    public LocalDateTime getPurchaseDate() {
        return purchaseDate;
    }
}
