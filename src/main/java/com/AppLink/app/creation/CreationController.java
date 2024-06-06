package com.appLink.app.creation;
import com.appLink.app.database.entity.UrlEntity;
import com.appLink.app.database.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/urls")
public class CreationController {

    private final UrlService urlService;
    private final CreationMapper creationMapper;

    @Autowired
    public CreationController(UrlService urlService, CreationMapper creationMapper) {
        this.urlService = urlService;
        this.creationMapper = creationMapper;
    }

    // Створення нового скороченого URL
    @PostMapping
    public ResponseEntity<CreationResponse> createUrl(@RequestBody CreationRequest request) {
        UrlEntity urlEntity = UrlEntity.builder()
                .shortUrl(request.getShortUrl())
                .longUrl(request.getLongUrl())
                .createdAt(LocalDateTime.now())
                .expiredAt(LocalDateTime.now().plusDays(14))
                .visited(0)
                .build();
        UrlEntity savedUrl = urlService.saveUrlEntity(urlEntity);
        CreationResponse response = creationMapper.creationResponse(savedUrl);
        return ResponseEntity.ok(response);
    }

    // Отримання скороченого URL за коротким URL
    @GetMapping("/short/{shortUrl}")
    public ResponseEntity<CreationResponse> getUrlByShortUrl(@PathVariable String shortUrl) {
        UrlEntity urlEntity = urlService.findUrlEntityByShortUrl(shortUrl);
        CreationResponse response = creationMapper.creationResponse(urlEntity);
        return ResponseEntity.ok(response);
    }

    // Отримання всіх скорочених URL за ім'ям користувача
    @GetMapping("/user/{username}")
    public ResponseEntity<List<CreationResponse>> getAllUrlsByUsername(@PathVariable String username) {
        List<UrlEntity> urlEntities = urlService.findAllUrlEntityByUsername(username);
        List<CreationResponse> responses = urlEntities.stream()
                .map(creationMapper::creationResponse)
                .toList();
        return ResponseEntity.ok(responses);
    }

    // Видалення скороченого URL за ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUrl(@PathVariable long id) {
        UrlEntity urlEntity = urlService.getUrlById(id); // Метод в UrlService для отримання URL за ID
        urlService.deleteUrlEntity(urlEntity);
        return ResponseEntity.noContent().build();
    }
}