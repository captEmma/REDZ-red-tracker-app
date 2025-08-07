package com.redz.financeportfolio.exception;

public class InsufficientSharesException extends RuntimeException {

    public InsufficientSharesException() {
        super("Not enough shares");
    }

}
