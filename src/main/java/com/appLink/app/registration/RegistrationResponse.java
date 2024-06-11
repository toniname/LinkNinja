package com.applink.app.registration;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegistrationResponse {
    private String token;
    private RegistrationError error;

    public enum RegistrationError {
        SUCCESS,
        USER_NAME_ALREADY_EXISTS,
        PASSWORD_TO_WEEK,
        USER_NAME_ALREADY_EXISTS_AND_PASSWORD_TO_WEEK
    }

    public static RegistrationResponse success(String token) {
        return builder().error(RegistrationError.SUCCESS).token(token).build();
    }

    public static RegistrationResponse registrationError(RegistrationError error) {
        return builder().error(error).token(null).build();
    }
}