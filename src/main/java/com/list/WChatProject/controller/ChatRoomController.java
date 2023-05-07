package com.list.WChatProject.controller;

import com.list.WChatProject.chat.ChatRoom;
import com.list.WChatProject.exception.CustomException;
import com.list.WChatProject.security.jwt.MemberPrincipal;
import com.list.WChatProject.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/chat")
public class ChatRoomController {
    private final ChatService chatService;

    // 모든 채팅방 목록 반환
    @GetMapping("/rooms")
    public List<ChatRoom> room(@AuthenticationPrincipal MemberPrincipal memberPrincipal) {

        if (memberPrincipal.getMember().getNickName() == null) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "닉네임이 설정되지 않았습니다.");
        }

        return chatService.findAllRoom();
    }

    // 채팅방 생성
    @PostMapping("/room")
    public ChatRoom createRoom(@RequestBody String name) {

        return chatService.createRoom(name);
    }

    // 특정 채팅방 조회
    @GetMapping("/room/{roomId}")
    public ChatRoom roomInfo(@PathVariable String roomId) {

        return chatService.findById(roomId);
    }
}