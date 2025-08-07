package com.redz.financeportfolio.exception;

public class InsufficientSharesException extends Exception {

    public InsufficientSharesException() {
        super("Not enough shares");
    }

}
