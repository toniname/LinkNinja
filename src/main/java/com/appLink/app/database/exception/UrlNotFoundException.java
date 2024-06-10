package com.appLink.app.database.exception;

public class UrlNotFoundException extends RuntimeException {
    public UrlNotFoundException(String url) {
        super(url);
    }
}
