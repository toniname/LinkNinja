package com.applink.app.update;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateRequest {
    private String shortUrl;
    private String longUrl;
}
