package io.spring.hexagonal.application.adapter.in.usecase.member;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.spring.hexagonal.application.port.in.usecase.member.MemberCreateCommand;
import io.spring.hexagonal.application.port.in.usecase.member.MemberCreateUseCasePort;
import io.spring.hexagonal.application.port.in.usecase.member.MemberInfo;
import io.spring.hexagonal.application.port.out.repository.member.MemberRepositoryPort;
import io.spring.hexagonal.domain.common.exception.ExceptionCode;
import io.spring.hexagonal.domain.member.Member;
import io.spring.hexagonal.domain.member.MemberAlreadyExistsException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MemberCreateUseCase implements MemberCreateUseCasePort {

	private final MemberRepositoryPort memberRepository;

	@Transactional
	@Override
	public MemberInfo create(MemberCreateCommand command) {
		Member member = command.toDomain();
		validateMember(member);

		return MemberInfo.from(memberRepository.save(member));
	}

	private void validateMember(Member member) {
		memberRepository.findByName(member.getName())
			.ifPresent(m -> {
				throw new MemberAlreadyExistsException(ExceptionCode.MEMBER_ALREADY_EXISTS);
			});
	}
}
