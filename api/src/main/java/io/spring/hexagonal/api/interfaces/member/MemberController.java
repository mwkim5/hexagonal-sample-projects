package io.spring.hexagonal.api.interfaces.member;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.spring.hexagonal.api.interfaces.common.ApiResponse;
import io.spring.hexagonal.api.interfaces.member.dto.MemberCreateRequest;
import io.spring.hexagonal.api.interfaces.member.dto.MemberFindListRequest;
import io.spring.hexagonal.api.interfaces.member.dto.MemberInfoResponse;
import io.spring.hexagonal.api.interfaces.member.dto.MemberUpdateRequest;
import io.spring.hexagonal.application.port.in.usecase.member.MemberCreateUseCasePort;
import io.spring.hexagonal.application.port.in.usecase.member.MemberFindListUseCasePort;
import io.spring.hexagonal.application.port.in.usecase.member.MemberFindUseCasePort;
import io.spring.hexagonal.application.port.in.usecase.member.MemberInfo;
import io.spring.hexagonal.application.port.in.usecase.member.MemberUpdateUseCasePort;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/members")
@RestController
public class MemberController {

	private final MemberCreateUseCasePort memberCreateUseCase;
	private final MemberFindUseCasePort memberFindUseCase;
	private final MemberUpdateUseCasePort memberUpdateUseCase;
	private final MemberFindListUseCasePort memberFindListUseCase;

	@GetMapping
	public ResponseEntity<ApiResponse<Page<MemberInfoResponse>>> findAllMembers(
		MemberFindListRequest request,
		Pageable pageable
	) {

		Page<MemberInfoResponse> responses = memberFindListUseCase.findAll(request.toQuery(), pageable)
			.map(MemberInfoResponse::from);

		return ResponseEntity.ok(ApiResponse.success(responses));
	}

	@GetMapping("/{memberId}")
	public ResponseEntity<ApiResponse<MemberInfoResponse>> findMember(@PathVariable(value = "memberId") Long id) {
		MemberInfo memberInfo = memberFindUseCase.findById(id);
		ApiResponse<MemberInfoResponse> response = ApiResponse.success(MemberInfoResponse.from(memberInfo));
		return ResponseEntity.ok(response);
	}

	@PostMapping
	public ResponseEntity<ApiResponse<MemberInfoResponse>> createMember(
		@RequestBody @Valid MemberCreateRequest request) {
		MemberInfo memberInfo = memberCreateUseCase.create(request.toCommand());
		ApiResponse<MemberInfoResponse> response = ApiResponse.success(MemberInfoResponse.from(memberInfo));
		return ResponseEntity.ok(response);
	}

	@PutMapping("/{memberId}")
	public ResponseEntity<ApiResponse<Void>> updateMember(
		@PathVariable(value = "memberId") Long id,
		@RequestBody @Valid MemberUpdateRequest request
	) {
		memberUpdateUseCase.update(request.toCommand(id));
		return ResponseEntity.ok(ApiResponse.success(null));
	}
}
