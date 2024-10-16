package io.spring.hexagonal.jpa.entity.member.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import io.spring.hexagonal.application.port.out.repository.member.MemberFindListQuery;
import io.spring.hexagonal.application.port.out.repository.member.MemberRepositoryPort;
import io.spring.hexagonal.domain.member.Member;
import io.spring.hexagonal.jpa.entity.member.MemberEntity;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class MemberCoreRepository implements MemberRepositoryPort {
	private final MemberJpaRepository memberJpaRepository;

	@Override
	public Member save(Member member) {
		return memberJpaRepository.save(MemberEntity.create(member)).toDomain();
	}

	@Override
	public Optional<Member> findById(Long id) {
		return memberJpaRepository.findById(id).map(MemberEntity::toDomain);
	}

	@Override
	public Optional<Member> findByName(String name) {
		return memberJpaRepository.findByName(name).map(MemberEntity::toDomain);
	}

	@Override
	public Page<Member> findAll(MemberFindListQuery query, Pageable pageable) {
		return memberJpaRepository.findAll(query, pageable).map(MemberEntity::toDomain);
	}
}
