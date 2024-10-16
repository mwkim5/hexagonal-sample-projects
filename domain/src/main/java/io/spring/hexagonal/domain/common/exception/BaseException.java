package io.spring.hexagonal.domain.common.exception;

import lombok.Getter;

@Getter
public class BaseException extends RuntimeException {
    private final String message;
    private final String code;
    private final int httpStatus;

    public BaseException(String message, String code, int httpStatus) {
        super(message);
        this.message = message;
        this.code = code;
        this.httpStatus = httpStatus;
    }

}
