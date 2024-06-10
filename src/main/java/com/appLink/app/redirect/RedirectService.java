package com.appLink.app.redirect;


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
public class RedirectService {
    private final UrlService urlService;


    public String redirectString(String shortUrl) {
        try {
            UrlEntity urlEntity =
                    urlService.findUrlEntityByShortUrl(shortUrl);
            if (isUrlDateValid(urlEntity.getExpiredAt())) {
                urlEntity.setVisited(urlEntity.getVisited() + 1L);
                urlService.saveUrlEntity(urlEntity);

                return urlEntity.getLongUrl();
            }

            urlService.deleteUrlEntity(urlEntity);
            return null;

        } catch (UrlNotFoundException e) {
            log.info(e.toString());
        }
        return null;
    }

    private boolean isUrlDateValid(LocalDateTime dateTime) {
        LocalDateTime now = LocalDateTime.now();
        return now.isBefore(dateTime);
    }
}