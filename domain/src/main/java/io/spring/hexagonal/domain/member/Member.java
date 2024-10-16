package io.spring.hexagonal.domain.member;

import java.util.Objects;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class Member {
	private Long id;
	private String name;

	public static Member create(Long id, String name) {
		Objects.requireNonNull(name, "name must not be null");

		return new Member(id, name);
	}

	public void update(String name) {
		Objects.requireNonNull(name, "name must not be null");
		this.name = name;
	}
}
