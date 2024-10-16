package io.spring.hexagonal.jpa.entity.member.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import io.spring.hexagonal.jpa.entity.member.MemberEntity;

public interface MemberJpaRepository extends JpaRepository<MemberEntity, Long>, MemberQueryDslRepository {
	Optional<MemberEntity> findByName(String name);
}
