package io.spring.hexagonal.application.port.in.usecase.member;

public interface MemberUpdateUseCasePort {
	void update(MemberUpdateCommand command);
}
