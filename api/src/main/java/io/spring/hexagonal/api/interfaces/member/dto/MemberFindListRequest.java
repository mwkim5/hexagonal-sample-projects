package io.spring.hexagonal.api.interfaces.member.dto;

import io.spring.hexagonal.application.port.out.repository.member.MemberFindListQuery;

public record MemberFindListRequest(String name) {

	public MemberFindListQuery toQuery() {
		return new MemberFindListQuery(name);
	}
}
