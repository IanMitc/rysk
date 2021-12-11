package com.revature.rysk.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class PermissionsException extends RuntimeException {
    public PermissionsException(String message) {
        super(message);
    }
}
