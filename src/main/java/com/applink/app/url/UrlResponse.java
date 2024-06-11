package com.applink.app.url;

import com.applink.app.database.dto.UrlDto;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UrlResponse {
    private List<UrlDto> urlDto;
}
