package com.list.WChatProject.service;

import com.list.WChatProject.dto.MemberDto;
import com.list.WChatProject.entity.Member;
import com.list.WChatProject.entity.RefreshToken;
import com.list.WChatProject.exception.CustomException;
import com.list.WChatProject.repository.MemberRepository;
import com.list.WChatProject.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.list.WChatProject.dto.MemberDto.*;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final MemberRepository memberRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final PasswordEncoder passwordEncoder;

    private final Logger LOGGER = LoggerFactory.getLogger(AuthService.class);

    public Member register(RegisterRequestDto registerRequestDto) {
        LOGGER.info("[AuthService] register 시작");
        if (memberRepository.existsByName(registerRequestDto.getName())) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "이미 가입되어있는 아이디 입니다.");
        }

        Member member = Member.builder()
                .name(registerRequestDto.getName())
                .password(passwordEncoder.encode(registerRequestDto.getPassword()))
                .isBan(false)
                .build();
        LOGGER.info("[AuthService] register 종료: member.getUsername() = {}", member.getUsername());
        LOGGER.info("[AuthService] register 종료: member.getName() = {}", member.getName());
        LOGGER.info("[AuthService] register 종료: member.getAuthorities() = {}", member.getAuthorities());
        return memberRepository.save(member);
    }

    public Long login(LoginDto login) {
        LOGGER.info("[AuthService] login 시작");
        Member member = memberRepository.findByName(login.getName())
                .orElseThrow(
                () -> new CustomException(HttpStatus.UNAUTHORIZED, "아이디 또는 비밀번호가 일치하지 않습니다."));

        if (!passwordEncoder.matches(login.getPassword(), member.getPassword())) {
            throw new CustomException(HttpStatus.UNAUTHORIZED, "아이디 또는 비밀번호가 일치하지 않습니다.");
        }

        LOGGER.info("[AuthService] register 종료: member.getUsername() = {}", member.getUsername());
        LOGGER.info("[AuthService] register 종료: member.getName() = {}", member.getName());
        LOGGER.info("[AuthService] register 종료: member.getAuthorities() = {}", member.getAuthorities());

        return member.getId();
    }

    public Boolean saveRefreshToken(Long loginId, String refreshToken) {
        LOGGER.info("[AuthService] saveRefreshToken 시작");
        RefreshToken rt = RefreshToken.builder()
                .key(loginId.toString())
                .value(refreshToken)
                .build();
        refreshTokenRepository.save(rt);

        LOGGER.info("[AuthService] saveRefreshToken 종료: rt.getKey() = {}", rt.getKey());
        LOGGER.info("[AuthService] saveRefreshToken 종료: rt.getValue() = {}", rt.getValue());
        return true;
    }

    public Member inquire(Long memberId) {
        LOGGER.info("[AuthService] inquire 시작");
        Boolean aBoolean = memberRepository.existsById(memberId);
        LOGGER.info("[AuthService] memberRepository.existsById(memberId) 결과 = {}", aBoolean);
        Member member = memberRepository.findById(memberId)
                .orElseThrow(
                        () -> new CustomException(HttpStatus.UNAUTHORIZED, "일치하는 유저가 없습니다."));
        LOGGER.info("[AuthService] inquire 종료: member.getUsername() = {}", member.getUsername());
        LOGGER.info("[AuthService] inquire 종료: member.getName() = {}", member.getName());
        LOGGER.info("[AuthService] inquire 종료: member.getAuthorities() = {}", member.getAuthorities());
        return member;
    }

    public Member updatePassword(Long uid, UpdatePasswordRequestDto updatePasswordRequestDto) {
        Member member = memberRepository.findById(uid)
                .orElseThrow(
                        () -> new CustomException(HttpStatus.UNAUTHORIZED, "일치하는 유저가 없습니다."));
        LOGGER.info("[AuthService] updatePasswordRequestDto.getRawPassword() = {}", updatePasswordRequestDto.getRawPassword());
        LOGGER.info("[AuthService] member.getPassword() = {}", member.getPassword());
        if (!passwordEncoder.matches(updatePasswordRequestDto.getRawPassword(), member.getPassword())) {
            throw new CustomException(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다.");
        }

        String encodePassword = passwordEncoder.encode(updatePasswordRequestDto.getNewPassword());
        member.setPassword(encodePassword);
        LOGGER.info("[AuthService] updatePasswordRequestDto.getNewPassword() = {}", updatePasswordRequestDto.getNewPassword());
        LOGGER.info("[AuthService] passwordEncoder.encode(updatePasswordRequestDto.getNewPassword() = {}", encodePassword);

        Member result = memberRepository.save(member);
        LOGGER.info("[AuthService] result.getPassword() = {}", result.getPassword());

        return result;
    }
}
