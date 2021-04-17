package com.assignment.demo.exception;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class AlreadyExistException extends Exception {

    public AlreadyExistException() {
        log.info("Already Exist Exception");
    }

    public AlreadyExistException(String message) {
        super(message);
        log.info("Already Exist Exception");
    }
}
