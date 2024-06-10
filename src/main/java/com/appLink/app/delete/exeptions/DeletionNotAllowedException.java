package com.appLink.app.delete.exeptions;

public class DeletionNotAllowedException extends RuntimeException {

    public DeletionNotAllowedException(String message) {
        super(message);
    }
}