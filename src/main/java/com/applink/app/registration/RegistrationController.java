package com.applink.app.registration;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("registrationController")
@RequiredArgsConstructor
public class RegistrationController {
    private final RegistrationService registrationService;

    @PostMapping("/api/v1/registration")
    public RegistrationResponse register(RegistrationRequest registrationRequest) {
        return registrationService.register(registrationRequest);
    }
}
