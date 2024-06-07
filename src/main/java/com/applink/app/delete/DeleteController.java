package com.applink.app.delete;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController("deleteController")
@AllArgsConstructor
@Slf4j
public class DeleteController {

    private final DeletionService deletionService;

    @PostMapping("api/v1/delete")
    public DeleteResponse delete(@RequestBody DeleteRequest deleteRequest, Principal principal) {
        return deletionService.delete(deleteRequest, principal.getName());
    }

}
