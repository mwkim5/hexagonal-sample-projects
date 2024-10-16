package io.spring.hexagonal.domain.common.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public enum ExceptionCode {
	MEMBER_NOT_FOUND("존재하지 않는 회원입니다.", HttpStatus.NOT_FOUND),
	MEMBER_ALREADY_EXISTS("이미 존재하는 회원입니다.", HttpStatus.BAD_REQUEST);

	private final String message;
	private final HttpStatus httpStatus;

	ExceptionCode(String message, HttpStatus httpStatus) {
		this.message = message;
		this.httpStatus = httpStatus;
	}
}
