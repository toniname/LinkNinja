package com.appLink.app.url;


import com.appLink.app.database.entity.UserEntity;
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
