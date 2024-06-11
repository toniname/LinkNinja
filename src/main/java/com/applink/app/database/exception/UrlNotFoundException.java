package com.applink.app.database.exception;

public class UrlNotFoundException extends RuntimeException {
    public UrlNotFoundException(String url) {
        super(url);
    }
}
