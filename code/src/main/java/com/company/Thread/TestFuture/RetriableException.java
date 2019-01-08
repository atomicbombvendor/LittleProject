package com.company.Thread.TestFuture;

public class RetriableException extends RuntimeException {

    public RetriableException(Throwable cause) {
        super(cause);
    }

    public RetriableException(String message) {
        super(message);
    }

    public RetriableException(String message, Throwable cause) {
        super(message, cause);
    }
}