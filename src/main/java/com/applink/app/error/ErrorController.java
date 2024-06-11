package com.applink.app.error;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("errorController")
public class ErrorController {

    @GetMapping("/404")
    public String error404() {
        return "404";
    }

}