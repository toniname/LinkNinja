package com.appLink.app.update;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController("updateController")
@RequiredArgsConstructor
public class UpdateController {
    private final UpdateService updateService;

    @PostMapping("/api/v1/update")
    public UpdateResponse update(UpdateRequest updateRequest, Principal principal) {
        return updateService.update(updateRequest, principal.getName());
    }
}