package io.spring.hexagonal.application.port.in.usecase.member;

import io.spring.hexagonal.domain.member.Member;

public record MemberCreateCommand(String name) {

	public Member toDomain() {
		return Member.create(null, name);
	}
}
