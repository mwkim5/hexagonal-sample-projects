package io.spring.hexagonal.application.port.in.usecase.member;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import io.spring.hexagonal.application.port.out.repository.member.MemberFindListQuery;

public interface MemberFindListUseCasePort {
	Page<MemberInfo> findAll(MemberFindListQuery query, Pageable pageable);
}
