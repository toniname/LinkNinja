package com.applink.app.url;


import com.applink.app.database.service.UrlService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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