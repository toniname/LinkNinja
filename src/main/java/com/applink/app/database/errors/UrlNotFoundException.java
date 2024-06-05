package com.applink.app.database.errors;

public class UrlNotFoundException extends RuntimeException {
    public UrlNotFoundException(String url) {
        super(url);
    }
}
