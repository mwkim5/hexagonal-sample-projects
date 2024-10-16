package io.spring.hexagonal.api.interfaces.member.dto;

import io.spring.hexagonal.application.port.in.usecase.member.MemberInfo;

public record MemberInfoResponse(Long id, String name) {
	public static MemberInfoResponse from(MemberInfo memberInfo) {
		return new MemberInfoResponse(memberInfo.id(), memberInfo.name());
	}
}
