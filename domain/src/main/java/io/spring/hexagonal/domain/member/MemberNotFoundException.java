package io.spring.hexagonal.domain.member;

import io.spring.hexagonal.domain.common.exception.DomainException;
import io.spring.hexagonal.domain.common.exception.ExceptionCode;

public class MemberNotFoundException extends DomainException {
	public MemberNotFoundException(ExceptionCode exceptionCode) {
		super(exceptionCode);
	}
}
