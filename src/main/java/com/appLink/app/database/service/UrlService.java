package com.applink.app.database.service;


import com.applink.app.database.entity.UrlEntity;

import com.applink.app.database.exception.UrlNotFoundException;
import com.applink.app.database.repository.UrlRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "urls")
public class UrlService {
    private final UrlRepository urlRepository;

    @CachePut
    public UrlEntity findUrlEntityByShortUrl(String shortUrl) throws UrlNotFoundException {
        return urlRepository.findUrlEntitiesByShortUrl(shortUrl)
                .orElseThrow(() -> new UrlNotFoundException(shortUrl));
    }
    @CachePut
    public UrlEntity saveUrlEntity(UrlEntity urlEntity) {
        return urlRepository.save(urlEntity);
    }

    @CachePut
    public UrlEntity updateUrlEntity(UrlEntity urlEntity) {
        return urlRepository.save(urlEntity);
    }
    @CacheEvict(allEntries = true)
    public void deleteUrlEntity(UrlEntity urlEntity) {
        urlRepository.delete(urlEntity);
    }

    @Cacheable(cacheNames = "urls_list")
    public List<UrlEntity> findAllUrlEntityByUsername(String username) {
        return urlRepository.findAllByUserUsername(username);
    }

    @Cacheable
    public String getLongUrl(String shortUrl) {
        return urlRepository.findEntityByShortUrl(shortUrl);
    }

    public boolean isCodeValid(String code) {
        String shortUrl = urlRepository.findEntityByShortUrl(code);
        return shortUrl == null || shortUrl.isEmpty();
    }
}