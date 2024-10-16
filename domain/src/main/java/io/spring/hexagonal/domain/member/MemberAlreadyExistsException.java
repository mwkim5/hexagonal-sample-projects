package io.spring.hexagonal.domain.member;

import io.spring.hexagonal.domain.common.exception.DomainException;
import io.spring.hexagonal.domain.common.exception.ExceptionCode;

public class MemberAlreadyExistsException extends DomainException {
	public MemberAlreadyExistsException(ExceptionCode exceptionCode) {
		super(exceptionCode);
	}
}
