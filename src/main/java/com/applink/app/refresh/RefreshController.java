package com.applink.app.refresh;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/refresh")
public class RefreshController {
    @Autowired
    private RefreshService refreshService;

    @PostMapping
    public RefreshResponse refresh(@RequestBody RefreshRequest refreshRequest, @RequestHeader("username") String username) {
        return refreshService.refresh(refreshRequest, username);
    }
}