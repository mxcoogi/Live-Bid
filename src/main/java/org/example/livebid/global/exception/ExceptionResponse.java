package org.example.livebid.global.exception;

import org.springframework.http.HttpStatus;

public record ExceptionResponse (
        HttpStatus httpStatus,
        int code,
        String message
){
}
