package com.list.WChatProject.controller;

import com.list.WChatProject.entity.AccountType;
import com.list.WChatProject.entity.Member;
import com.list.WChatProject.exception.CustomException;
import com.list.WChatProject.security.jwt.MemberPrincipal;
import com.list.WChatProject.service.AuthService;
import com.list.WChatProject.service.JwtService;
import com.list.WChatProject.service.KakaoAPIService;
import com.list.WChatProject.service.UserDetailsServiceImpl;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.time.LocalDateTime;

import static com.list.WChatProject.dto.KaKaoAuthDto.*;
import static com.list.WChatProject.dto.MemberDto.*;
import static com.list.WChatProject.dto.TokenDto.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    private final JwtService jwtService;
    private final UserDetailsServiceImpl userDetailsService;
    private final KakaoAPIService kakaoAPIService;

    private final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);

    @GetMapping("/kakao/callback")
    public KakaoLoginResponse kakaoLogin(@RequestParam("code") String code) {
        String kakaoAccountToken = kakaoAPIService.getKakaoAccountAccessToken(code);

        KakaoAccountInformationRequestResponse kakaoAccountInformationRequestResponse = kakaoAPIService
                .getKakaoAccountInformation(kakaoAccountToken);

        if (!authService.isRegisterdKakao(kakaoAccountInformationRequestResponse.getId())) {
            authService.register(new RegisterRequestDto(kakaoAccountInformationRequestResponse.getId(), kakaoAccountInformationRequestResponse.getKakao_account().getEmail(), kakaoAccountInformationRequestResponse.getKakao_account().getProfile().getNickname(), null, AccountType.KAKAO));
        }

        Long uid = authService.getUidFromKakaoAccount(kakaoAccountInformationRequestResponse.getId());
        String accessToken = jwtService.createAccessToken(uid);
        String refreshToken = jwtService.createRefreshToken(uid);

        authService.saveRefreshToken(uid, refreshToken);
        LOGGER.info("[{}] 님이 로그인 하셨습니다.", kakaoAccountInformationRequestResponse.getKakao_account().getProfile().getNickname());
        return new KakaoLoginResponse(true, accessToken, refreshToken);
    }

    @GetMapping("/info")
    public NickNameResponseDto myNickName(@AuthenticationPrincipal MemberPrincipal memberPrincipal) {
        return new NickNameResponseDto(memberPrincipal.getMember().getNickName());
    }

    @PutMapping("/change")
    public ChangeNickNameResponseDto changeMyNickName(@AuthenticationPrincipal MemberPrincipal memberPrincipal, @RequestBody String nickName) {
        LOGGER.info("[{}] 님이 [{}] 으로 닉네임 변경을 시도했습니다.", memberPrincipal.getMember().getName(), nickName);
        return authService.changeNickName(memberPrincipal.getMember().getId(), nickName);
    }

    @PostMapping("/logout")
    public TokenDeleteDto logout(@AuthenticationPrincipal MemberPrincipal memberPrincipal) {
        if (memberPrincipal.getMember().getId() == null) {
            throw new CustomException(HttpStatus.UNAUTHORIZED, "인증되지 않은 유저입니다.");
        }
        Boolean logout = authService.logout(memberPrincipal.getMember().getId());
        return new TokenDeleteDto(logout);
    }

    @PostMapping("/refresh")
    public UseRefreshTokenResponseDto useRefreshToken(@RequestBody @Valid UseRefreshTokenRequestDto useRefreshTokenRequestDto) {
        Claims claims = jwtService.decodeJwtToken(useRefreshTokenRequestDto.getRefreshToken());

        if (!claims.getSubject().equals("refresh_token")) {
            throw new CustomException(HttpStatus.UNAUTHORIZED, "잘못된 요청입니다.");
        }

        if (authService.getRefreshToken(claims.get("uid", Long.class)) == null) {
            throw new CustomException(HttpStatus.UNAUTHORIZED, "잘못된 요청입니다.");
        }

        String accessToken = jwtService.createAccessToken(claims.get("uid", Long.class));

        return new UseRefreshTokenResponseDto(true, accessToken);
    }

//    @PostMapping("/register")
//    public RegisterResponseDto register(@RequestBody @Valid RegisterRequestDto registerRequestDto) {
//        Member member = authService.register(registerRequestDto);
//        return new RegisterResponseDto(true, member.getUserId());
//    }
//
//    @PostMapping("/login")
//    public LoginResponseDto login(@RequestBody @Valid LoginDto loginDto) {
//        Long loginId = authService.login(loginDto);
//        String accessToken = jwtService.createAccessToken(loginId);
//        String refreshToken = jwtService.createRefreshToken(loginId);
//        authService.saveRefreshToken(loginId, refreshToken);
//        return new LoginResponseDto(true, loginId,accessToken, refreshToken);
//    }
//
//    @GetMapping("/inquire/{memberId}")
//    public MemberResponseDto inquire(@PathVariable @Valid Long memberId) {
//        System.out.println("memberId = " + memberId);
//        System.out.println("inquire [memberId] 대상으로 실행");
//        return new MemberResponseDto(true, authService.inquire(memberId).getUserId());
//    }
//
//
//    @GetMapping("/inquire/me")
//    public MemberResponseDto inquireMe(@AuthenticationPrincipal MemberPrincipal memberPrincipal) {
//        LOGGER.info("[AuthController] inquireMe 시작");
//        LOGGER.info("[AuthController] userInfoPrincipal.getUsername() = {}", memberPrincipal.getUsername());
//        LOGGER.info("[AuthController] userInfoPrincipal.getMember() = {}", memberPrincipal.getMember());
//        LOGGER.info("[AuthController] userInfoPrincipal.getMember().getId() = {}", memberPrincipal.getMember().getId());
//
//        System.out.println("inquire [본인] 대상으로 실행");
//        Member authMember = authService.inquire(memberPrincipal.getMember().getId());
//        UserDetails userDetails = userDetailsService.loadUserByUsername(memberPrincipal.getMember().getUsername());
//        return new MemberResponseDto(true, userDetails.getUsername());
//    }
//
//    @GetMapping("/test/non")
//    public TestResponseDto testAuthNon(@AuthenticationPrincipal MemberPrincipal memberPrincipal) {
//        return new TestResponseDto(true, memberPrincipal.getMember().getUserId());
//    }
//
//    @GetMapping("/test/user")
//    @Secured("ROLE_USER")
//    public TestResponseDto testAuthUser(@AuthenticationPrincipal MemberPrincipal memberPrincipal) {
//        return new TestResponseDto(true, memberPrincipal.getMember().getUserId());
//    }
//
//    @PutMapping("/password")
//    public MemberResponseDto updatePassword(@AuthenticationPrincipal MemberPrincipal memberPrincipal,@RequestBody UpdatePasswordRequestDto updatePasswordRequestDto) {
//        LOGGER.info("[AuthController] userInfoPrincipal.getMember().getId() = {}", memberPrincipal.getMember().getId());
//        Member member = authService.updatePassword(memberPrincipal.getMember().getId(), updatePasswordRequestDto);
//        return new MemberResponseDto(true, member.getUserId());
//    }
}
