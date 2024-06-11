package com.appLink.app.creation;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("com/appLink/app/creation")
@RequiredArgsConstructor
public class CreateController {
    private final CreationService creationService;

    @PostMapping("/api/v1/create")
    public CreationResponse creation(@RequestBody CreationRequest creationRequest) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return creationService.create(creationRequest, username);

    }

}