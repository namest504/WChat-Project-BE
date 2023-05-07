package com.list.WChatProject.controller;

import com.list.WChatProject.chat.ChatRoom;
import com.list.WChatProject.exception.CustomException;
import com.list.WChatProject.security.jwt.MemberPrincipal;
import com.list.WChatProject.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/chat")
public class ChatRoomController {
    private final ChatService chatService;
    private final Logger LOGGER = LoggerFactory.getLogger(ChatRoomController.class);

    // 모든 채팅방 목록 반환
    @GetMapping("/rooms")
    public List<ChatRoom> room(@AuthenticationPrincipal MemberPrincipal memberPrincipal) {

        if (memberPrincipal.getMember().getNickName() == null) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "닉네임이 설정되지 않았습니다.");
        }

        return chatService.findAllRoom();
    }

    // 채팅방 생성
    @PostMapping("/create")
    public ChatRoom createRoom(@AuthenticationPrincipal MemberPrincipal memberPrincipal,@RequestBody String name) {
        LOGGER.info("[{}] 님이 [{}] 방을 생성하였습니다.", memberPrincipal.getMember().getName(), name);
        return chatService.createRoom(name);
    }

    // 특정 채팅방 조회
    @GetMapping("/room/{roomId}")
    public ChatRoom roomInfo(@PathVariable String roomId) {

        return chatService.findById(roomId);
    }
}