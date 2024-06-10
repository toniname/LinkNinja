package com.appLink.app.delete;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class DeleteResponse {
    private String status;
    private String message;


    public static DeleteResponse success() {
        return DeleteResponse.builder().status("success").message("url was successfully deleted").build();
    }

    public static DeleteResponse notAllowed() {
        return DeleteResponse.builder().status("failure").message("not allowed").build();
    }

    public static DeleteResponse notFound() {
        return DeleteResponse.builder().status("failure").message("url not found").build();
    }

}
