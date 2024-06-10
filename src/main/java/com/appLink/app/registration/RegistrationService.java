package com.appLink.app.registration;


import com.appLink.app.database.entity.UserEntity;
import com.appLink.app.database.service.JwtService;
import com.appLink.app.database.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class RegistrationService {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public RegistrationResponse register(RegistrationRequest registrationRequest) {
        return validateRegistration(registrationRequest);
    }

    private RegistrationResponse validateRegistration(RegistrationRequest registrationRequest) {
        boolean isUsernameOk = isUsernameExists(registrationRequest.getUsername());
        boolean isPasswordOk = isPasswordOk(registrationRequest.getPassword());
        RegistrationResponse registrationResponse;
        if (!isUsernameOk && !isPasswordOk) {
            registrationResponse = RegistrationResponse
                    .registrationError(RegistrationResponse.RegistrationError.USER_NAME_ALREADY_EXISTS_AND_PASSWORD_TO_WEEK);
        } else if (!isUsernameOk) {
            registrationResponse =
                    RegistrationResponse.registrationError(RegistrationResponse.RegistrationError.USER_NAME_ALREADY_EXISTS);
        } else if (!isPasswordOk) {
            registrationResponse =
                    RegistrationResponse.registrationError(RegistrationResponse.RegistrationError.PASSWORD_TO_WEEK);
        } else {
            registrationResponse = getRegistrationResponse(registrationRequest);
        }

        return registrationResponse;
    }

    private RegistrationResponse getRegistrationResponse(RegistrationRequest registrationRequest) {
        RegistrationResponse registrationResponse;
        UserEntity newUser  = UserEntity.builder()
                .username(registrationRequest.getUsername())
                .password(passwordEncoder.encode(registrationRequest.getPassword()))
                .build();
        userService.saveUser(newUser);
        registrationResponse = RegistrationResponse.success(jwtService.generateToken(newUser));
        return registrationResponse;
    }

    private boolean isPasswordOk(String password) {
        Pattern pattern = Pattern.compile("^(?=.*[A-Za-z0-9]).{8,}$");
        Matcher matcher = pattern.matcher(password);
        return matcher.find();

    }

    private boolean isUsernameExists(String username) {
        try {
            userService.getUserByUsername(username);
            return false;
        } catch (UsernameNotFoundException e) {
            return true;
        }
    }


}