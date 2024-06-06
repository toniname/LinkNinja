package com.applink.app.redirect;

import com.applink.app.database.entity.UrlEntity;
import com.applink.app.database.exception.UrlNotFoundException;
import com.applink.app.database.service.UrlService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class RedirectService {
    private final UrlService urlService;


    public String redirectString(String shortUrl){
        try {
            UrlEntity urlEntity =
                    urlService.findUrlEntityByShortUrl(shortUrl);
            urlEntity.setVisited(urlEntity.getVisited() + 1L);

            urlService.saveUrlEntity(urlEntity);

            return urlEntity.getLongUrl();

        } catch (UrlNotFoundException e) {
            log.info(e.toString());
        }
        return null;
    }
}
