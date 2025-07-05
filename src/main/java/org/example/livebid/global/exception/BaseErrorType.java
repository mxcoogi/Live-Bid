package org.example.livebid.global.exception;

import org.springframework.http.HttpStatus;

public interface BaseErrorType {
    String getMessage();
    HttpStatus getHttpStatus();
}
