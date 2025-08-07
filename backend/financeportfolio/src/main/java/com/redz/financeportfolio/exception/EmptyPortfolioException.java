package com.redz.financeportfolio.exception;

public class EmptyPortfolioException extends Exception{

    public EmptyPortfolioException(){
        super("The portfolio is empty");
    }
}
