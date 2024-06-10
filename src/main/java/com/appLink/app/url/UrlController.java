package com.appLink.app.url;

import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController("urlController")
@RequiredArgsConstructor
public class UrlController {
    private final UrlGetService urlService;

    @GetMapping("/api/v1/urls")
    public UrlResponse getUrls(Principal principal) {
        return urlService.get(principal.getName());
    }
}