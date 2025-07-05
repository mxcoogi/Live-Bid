package org.example.livebid.global.exception;

import org.springframework.http.HttpStatus;

import javax.lang.model.type.ErrorType;

public class CustomException extends RuntimeException{
    String message;
    HttpStatus httpStatus;

    public CustomException(BaseErrorType errorType) {
        this.message = errorType.getMessage();
        this.httpStatus = errorType.getHttpStatus();
    }
}
