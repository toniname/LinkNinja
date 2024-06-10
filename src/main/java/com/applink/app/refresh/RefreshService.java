package com.applink.app.refresh;

import com.applink.app.database.entity.UrlEntity;
import com.applink.app.database.service.UrlService;
import com.applink.app.database.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
public class RefreshService {
    private final UrlService urlService;
    private final UserService userService;
    private final RefreshMapper refreshMapper;

    public RefreshResponse refresh(RefreshRequest refreshRequest, String username) {
        UrlEntity urlEntity = urlService.getUrlEntityById(refreshRequest.getId());
        if (urlEntity!= null && urlEntity.getUser().getUsername().equals(username)) {
            String validatedUrl = validateUrl(refreshRequest.getNewLongUrl());
            if (validatedUrl!= null) {
                urlEntity.setLongUrl(validatedUrl);
                UrlEntity updatedEntity = urlService.saveUrlEntity(urlEntity);
                return refreshMapper.refreshResponse(updatedEntity);
            }
        }
        return new RefreshResponse();
    }

    private String validateUrl(String url) {
        if (url.length() > 8) {
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
}