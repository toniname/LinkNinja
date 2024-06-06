package com.appLink.app.url;


import com.appLink.app.database.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UrlResponse {

    private long id;
    private UserEntity user;
    private String shortUrl;
    private long visited;
    private LocalDateTime createdAt;
    private LocalDateTime expiredAt;
}