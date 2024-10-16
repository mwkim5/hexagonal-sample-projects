package io.spring.hexagonal.jpa.entity.member.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import io.spring.hexagonal.application.port.out.repository.member.MemberFindListQuery;
import io.spring.hexagonal.jpa.entity.member.MemberEntity;

public interface MemberQueryDslRepository {
	Page<MemberEntity> findAll(MemberFindListQuery query, Pageable pageable);
}
