package io.spring.hexagonal.application.adapter.in.usecase.member;

import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import io.spring.hexagonal.application.port.in.usecase.member.MemberFindUseCasePort;
import io.spring.hexagonal.application.port.in.usecase.member.MemberInfo;
import io.spring.hexagonal.application.port.out.repository.member.MemberRepositoryPort;
import io.spring.hexagonal.domain.common.exception.ExceptionCode;
import io.spring.hexagonal.domain.member.MemberNotFoundException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MemberFindUseCase implements MemberFindUseCasePort {
	private final MemberRepositoryPort memberRepository;

	@Override
	public MemberInfo findById(Long id) {
		return memberRepository.findById(id)
			.map(MemberInfo::from)
			.orElseThrow(() -> new MemberNotFoundException(ExceptionCode.MEMBER_NOT_FOUND));
	}
}
