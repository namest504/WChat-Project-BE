package com.list.WChatProject.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

public class KaKaoAuthDto {
	/**
	 * 사용자에게 Kakao Login 인가코드를 받아 Token을 요청 후 응답에 있는 token을 매핑하기 위한 DTO
	 */
	@Getter
	@Setter
	public static class KakaoLoginTokenRequestResponse {
		private String access_token;
	}

	/**
	 * 카카오 API로부터 받은 유저 정보를 매핑하기 위한 DTO
	 */
	@Getter
	@Setter
	public static class KakaoAccountInformationRequestResponse {
		private String id;
		private kakao_account kakao_account;
	}

	/**
	 * KakaoAccountInformationRequestResponse에서 사용되는 class
	 */
	@Getter
	@Setter
	public static class kakao_account {
		private String email;
		private String name;
		private profile profile;
	}

	/**
	 * KakaoAccountInformationRequestResponse에서 사용되는 class
	 */
	@Getter
	@Setter
	public static class profile {
		private String nickname;
	}

	/**
	 * Kakao Login Response DTO
	 */
	@Getter
	@Setter
	@AllArgsConstructor
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	public static class KakaoLoginResponse {
		private Boolean success;
		private String accessToken;
		private String refreshToken;
		private String registerKakaoAccountToken;
	}

}
