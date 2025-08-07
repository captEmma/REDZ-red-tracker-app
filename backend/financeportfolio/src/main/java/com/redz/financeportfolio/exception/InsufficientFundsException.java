package com.redz.financeportfolio.exception;

public class InsufficientFundsException extends Exception {

    public InsufficientFundsException() {
        super("Not enough funds");
    }

}
