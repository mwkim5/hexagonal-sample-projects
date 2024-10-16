package io.spring.hexagonal.application.adapter.in.usecase.member;

import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.spring.hexagonal.application.port.in.usecase.member.MemberUpdateCommand;
import io.spring.hexagonal.application.port.in.usecase.member.MemberUpdateUseCasePort;
import io.spring.hexagonal.application.port.out.repository.member.MemberRepositoryPort;
import io.spring.hexagonal.domain.common.exception.ExceptionCode;
import io.spring.hexagonal.domain.member.Member;
import io.spring.hexagonal.domain.member.MemberAlreadyExistsException;
import io.spring.hexagonal.domain.member.MemberNotFoundException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MemberUpdateUseCase implements MemberUpdateUseCasePort {

	private final MemberRepositoryPort memberRepository;

	@Transactional
	@Override
	public void update(MemberUpdateCommand command) {
		validateMember(command);

		Member member = memberRepository.findById(command.id())
			.orElseThrow(() -> new MemberNotFoundException(ExceptionCode.MEMBER_NOT_FOUND));

		member.update(command.name());

		memberRepository.save(member);
	}

	private void validateMember(MemberUpdateCommand command) {
		memberRepository.findByName(command.name())
			.ifPresent(m -> {
				if (!Objects.equals(m.getId(), command.id())) {
					throw new MemberAlreadyExistsException(ExceptionCode.MEMBER_ALREADY_EXISTS);
				}
			});
	}
}
