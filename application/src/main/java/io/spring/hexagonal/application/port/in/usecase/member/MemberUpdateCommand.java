package io.spring.hexagonal.application.port.in.usecase.member;

public record MemberUpdateCommand(Long id, String name) {
}
