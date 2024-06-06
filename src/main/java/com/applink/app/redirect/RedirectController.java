package com.applink.app.redirect;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.view.RedirectView;

@Controller("redirectController")
@RequiredArgsConstructor
@Slf4j
public class RedirectController {
    private final RedirectService redirectService;


    @GetMapping("/urls/{id}")
    public RedirectView frontController(@PathVariable String id) {
        try {
            String byShortUrl = redirectService.redirectString(id);

            if (!(byShortUrl == null || byShortUrl.isEmpty())) {
                return new RedirectView(byShortUrl);
            }

        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return new RedirectView("/404");
    }
}
