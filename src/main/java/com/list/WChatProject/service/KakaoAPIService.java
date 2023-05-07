package com.list.WChatProject.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

import static com.list.WChatProject.dto.KaKaoAuthDto.*;

@Service
public class KakaoAPIService {
	private RestTemplate restTemplate;

	public KakaoAPIService(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@Value("${KAKAO_OAUTH_API_KEY}")
	private String KAKAO_OAUTH_API_KEY;

	@Value("${KAKAO_OAUTH_REDIRECT_URI}")
	private String KAKAO_OAUTH_REDIRECT_URI;

	@Value("${KAKAO_OAUTH_TOKEN_API_URI}")
	private String KAKAO_OAUTH_TOKEN_API_URI;

	@Value("${KAKAO_OAUTH_INFORMATION_API_URI}")
	private String KAKAO_OAUTH_INFORMATION_API_URI;

	public String getKakaoAccountAccessToken(String code) {
		MultiValueMap<String, String> data = new LinkedMultiValueMap<>();
		data.add("grant_type", "authorization_code");
		data.add("client_id", KAKAO_OAUTH_API_KEY);
		data.add("redirect_uri", KAKAO_OAUTH_REDIRECT_URI);
		data.add("code", code);

		URI uri = UriComponentsBuilder
				.fromUriString(KAKAO_OAUTH_TOKEN_API_URI)
				.encode()
				.build()
				.toUri();

		ResponseEntity<KakaoLoginTokenRequestResponse> result =
				restTemplate.postForEntity(uri, data,
						KakaoLoginTokenRequestResponse.class);

		if (result.getStatusCode() == HttpStatus.OK) {
			return result.getBody().getAccess_token();
		}

		else {
			return null;
		}
	}

	public KakaoAccountInformationRequestResponse getKakaoAccountInformation(String token) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer " + token);
		HttpEntity<String> request = new HttpEntity<String>(headers);

		ResponseEntity<KakaoAccountInformationRequestResponse> response = restTemplate.exchange(
				KAKAO_OAUTH_INFORMATION_API_URI,
				HttpMethod.GET,
				request,
				KakaoAccountInformationRequestResponse.class);

		if (response.getStatusCode() == HttpStatus.OK) {
			return response.getBody();
		} else {
			return null;
		}
	}
}