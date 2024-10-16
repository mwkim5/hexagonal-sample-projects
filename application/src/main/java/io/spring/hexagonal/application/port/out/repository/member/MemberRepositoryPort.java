package io.spring.hexagonal.application.port.out.repository.member;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import io.spring.hexagonal.domain.member.Member;

public interface MemberRepositoryPort {
	Member save(Member member);

	Optional<Member> findById(Long id);

	Optional<Member> findByName(String name);

	Page<Member> findAll(MemberFindListQuery query, Pageable pageable);

}
