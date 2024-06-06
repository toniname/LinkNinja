package com.applink.app.urls;

import com.applink.app.database.entity.UserEntity;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UrlDTO {

    private long id;
    private String shortUrl;
    private String longUrl;
    private UserEntity user;
    private long visited;
    private LocalDateTime createdAt;
    private LocalDateTime expiredAt;
}
