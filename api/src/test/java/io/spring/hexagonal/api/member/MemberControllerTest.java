package io.spring.hexagonal.api.member;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import io.spring.hexagonal.api.AbstractControllerTest;
import io.spring.hexagonal.api.interfaces.member.dto.MemberCreateRequest;
import io.spring.hexagonal.api.interfaces.member.dto.MemberUpdateRequest;
import io.spring.hexagonal.domain.common.exception.ExceptionCode;

@Transactional
@Sql(scripts = "/sql/member-controller-test.sql")
class MemberControllerTest extends AbstractControllerTest {

	@DisplayName("회원 가입 성공")
	@Test
	void 회원_가입_성공() throws Exception {

		//given
		String name = "zozo";

		// when
		ResultActions resultActions = mockMvc.perform(
			MockMvcRequestBuilders.post("/api/v1/members")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(new MemberCreateRequest(name))));

		// then
		resultActions.andExpect(status().isOk())
			.andExpect(jsonPath("data.id").exists())
			.andExpect(jsonPath("data.name").value(name));
	}

	@DisplayName("회원 가입 실패 - 중복된 이름")
	@Test
	void 회원_가입_실패_case1() throws Exception {

		//given
		String name = "kim";
		String json = objectMapper.writeValueAsString(new MemberCreateRequest(name));

		// when
		ResultActions resultActions = mockMvc.perform(
			MockMvcRequestBuilders.post("/api/v1/members").contentType(MediaType.APPLICATION_JSON).content(json));

		// then
		resultActions.andExpect(status().isOk())
			.andExpect(jsonPath("data.code").value(ExceptionCode.MEMBER_ALREADY_EXISTS.name()))
			.andExpect(jsonPath("success").value(false));
	}

	//회원 목록 조회
	@Test
	@DisplayName("회원 목록 조회 성공")
	void 회원_목록_조회() throws Exception {
		// give
		int numberOfElements = 10;
		// when
		ResultActions resultActions = mockMvc.perform(get("/api/v1/members"));
		// then
		resultActions.andExpect(status().isOk())
			.andExpect(jsonPath("data.content.size()").value(numberOfElements))
			.andExpect(jsonPath("data.content..id").exists())
			.andExpect(jsonPath("data.content..name").exists())
			.andExpect(jsonPath("data.numberOfElements").value(numberOfElements));
	}

	@Test
	@DisplayName("회원 목록 조회 - page : 1, size : 3")
	void 회원_목록_조회_paging() throws Exception {
		// give
		String page = "1";
		String size = "3";
		int totalElements = 10;
		int totalPages = 4;
		boolean success = true;
		int numberOfElements = 3;

		// when
		ResultActions resultActions = mockMvc.perform(get("/api/v1/members").param("page", page).param("size", size));
		// then
		resultActions.andExpect(status().isOk())
			.andExpect(jsonPath("data.content.size()").value(size))
			.andExpect(jsonPath("data.content..id").exists())
			.andExpect(jsonPath("data.content..name").exists())
			.andExpect(jsonPath("data.numberOfElements").value(numberOfElements))
			.andExpect(jsonPath("data.totalElements").value(totalElements))
			.andExpect(jsonPath("data.totalPages").value(totalPages))
			.andExpect(jsonPath("success").value(success));
	}

	//회원 단건 조회
	@Test
	@DisplayName("회원 단건 조회 성공")
	void 회원_단건_조회_성공() throws Exception {
		// give
		final Long id = 100L;
		// when
		ResultActions resultActions = mockMvc.perform(get("/api/v1/members/{id}", id));
		// then
		resultActions.andExpect(status().isOk())
			.andExpect(jsonPath("data.id").value(id))
			.andExpect(jsonPath("data.name").exists())
			.andExpect(jsonPath("success").value(true));
	}

	//회원 단건 조회 실패 - (존재하지 않는 회원)
	@Test
	@DisplayName("회원 단건 조회 실패 - 존재하지 않는 회원")
	void 회원_단건_조회_실패_case1() throws Exception {
		// give
		final Long id = 999L;
		// when
		ResultActions resultActions = mockMvc.perform(get("/api/v1/members/{id}", id));
		// then
		resultActions.andExpect(status().isOk())
			.andExpect(jsonPath("success").value(false))
			.andExpect(jsonPath("data.message").value(ExceptionCode.MEMBER_NOT_FOUND.getMessage()))
			.andExpect(jsonPath("data.code").value(ExceptionCode.MEMBER_NOT_FOUND.name()));
	}

	@Test
	@DisplayName("회원 수정 성공")
	void 회원_수정_성공() throws Exception {
		// give
		final Long id = 100L;
		final String name = "updateName";
		// when
		ResultActions resultActions = mockMvc.perform(put("/api/v1/members/{id}", id)
			.content(objectMapper.writeValueAsString(new MemberUpdateRequest(name)))
			.contentType(MediaType.APPLICATION_JSON));
		// then
		resultActions.andExpect(status().isOk())
			.andExpect(jsonPath("success").value(true));
	}

	@Test
	@DisplayName("회원 수정 실패 - 중복된 이름")
	void 회원_수정_실패_case1() throws Exception {
		// give
		final Long id = 2L;
		final String name = "kim";
		// when
		ResultActions resultActions = mockMvc.perform(put("/api/v1/members/{memberId}", id)
			.content(objectMapper.writeValueAsString(new MemberUpdateRequest(name)))
			.contentType(MediaType.APPLICATION_JSON));
		// then
		resultActions.andExpect(status().isOk())
			.andExpect(jsonPath("success").value(false))
			.andExpect(jsonPath("data.message").value(ExceptionCode.MEMBER_ALREADY_EXISTS.getMessage()))
			.andExpect(jsonPath("data.code").value(ExceptionCode.MEMBER_ALREADY_EXISTS.name()));
	}
}