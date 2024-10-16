package io.spring.hexagonal.application.port.in.usecase.member;

import io.spring.hexagonal.domain.member.Member;

public record MemberInfo(Long id, String name) {

	public static MemberInfo from(Member member) {
		return new MemberInfo(member.getId(), member.getName());
	}
}
