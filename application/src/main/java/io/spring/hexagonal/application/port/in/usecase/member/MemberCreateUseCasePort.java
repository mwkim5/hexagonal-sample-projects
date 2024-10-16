package io.spring.hexagonal.application.port.in.usecase.member;

public interface MemberCreateUseCasePort {
	MemberInfo create(MemberCreateCommand command);
}
