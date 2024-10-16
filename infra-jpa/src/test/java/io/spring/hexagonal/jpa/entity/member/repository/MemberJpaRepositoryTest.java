package io.spring.hexagonal.jpa.entity.member.repository;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import io.spring.hexagonal.domain.member.Member;

@DisplayName("회원 레파지토리 테스트")
@DataJpaTest
class MemberJpaRepositoryTest {

	@Autowired
	private MemberCoreRepository memberRepository;

	@DisplayName("회원 엔티티를 저장한다")
	@Test
	public void 회원_엔티티_저장_성공() {
		// given
		Member member = Member.create(null, "kim");

		// when
		Member savedMember = memberRepository.save(member);

		// then
		Assertions.assertThat(savedMember.getId()).isNotNull();
	}

	@TestConfiguration
	static class MemberRepositoryConfiguration {
		@Bean
		public MemberCoreRepository memberCoreRepository(MemberJpaRepository memberJpaRepository) {
			return new MemberCoreRepository(memberJpaRepository);
		}
	}
}