package com.list.WChatProject.controller;

import com.list.WChatProject.chat.ChatMessage;
import com.list.WChatProject.chat.MessageType;
import com.list.WChatProject.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class MessageController {

    private final SimpMessageSendingOperations sendingOperations;
    private final ChatService chatService;

    @MessageMapping("/chat/message")
    public void enter(ChatMessage message) {
        if (MessageType.ENTER.equals(message.getType())) {
            message.setMessage(message.getSender() + "님이 입장하였습니다.");
            chatService.countPeopleChatRoom(message.getRoomId(), message.getType().getValue());
        }
        if (MessageType.EXIT.equals(message.getType())) {
            message.setMessage(message.getSender() + "님이 퇴장하였습니다.");
            chatService.countPeopleChatRoom(message.getRoomId(), message.getType().getValue());
        }
        sendingOperations.convertAndSend("/topic/chat/room/" + message.getRoomId(), message);
    }
}
