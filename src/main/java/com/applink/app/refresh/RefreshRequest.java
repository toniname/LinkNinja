package com.applink.app.refresh;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RefreshRequest {
    private Long id;
    private String newLongUrl;
}
