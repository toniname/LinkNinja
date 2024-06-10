package com.appLink.app.creation;


import com.appLink.app.database.entity.UrlEntity;
import com.appLink.app.database.entity.UserEntity;
import com.appLink.app.database.service.UrlService;
import com.appLink.app.database.service.UserService;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

import java.util.UUID;

import static com.applink.app.update.UpdateService.isValidUrl;

@Service
@RequiredArgsConstructor
@Slf4j
public class CreationService {
    private final UrlService urlService;
    private final UserService userService;
    private final com.applink.app.creation.CreationMapper creationMapper;


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
        return getString(url);
    }

    @Nullable
    public static String getString(String url) {
        if(url.length()>8) {
            String substringHttps = url.substring(0, 8);
            String substringHttp = url.substring(0, 7);
            if ((substringHttps.compareTo("https://") == 0) || substringHttp.compareTo("http://") == 0) {
                return url;
            } else {
                return "https://" + url;
            }
        }
        return null;
    }

    private boolean isLinkActive(String link) {
        return isValidUrl(link, log);
    }

}