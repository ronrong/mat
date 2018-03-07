package com.ronrong.thymeleaf.mat.exception;

public abstract class MatException extends RuntimeException {

    private static final long serialVersionUID = -1080862110715121407L;

    protected MatException(final String message, final Throwable cause) {
        super(message, cause);
    }

    protected MatException(final String message) {
        super(message);
    }
    
}
