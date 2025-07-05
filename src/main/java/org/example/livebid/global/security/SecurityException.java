package org.example.livebid.global.security;

import org.example.livebid.global.exception.BaseErrorType;
import org.springframework.http.HttpStatus;


public enum SecurityException implements BaseErrorType {
    // 인증(Authentication)
    AUTHENTICATION_REQUIRED("인증이 필요합니다.", HttpStatus.UNAUTHORIZED),
    INVALID_CREDENTIALS("아이디 또는 비밀번호가 일치하지 않습니다.", HttpStatus.UNAUTHORIZED),
    INVALID_TOKEN("유효하지 않은 토큰입니다.", HttpStatus.UNAUTHORIZED),
    EXPIRED_TOKEN("토큰이 만료되었습니다.", HttpStatus.UNAUTHORIZED),
    UNSUPPORTED_TOKEN("지원하지 않는 토큰 형식입니다.", HttpStatus.UNAUTHORIZED),
    MALFORMED_TOKEN("토큰 형식이 잘못되었습니다.", HttpStatus.UNAUTHORIZED),

    // 인가(Authorization)
    ACCESS_DENIED("접근 권한이 없습니다.", HttpStatus.FORBIDDEN),
    INSUFFICIENT_PERMISSION("필요한 권한이 없습니다.", HttpStatus.FORBIDDEN),
    ROLE_NOT_ALLOWED("해당 역할은 이 작업을 수행할 수 없습니다.", HttpStatus.FORBIDDEN);

    private final String message;
    private final HttpStatus httpStatus;

    SecurityException(String message, HttpStatus httpStatus) {
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
