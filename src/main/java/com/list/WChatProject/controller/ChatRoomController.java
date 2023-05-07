package com.list.WChatProject.controller;

import com.list.WChatProject.chat.ChatRoom;
import com.list.WChatProject.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/chat")
public class ChatRoomController {
    private final ChatService chatService;

    // 모든 채팅방 목록 반환
    @GetMapping("/rooms")
    public List<ChatRoom> room() {

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