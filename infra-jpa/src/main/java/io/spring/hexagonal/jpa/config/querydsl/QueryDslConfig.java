package io.spring.hexagonal.jpa.config.querydsl;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.querydsl.jpa.impl.JPAQueryFactory;

import jakarta.persistence.EntityManager;

@Configuration
public class QueryDslConfig {

	@Bean
	JPAQueryFactory queryFactory(EntityManager entityManager) {
		return new JPAQueryFactory(entityManager);
	}
}
