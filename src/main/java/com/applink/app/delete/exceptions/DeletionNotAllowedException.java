package com.applink.app.delete.exceptions;

public class DeletionNotAllowedException extends RuntimeException {

    public DeletionNotAllowedException(String message) {
        super(message);
    }
}
