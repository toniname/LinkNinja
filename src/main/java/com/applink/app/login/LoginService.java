package com.applink.app.login;

import com.applink.app.database.entity.UserEntity;
import com.applink.app.database.service.JwtService;
import com.applink.app.database.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.*;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoginService {
    private final UserService userService;
    private final JwtService jwtService;
    private final AuthenticationManager manager;

    public LoginResponse login(LoginRequest loginRequest) {
        try {
            manager.authenticate(new UsernamePasswordAuthenticationToken(
                    loginRequest.getUsername(),
                    loginRequest.getPassword()
            ));
            UserEntity byUsername = userService.getUserByUsername(loginRequest.getUsername());
                return LoginResponse.success(jwtService.generateToken(byUsername));
        } catch (DisabledException | LockedException | BadCredentialsException exception) {
            log.error(exception.getMessage());
        }
        return LoginResponse.failure();
    }
}
