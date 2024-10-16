package io.spring.hexagonal.application.adapter.in.usecase.member;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.spring.hexagonal.application.port.in.usecase.member.MemberFindListUseCasePort;
import io.spring.hexagonal.application.port.in.usecase.member.MemberInfo;
import io.spring.hexagonal.application.port.out.repository.member.MemberFindListQuery;
import io.spring.hexagonal.application.port.out.repository.member.MemberRepositoryPort;
import io.spring.hexagonal.domain.member.Member;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MemberFindListUseCase implements MemberFindListUseCasePort {

	private final MemberRepositoryPort memberRepository;

	@Transactional(readOnly = true)
	@Override
	public Page<MemberInfo> findAll(MemberFindListQuery query, Pageable pageable) {
		return memberRepository.findAll(query, pageable).map(MemberInfo::from);
	}
}
