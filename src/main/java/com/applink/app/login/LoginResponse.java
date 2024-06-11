package com.applink.app.login;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResponse {
    private String token;
    private LoginStatus loginStatus;

    public static LoginResponse success(String token) {
        return LoginResponse.builder().token(token).loginStatus(LoginStatus.SUCCESS).build();
    }

    public static LoginResponse failure() {
        return LoginResponse.builder().token(null).loginStatus(LoginStatus.BAD_CREDENTIALS).build();
    }
}
