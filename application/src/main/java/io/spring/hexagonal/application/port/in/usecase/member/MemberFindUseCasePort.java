package io.spring.hexagonal.application.port.in.usecase.member;

public interface MemberFindUseCasePort {
	MemberInfo findById(Long id);
}
