package com.redz.financeportfolio.exception;

public class StockSymbolNotFoundException extends Exception{

    public StockSymbolNotFoundException() {
        super("Stock symbol not found");
    }

}
