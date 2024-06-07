package com.applink.app.creation;

import com.applink.app.database.entity.UrlEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CreationMapper {

    @Value("${server.addres}")
    private String serverAddress;


    public CreationResponse creationResponse(UrlEntity urlEntity) {
        return CreationResponse.builder()
                .id(urlEntity.getId())
                .shortUrl(serverAddress+"/urls/"+urlEntity.getShortUrl())
                .longUrl(urlEntity.getLongUrl())
                .visited(urlEntity.getVisited())
                .ownedBy(urlEntity.getUser().getUsername())
                .createdAt(urlEntity.getCreatedAt())
                .expiredAt(urlEntity.getExpiredAt())
                .build();
    }
}
