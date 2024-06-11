package com.applink.app.refresh;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("com/applink/app/refresh")
@RequiredArgsConstructor
public class RefreshController {
    private RefreshService refreshService;

    @PostMapping("/api/v1/refresh")
    public RefreshResponse refresh(@RequestBody RefreshRequest refreshRequest, Principal principal) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return refreshService.refresh(refreshRequest, username);
    }
}