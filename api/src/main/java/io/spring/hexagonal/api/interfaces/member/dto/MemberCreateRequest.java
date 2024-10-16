package io.spring.hexagonal.api.interfaces.member.dto;

import io.spring.hexagonal.application.port.in.usecase.member.MemberCreateCommand;
import jakarta.validation.constraints.NotBlank;

public record MemberCreateRequest(
	@NotBlank(message = "회원 이름은 필수 값입니다.")
	String name
) {
	public MemberCreateCommand toCommand() {
		return new MemberCreateCommand(name);
	}
}
