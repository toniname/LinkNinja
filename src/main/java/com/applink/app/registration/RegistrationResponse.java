package com.applink.app.registration;

import com.applink.app.url.RegistrationError;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegistrationResponse {
    private String token;
    private RegistrationError error;

    public static RegistrationResponse success(String token) {
        return builder().error(RegistrationError.SUCCESS).token(token).build();
    }

    public static RegistrationResponse registrationError(RegistrationError error) {
        return builder().error(error).token(null).build();
    }
}