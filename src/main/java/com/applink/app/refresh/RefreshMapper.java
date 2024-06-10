package com.applink.app.refresh;

import com.applink.app.database.entity.UrlEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
@Component
public class RefreshMapper {
    @Value("${server.address}")
    private String serverAddress;

    public RefreshResponse refreshResponse(UrlEntity urlEntity) {
        return RefreshResponse.builder()
                .id(urlEntity.getId())
                .shortUrl(serverAddress + "/urls/" + urlEntity.getShortUrl())
                .longUrl(urlEntity.getLongUrl())
                .visited(urlEntity.getVisited())
                .ownedBy(urlEntity.getUser().getUsername())
                .createdAt(urlEntity.getCreatedAt())
                .expiredAt(urlEntity.getExpiredAt())
                .build();
    }
}