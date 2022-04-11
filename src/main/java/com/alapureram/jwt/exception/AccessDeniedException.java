package com.alapureram.jwt.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class AccessDeniedException extends RuntimeException {

    public AccessDeniedException(Exception e) {
        super(e);
    }

    public AccessDeniedException(String message) {
        super(message);
    }
}
