package com.applink.app.extend;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController("extendController")
@RequiredArgsConstructor
public class ExtendController {
    private final ExtendService extendService;

    @PostMapping("/api/v1/extend")
    public ExtendResponse extend(@RequestBody ExtendRequest extendRequest, Principal principal) {
        return extendService.extend(extendRequest, principal.getName());
    }
}
