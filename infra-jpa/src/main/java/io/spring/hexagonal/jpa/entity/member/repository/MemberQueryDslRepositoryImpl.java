package io.spring.hexagonal.jpa.entity.member.repository;

import static io.spring.hexagonal.jpa.entity.member.QMemberEntity.*;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;

import io.spring.hexagonal.application.port.out.repository.member.MemberFindListQuery;
import io.spring.hexagonal.jpa.entity.member.MemberEntity;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MemberQueryDslRepositoryImpl implements MemberQueryDslRepository {

	private final JPAQueryFactory queryFactory;

	@Override
	public Page<MemberEntity> findAll(MemberFindListQuery query, Pageable pageable) {

		BooleanBuilder builder = getWhereBuilder(query);

		List<MemberEntity> contents = queryFactory.select(memberEntity)
			.from(memberEntity)
			.where(builder)
			.orderBy(memberEntity.id.desc())
			.offset(pageable.getOffset())
			.limit(pageable.getPageSize())
			.fetch();

		Long count = Optional.ofNullable(
			queryFactory
				.select(memberEntity.countDistinct())
				.from(memberEntity)
				.where(builder)
				.fetchOne()
		).orElse(0L);

		return new PageImpl<>(contents, pageable, count);
	}

	private BooleanBuilder getWhereBuilder(MemberFindListQuery query) {
		BooleanBuilder builder = new BooleanBuilder();

		if (query.name() != null && !query.name().isBlank()) {
			builder.and(memberEntity.name.like(query.name() + "%"));
		}

		return builder;
	}
}
