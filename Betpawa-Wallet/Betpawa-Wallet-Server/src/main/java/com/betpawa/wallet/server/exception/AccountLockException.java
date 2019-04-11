package com.betpawa.wallet.server.exception;

/**
 *
 * @author Yerlan
 */
public class AccountLockException extends RuntimeException {

    public AccountLockException(String message) {
        super(message);
    }

    public AccountLockException(String message, Throwable cause) {
        super(message, cause);
    }
}
