package com.appLink.app.extend;


import com.appLink.app.database.entity.UrlEntity;
import com.appLink.app.database.error.UrlNotFoundException;
import com.appLink.app.database.service.UrlService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class ExtendService {
    private final UrlService urlService;

    public ExtendResponse extend(ExtendRequest extendRequest, String username) {
        try {
            UrlEntity urlEntity = urlService
                    .findUrlEntityByShortUrl(extendRequest.getShortUrl());
            if (isOwnedByUser(urlEntity, username)
                    && isUrlDateValid(urlEntity.getExpiredAt())){
                urlEntity.setExpiredAt(LocalDateTime.now().plusMinutes(14));
                urlService.updateUrlEntity(urlEntity);
                return ExtendResponse.success();
            } else if (!isOwnedByUser(urlEntity, username)) {
                return ExtendResponse.error(ExtendResponse.ExtendError.BELONGS_TO_ANOTHER_USER);
            }
        }catch (UrlNotFoundException e) {
            log.error(e.getMessage());
        }
        return ExtendResponse.error(ExtendResponse.ExtendError.LINK_EXPIRED);
    }

    private boolean isUrlDateValid(LocalDateTime dateTime) {
        LocalDateTime now = LocalDateTime.now();
        return now.isAfter(dateTime);
    }

    private boolean isOwnedByUser(UrlEntity urlEntity, String username) {
        return urlEntity.getUser().getUsername().compareTo(username)==0;
    }
}