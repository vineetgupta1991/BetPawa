package com.betpawa.wallet.server.exception;

/**
 *
 * @author Yerlan
 */
public class NotValidAmountException extends Exception {

    public NotValidAmountException(String message) {
        super(message);
    }

    public NotValidAmountException(String message, Throwable cause) { super(message, cause); }
}
