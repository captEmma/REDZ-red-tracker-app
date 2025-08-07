package com.redz.financeportfolio.exception;

public class InsufficientFundsException extends RuntimeException {

    public InsufficientFundsException() {
        super("Not enough funds");
    }

}
