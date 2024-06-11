package com.applink.app.url;

import com.applink.app.database.dto.UrlDto;
import com.applink.app.database.entity.UrlEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UrlMapper {

    public List<UrlDto> mapToUrlDtoList(List<UrlEntity> urls) {
        List<UrlDto> urlDtos = new ArrayList<>();
        if(urls != null && !urls.isEmpty()) {
            for (UrlEntity urlEntity : urls) {
                urlDtos.add(UrlDto.builder()
                                .id(urlEntity.getId())
                                .shortUrl(urlEntity.getShortUrl())
                                .longUrl(urlEntity.getLongUrl())
                                .user(urlEntity.getUser().getUsername())
                                .visited(urlEntity.getVisited())
                                .createdAt(urlEntity.getCreatedAt())
                                .expiredAt(urlEntity.getExpiredAt())
                        .build());
            }
        }
        return urlDtos;
    }
}
