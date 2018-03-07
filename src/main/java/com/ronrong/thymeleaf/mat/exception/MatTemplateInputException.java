package com.ronrong.thymeleaf.mat.exception;

public class MatTemplateInputException extends MatExecuteException {

    private static final long serialVersionUID = 1519786970L;

    public MatTemplateInputException(final String message) {
        super(message);
    }
    
    public MatTemplateInputException(final String message, final Throwable cause) {
        super(message, cause);
    }



    
}
