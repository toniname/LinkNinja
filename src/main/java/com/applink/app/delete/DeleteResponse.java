package com.applink.app.delete;

import lombok.Data;

@Data
public class DeleteResponse {
    private String status;
    private String message;


    public DeleteResponse(DeleteResponceMessage deleteResponceMessage) {
        this.status = deleteResponceMessage.getStatus();
        this.message = deleteResponceMessage.getMesaage();
    }



}
