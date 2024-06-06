package com.appLink.app.creation;

import com.appLink.app.database.entity.UrlEntity;
import org.springframework.stereotype.Service;

@Service
public class CreationMapper {

    public CreationResponse creationResponse(UrlEntity urlEntity) {
        return CreationResponse.builder()
                .id(urlEntity.getId())
                .shortUrl(urlEntity.getShortUrl())
                .longUrl(urlEntity.getLongUrl())
                .visited(urlEntity.getVisited())
                .createdAt(urlEntity.getCreatedAt())
                .expiredAt(urlEntity.getExpiredAt())
                .build();
    }
}
