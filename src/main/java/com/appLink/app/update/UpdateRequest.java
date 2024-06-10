package com.appLink.app.update;

import lombok.Data;

@Data
public class UpdateRequest {
    private String shortUrl;
    private String longUrl;
}