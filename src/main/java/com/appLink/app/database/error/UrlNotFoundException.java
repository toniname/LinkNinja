package com.appLink.app.database.error;

public class UrlNotFoundException extends RuntimeException {
    public UrlNotFoundException(String url) {
        super(url);
    }
}
