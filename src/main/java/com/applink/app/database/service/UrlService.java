package com.applink.app.database.service;

import com.applink.app.database.entity.UrlEntity;
import com.applink.app.database.errors.UrlNotFoundException;
import com.applink.app.database.repository.UrlRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UrlService {
    private final UrlRepository urlRepository;

    public UrlEntity findUrlEntityByShortUrl(String shortUrl) throws UrlNotFoundException {
        return urlRepository.findUrlEntitiesByShortUrl(shortUrl)
                .orElseThrow(() -> new UrlNotFoundException(shortUrl));
    }

    public UrlEntity saveUrlEntity(UrlEntity urlEntity) {
        return urlRepository.save(urlEntity);
    }

    public UrlEntity updateUrlEntity(UrlEntity urlEntity) {
        return urlRepository.save(urlEntity);
    }

    public void deleteUrlEntity(UrlEntity urlEntity) {
        urlRepository.delete(urlEntity);
    }

    public List<UrlEntity> findAllUrlEntityByUsername(String username) {
        return urlRepository.findAllByUserUsername(username);
    }
}
