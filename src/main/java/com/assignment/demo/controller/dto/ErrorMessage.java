package com.assignment.demo.controller.dto;

import java.util.Date;

public class ErrorMessage {

    private String messageAr;
    private String messageEn;
    private Date timestamp;
    private String titleAr;
    private String titleEn;
    private Throwable sourceException;

    public ErrorMessage(String messageAr, String messageEn, String titleAr, String titleEn, Throwable sourceException) {
        this.messageAr = messageAr;
        this.messageEn = messageEn;
        this.timestamp = new Date();
        this.titleAr = titleAr;
        this.titleEn = titleEn;
        this.sourceException = sourceException;
    }



    public String getMessageAr() {
        return messageAr;
    }

    public String getMessageEn() {
        return messageEn;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getTitleAr() {
        return titleAr;
    }

    public String getTitleEn() {
        return titleEn;
    }

    public Throwable getSourceException() {
        return sourceException;
    }
}
