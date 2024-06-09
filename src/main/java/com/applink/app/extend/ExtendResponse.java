package com.applink.app.extend;

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
        success,
        linkExpired,
        belongsToAnotherUser
    }

    public static ExtendResponse success() {
        return new ExtendResponse(ExtendError.success);
    }
    public static ExtendResponse error(ExtendError error) {
        return new ExtendResponse(error);
    }
}
