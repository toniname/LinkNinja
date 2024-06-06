package com.appLink.app.creation;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class CreationResponse {
    private long id;
    private String shortUrl;
    private String longUrl;
    private long visited;
    private LocalDateTime createdAt;
    private LocalDateTime expiredAt;
}