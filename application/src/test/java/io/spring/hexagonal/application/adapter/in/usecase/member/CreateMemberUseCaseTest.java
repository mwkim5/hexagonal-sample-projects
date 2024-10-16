package io.spring.hexagonal.application.adapter.in.usecase.member;

import static org.mockito.Mockito.*;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import io.spring.hexagonal.application.port.in.usecase.member.MemberCreateCommand;
import io.spring.hexagonal.application.port.in.usecase.member.MemberInfo;
import io.spring.hexagonal.application.port.out.repository.member.MemberRepositoryPort;
import io.spring.hexagonal.domain.member.Member;

@ExtendWith(MockitoExtension.class)
class CreateMemberUseCaseTest {

	@Mock
	private MemberRepositoryPort memberRepository;

	@InjectMocks
	private MemberCreateUseCase createMemberUseCase;

	@Test
	public void 회원_생성_유스케이스_테스트() {

		// given
		long id = 1L;
		String name = "kim";
		when(memberRepository.save(any())).thenReturn(Member.create(id, name));
		when(memberRepository.findByName(any())).thenReturn(Optional.empty());
		MemberCreateCommand command = new MemberCreateCommand(name);

		// when
		MemberInfo memberInfo = createMemberUseCase.create(command);

		// then
		Assertions.assertThat(memberInfo.id()).isEqualTo(id);
	}

}