package org.example.livebid.domain.user.enums;

import org.example.livebid.global.exception.BaseErrorType;
import org.springframework.http.HttpStatus;

public enum UserException implements BaseErrorType {
    USER_NOT_FOUND("존재하지 않는 사용자입니다.", HttpStatus.NOT_FOUND),
    EMAIL_ALREADY_EXISTS("이미 사용 중인 이메일입니다.", HttpStatus.CONFLICT),
    INVALID_PASSWORD("비밀번호가 올바르지 않습니다.", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED_ACCESS("접근 권한이 없습니다.", HttpStatus.FORBIDDEN),
    USER_ALREADY_DELETED("이미 삭제된 사용자입니다.", HttpStatus.BAD_REQUEST),
    INVALID_USER_ROLE("잘못된 사용자 권한입니다.", HttpStatus.BAD_REQUEST);

    private final String message;
    private final HttpStatus httpStatus;

    UserException(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    @Override
    public HttpStatus getHttpStatus() {
        return this.httpStatus;
    }
}
