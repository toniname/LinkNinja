package com.appLink.app.url;



import com.appLink.app.database.dto.UrlDto;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UrlResponse {
    private List<UrlDto> urlDto;
}