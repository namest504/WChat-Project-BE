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
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static com.list.WChatProject.dto.MemberDto.*;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final MemberRepository memberRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final PasswordEncoder passwordEncoder;

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
                    .userId(registerRequestDto.getId())
                    .nickName(registerRequestDto.getId())
                    .name(registerRequestDto.getNickName())
                    .isBan(false)
                    .accountType(AccountType.KAKAO)
                    .changeAt(LocalDateTime.now())
                    .build();
            return memberRepository.save(member);
        } else {
            Member member = Member.builder()
                    .userId(registerRequestDto.getId())
                    .nickName(registerRequestDto.getId())
                    .name(registerRequestDto.getNickName())
                    .password(passwordEncoder.encode(registerRequestDto.getPassword()))
                    .isBan(false)
                    .accountType(AccountType.BASIC)
                    .changeAt(LocalDateTime.now())
                    .build();
            return memberRepository.save(member);
        }
    }

    @Transactional
    public ChangeNickNameResponseDto changeNickName(Long uId, String nickName) {
        Member member = memberRepository.findById(uId)
                .orElseThrow(() -> new CustomException(HttpStatus.UNAUTHORIZED, "로그인이 필요한 기능입니다."));

        if (memberRepository.existsByNickName(nickName)) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "이미 존재하는 닉네임입니다.");
        }

        if (LocalDateTime.now().isBefore(member.getChangeAt().plusMinutes(1))) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "닉네임 변경 쿨타임입니다.");
        }
        member.setNickName(nickName);
        member.setChangeAt(LocalDateTime.now());
        memberRepository.save(member);
        return new ChangeNickNameResponseDto(true, member.getChangeAt().plusMinutes(1));
    }

    @Transactional
    public Boolean logout(Long uid) {
        refreshTokenRepository.deleteByKey(uid.toString());
        return true;
    }

//    public Long login(LoginDto login) {
//        Member member = memberRepository.findByUserId(login.getUserId())
//                .orElseThrow(
//                        () -> new CustomException(HttpStatus.UNAUTHORIZED, "아이디 또는 비밀번호가 일치하지 않습니다."));
//
//        if (!passwordEncoder.matches(login.getPassword(), member.getPassword())) {
//            throw new CustomException(HttpStatus.UNAUTHORIZED, "아이디 또는 비밀번호가 일치하지 않습니다.");
//        }
//
//        return member.getId();
//    }

    public String getRefreshToken(Long uid) {
        RefreshToken refreshToken = refreshTokenRepository.findByKey(uid.toString())
                .orElseThrow(() -> new CustomException(HttpStatus.BAD_REQUEST, "로그아웃 된 사용자입니다."));
        return refreshToken.toString();
    }

    public Boolean saveRefreshToken(Long loginId, String refreshToken) {
        RefreshToken rt = RefreshToken.builder()
                .key(loginId.toString())
                .value(refreshToken)
                .build();
        refreshTokenRepository.save(rt);

        return true;
    }

//    public Member inquire(Long memberId) {
//        Member member = memberRepository.findById(memberId)
//                .orElseThrow(
//                        () -> new CustomException(HttpStatus.UNAUTHORIZED, "일치하는 유저가 없습니다."));
//        return member;
//    }
//
//    public Member updatePassword(Long uid, UpdatePasswordRequestDto updatePasswordRequestDto) {
//        Member member = memberRepository.findById(uid)
//                .orElseThrow(
//                        () -> new CustomException(HttpStatus.UNAUTHORIZED, "일치하는 유저가 없습니다."));
//        if (!passwordEncoder.matches(updatePasswordRequestDto.getRawPassword(), member.getPassword())) {
//            throw new CustomException(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다.");
//        }
//
//        String encodePassword = passwordEncoder.encode(updatePasswordRequestDto.getNewPassword());
//        member.setPassword(encodePassword);
//
//        Member result = memberRepository.save(member);
//
//        return result;
//    }
}
