package com.appLink.app.extend;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExtendResponse {
    private ExtendError error;

    public enum ExtendError {
        SUCCESS,
        LINK_EXPIRED,
        BELONGS_TO_ANOTHER_USER
    }

    public static ExtendResponse success() {
        return new ExtendResponse(ExtendError.SUCCESS);
    }
    public static ExtendResponse error(ExtendError error) {
        return new ExtendResponse(error);
    }
}