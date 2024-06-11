package com.applink.app.delete;


import lombok.Getter;

@Getter
public enum DeleteResponceMessage {

    SUCCESS("url was successfully deleted", "success"),
    NOT_ALLOWED("url was not allowed to be deleted", "failure"),
    NOT_FOUND("url was not found", "failure"),;



    private final String status;
    private final String mesaage;

    DeleteResponceMessage(String mesaage, String status) {
        this.mesaage = mesaage;
        this.status = status;
    }
}
