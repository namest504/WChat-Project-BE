package com.list.WChatProject.service;

import com.list.WChatProject.entity.ChatRoom;
import com.list.WChatProject.entity.Member;
import com.list.WChatProject.entity.Session;
import com.list.WChatProject.exception.CustomException;
import com.list.WChatProject.repository.ChatRoomRepository;
import com.list.WChatProject.repository.MemberRepository;
import com.list.WChatProject.repository.SessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SessionService {

    private final SessionRepository sessionRepository;
    private final ChatRoomRepository chatRoomRepository;
    private final MemberRepository memberRepository;

    public Long setEnterInfo(Long uid, String roomId) {
        Member member = memberRepository.findById(uid)
                .orElseThrow(() -> new CustomException(HttpStatus.BAD_REQUEST, "유저가 없습니다."));

        ChatRoom chatRoom = chatRoomRepository.findByRoomId(roomId)
                .orElseThrow(() -> new CustomException(HttpStatus.BAD_REQUEST, "방이 없습니다."));

        Session session = Session.builder()
                .chatRoom(chatRoom)
                .member(member)
                .build();

        Session save = sessionRepository.save(session);
        return save.getId();
    }
}
