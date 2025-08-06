package com.redz.financeportfolio.model;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String username;
    private double cash;

    public User(){}

    public User(Integer id, String username, double cash){
        this.username = username;
        this.cash = cash;
    }

    public void buyStock(double cost){
        cash -= cost;
    }

    public void sellStock(double sellPrice){
        cash += sellPrice;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public double getCash() {
        return cash;
    }
}
