package io.spring.hexagonal.jpa.entity.member;

import io.spring.hexagonal.domain.member.Member;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "member")
@Entity
public class MemberEntity {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Long id;

	@Column(name = "name", nullable = false, unique = true)
	private String name;

	public static MemberEntity create(Member member) {
		return new MemberEntity(member.getId(), member.getName());
	}

	public Member toDomain() {
		return Member.create(id, name);
	}
}
