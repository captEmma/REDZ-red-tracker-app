package com.redz.financeportfolio.exception;

public class StockSymbolNotFoundException extends RuntimeException{

    public StockSymbolNotFoundException() {
        super("Stock symbol not found");
    }

}
