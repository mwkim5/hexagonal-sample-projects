package io.spring.hexagonal.domain.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class MemberTest {

	@Test
	public void 회원_도메인_생성() {
		// given
		String name = "kim";
		Long id = 1L;

		// when
		Member member = Member.create(id, name);

		// then
		Assertions.assertThat(member.getName()).isEqualTo(name);
	}

	@Test
	public void 회원_도메인_생성시_이름이_없으면_예외() {
		// given
		Long id = null;

		// when & then
		Assertions.assertThatThrownBy(() -> Member.create(id, null)).isInstanceOf(NullPointerException.class);
	}

	@Test
	public void 회원_도메인_수정시_이름이_없으면_예외() {
		// given
		Long id = 1L;
		String name = "kim";
		Member member = Member.create(id, name);

		// when & then
		Assertions.assertThatThrownBy(() -> member.update(null)).isInstanceOf(NullPointerException.class);
	}

}