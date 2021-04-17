package com.assignment.demo.exception;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class NoRecordFoundException extends Exception {

    public NoRecordFoundException() {
        log.info("No Record Found");
    }

    public NoRecordFoundException(String message) {
        super(message);
        log.info("No Record Found");
    }
}
