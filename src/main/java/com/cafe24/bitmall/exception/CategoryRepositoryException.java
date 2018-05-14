package com.cafe24.bitmall.exception;

public class CategoryRepositoryException extends RuntimeException{
    public CategoryRepositoryException() {
        super();
    }

    public CategoryRepositoryException(String message) {
        super("Category Repository Exception Occurred..\n" + message);
    }

    public CategoryRepositoryException(String message, Throwable cause) {
        super(message, cause);
    }

    public CategoryRepositoryException(Throwable cause) {
        super(cause);
    }

    protected CategoryRepositoryException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
