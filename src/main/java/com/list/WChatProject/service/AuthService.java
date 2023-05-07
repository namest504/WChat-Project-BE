package com.list.WChatProject.service;

import com.list.WChatProject.entity.AccountType;
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

    public boolean isRegisterdKakao(String userId) {
        return memberRepository.existsByUserIdAndAccountType(userId, AccountType.KAKAO);
    }

    public Long getUidFromKakaoAccount(String userId) {
        return memberRepository.findByUserIdAndAccountType(userId, AccountType.KAKAO)
                .orElseThrow(() -> new CustomException(HttpStatus.BAD_REQUEST, "kakao로 로그인된 아이디가 없습니다."))
                .getId();
    }

    public Member register(RegisterRequestDto registerRequestDto) {
        if (memberRepository.existsByUserId(registerRequestDto.getUserId())) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "이미 가입되어있는 아이디 입니다.");
        }

        if (registerRequestDto.getAccountType() == AccountType.KAKAO) {
            Member member = Member.builder()
                    .userId(registerRequestDto.getUserId())
                    .isBan(false)
                    .accountType(AccountType.KAKAO)
                    .build();
            return memberRepository.save(member);
        } else {
            Member member = Member.builder()
                    .userId(registerRequestDto.getUserId())
                    .password(passwordEncoder.encode(registerRequestDto.getPassword()))
                    .isBan(false)
                    .accountType(AccountType.BASIC)
                    .build();
            return memberRepository.save(member);
        }


    }

    public Long login(LoginDto login) {
        Member member = memberRepository.findByUserId(login.getUserId())
                .orElseThrow(
                        () -> new CustomException(HttpStatus.UNAUTHORIZED, "아이디 또는 비밀번호가 일치하지 않습니다."));

        if (!passwordEncoder.matches(login.getPassword(), member.getPassword())) {
            throw new CustomException(HttpStatus.UNAUTHORIZED, "아이디 또는 비밀번호가 일치하지 않습니다.");
        }

        return member.getId();
    }

    public Boolean saveRefreshToken(Long loginId, String refreshToken) {
        RefreshToken rt = RefreshToken.builder()
                .key(loginId.toString())
                .value(refreshToken)
                .build();
        refreshTokenRepository.save(rt);

        return true;
    }

    public Member inquire(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(
                        () -> new CustomException(HttpStatus.UNAUTHORIZED, "일치하는 유저가 없습니다."));
        return member;
    }

    public Member updatePassword(Long uid, UpdatePasswordRequestDto updatePasswordRequestDto) {
        Member member = memberRepository.findById(uid)
                .orElseThrow(
                        () -> new CustomException(HttpStatus.UNAUTHORIZED, "일치하는 유저가 없습니다."));
        if (!passwordEncoder.matches(updatePasswordRequestDto.getRawPassword(), member.getPassword())) {
            throw new CustomException(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다.");
        }

        String encodePassword = passwordEncoder.encode(updatePasswordRequestDto.getNewPassword());
        member.setPassword(encodePassword);

        Member result = memberRepository.save(member);

        return result;
    }
}
