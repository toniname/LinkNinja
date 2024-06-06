package com.applink.app.creation;

import com.applink.app.database.entity.UrlEntity;
import com.applink.app.database.entity.UserEntity;
import com.applink.app.database.service.UrlService;
import com.applink.app.database.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.time.LocalDateTime;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class CreationService {
    private final UrlService urlService;
    private final UserService userService;
    private final CreationMapper creationMapper;


    public CreationResponse create(CreationRequest urlCreationRequest, String username) {
        String validatedUrl = validateUrl(urlCreationRequest.getLongUrl());
        try {
            UserEntity userByUsername = userService.getUserByUsername(username);
            if (isLinkActive(validatedUrl)) {

                UrlEntity urlEntity = UrlEntity.builder()
                        .user(userByUsername)
                        .shortUrl(code())
                        .longUrl(validatedUrl)
                        .visited(0)
                        .createdAt(LocalDateTime.now())
                        .expiredAt(LocalDateTime.now().plusDays(14))
                        .build();

                UrlEntity savedEntity = urlService.saveUrlEntity(urlEntity);

                return creationMapper.creationResponse(savedEntity);
            }

        } catch (Exception e) {
            log.info(e.getMessage());
        }
        return new CreationResponse();
    }


    public String code() {
        String code;
        while (true) {
            code = UUID.randomUUID().toString().substring(1, 8);
            if (urlService.isCodeValid(code)) {
                return code;
            }
        }
    }


    private String validateUrl(String url) {
        if(url.length()>8) {
            String substringHttps = url.substring(1, 8);
            String substringHttp = url.substring(1, 7);

            if ((substringHttps.compareTo("https://") == 0) || substringHttp.compareTo("https//") == 0) {
                return url;
            } else {
                return "https://" + url;
            }
        }
        return null;
    }

    private boolean isLinkActive(String link) {
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
