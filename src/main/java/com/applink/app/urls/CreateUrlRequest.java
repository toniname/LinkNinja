package com.applink.app.urls;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUrlRequest extends UrlRequest {

    public CreateUrlRequest() {}
    public  CreateUrlRequest(String longUrl) {
        super(longUrl);
    }
}
