package org.example.livebid.domain.auction.enums;

import org.example.livebid.global.exception.BaseErrorType;
import org.springframework.http.HttpStatus;

public enum AuctionException implements BaseErrorType {
    AUCTION_NOT_FOUND("경매를 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    AUCTION_ALREADY_ENDED("이미 종료된 경매입니다.", HttpStatus.BAD_REQUEST),
    INVALID_STARTING_PRICE("시작 가격은 0보다 커야 합니다.", HttpStatus.BAD_REQUEST),
    UNAUTHORIZED_ACCESS("해당 경매에 접근할 권한이 없습니다.", HttpStatus.FORBIDDEN);


    private final String message;
    private final HttpStatus httpStatus;

    AuctionException(String s, HttpStatus httpStatus) {
        this.message = s;
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
