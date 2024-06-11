package com.applink.app.database.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UrlDto {
    private Long id;
    private String shortUrl;
    private String longUrl;
    private String user;
    private Long visited;
    private LocalDateTime createdAt;
    private LocalDateTime expiredAt;
}
