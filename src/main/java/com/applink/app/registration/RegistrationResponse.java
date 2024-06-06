package com.applink.app.registration;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegistrationResponse {
    private String token;
    private RegistrationError error;

    public enum RegistrationError {
        success,
        usernameAlreadyExists,
        passwordToWeek,
        usernameAlreadyExistsAndPasswordToWeek
    }

    public static RegistrationResponse success(String token) {
        return builder().error(RegistrationError.success).token(token).build();
    }

    public static RegistrationResponse registrationError(RegistrationError error) {
        return builder().error(error).token(null).build();
    }
}