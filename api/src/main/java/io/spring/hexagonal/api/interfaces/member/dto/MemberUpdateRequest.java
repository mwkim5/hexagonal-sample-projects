package io.spring.hexagonal.api.interfaces.member.dto;

import io.spring.hexagonal.application.port.in.usecase.member.MemberUpdateCommand;
import jakarta.validation.constraints.NotBlank;

public record MemberUpdateRequest(
	@NotBlank(message = "이름은 필수 값 입니다")
	String name
) {

	public MemberUpdateCommand toCommand(Long id) {
		return new MemberUpdateCommand(id, name);
	}
}
