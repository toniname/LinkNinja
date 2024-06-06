package com.applink.app.creation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreationResponse {
    private Long id;
    private String shortUrl;
    private String longUrl;
    private Long visited;
    private String ownedBy;
    private LocalDateTime createdAt;
    private LocalDateTime expiredAt;
}
