package com.applink.app.update;

import com.applink.app.database.dto.UrlDto;
import com.applink.app.database.entity.UrlEntity;
import com.applink.app.database.exception.UrlNotFoundException;
import com.applink.app.database.service.UrlService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.time.LocalDateTime;

import static com.applink.app.creation.CreationService.getString;

@Service
@RequiredArgsConstructor
@Slf4j
public class UpdateService {
    private final UrlService urlService;

    @Value("${address}")
    private String serverAddress;

    public UpdateResponse update(UpdateRequest updateRequest, String username) {
        try {
            String s = validateUrl(updateRequest.getLongUrl());
            UrlEntity urlEntity = urlService.findUrlEntityByShortUrl(updateRequest.getShortUrl());
            if (urlEntity == null) {
                throw new UrlNotFoundException("Url not found");
            }
            if (isOwnedByUser(urlEntity, username) && isUrlDateValid(urlEntity.getExpiredAt())
                    && isLinkActive(s)) {
                urlEntity.setLongUrl(updateRequest.getLongUrl());
                UrlEntity updated = urlService.updateUrlEntity(urlEntity);
                return UpdateResponse.ok(mapEntityToDto(updated));
            }
            if (!isUrlDateValid(urlEntity.getExpiredAt())) {
                urlService.deleteUrlEntity(urlEntity);
                return UpdateResponse.error(UpdateResponse.UpdateError.linkExpired);
            }
        } catch (UrlNotFoundException e) {
            log.error(e.getMessage());
            throw e;
        }
        return UpdateResponse.error(UpdateResponse.UpdateError.belongsToAnotherUser);
    }

    private UrlDto mapEntityToDto(UrlEntity urlEntity) {
        return UrlDto.builder()
                .id(urlEntity.getId())
                .shortUrl(serverAddress + "/urls/" + urlEntity.getShortUrl())
                .longUrl(urlEntity.getLongUrl())
                .visited(urlEntity.getVisited())
                .user(urlEntity.getUser().getUsername())
                .createdAt(urlEntity.getCreatedAt())
                .expiredAt(urlEntity.getExpiredAt())
                .build();
    }

    private boolean isUrlDateValid(LocalDateTime dateTime) {
        LocalDateTime now = LocalDateTime.now();
        return now.isBefore(dateTime);
    }

    private boolean isOwnedByUser(UrlEntity urlEntity, String username) {
        return urlEntity.getUser().getUsername().compareTo(username) == 0;
    }

    private String validateUrl(String url) {
        return getString(url);
    }

    private boolean isLinkActive(String link) {
        return isValidUrl(link, log);
    }

    public static boolean isValidUrl(String link, Logger log) {
        try {
            URL url = URI.create(link).toURL();
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("HEAD");
            int responseCode = connection.getResponseCode();
            return (200 <= responseCode && responseCode <= 399);
        } catch (Exception e) {
            log.error("{}{}", e.getMessage(), link);
            return false;
        }
    }
}
