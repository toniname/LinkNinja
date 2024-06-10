package com.appLink.app.url;


import com.appLink.app.database.service.UrlService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UrlGetService {
    private final UrlService urlService;
    private final UrlMapper urlMapper;

    public UrlResponse get(String username) {
        return UrlResponse.builder()
                .urlDto((urlMapper
                        .mapToUrlDtoList(urlService.findAllUrlEntityByUsername(username))))
                .build();
    }
}