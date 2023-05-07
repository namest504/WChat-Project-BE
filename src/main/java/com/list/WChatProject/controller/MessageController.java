package com.list.WChatProject.controller;

import com.list.WChatProject.chat.ChatMessage;
import com.list.WChatProject.chat.MessageType;
import com.list.WChatProject.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class MessageController {

    private final SimpMessageSendingOperations sendingOperations;
    private final ChatService chatService;
    private final Logger LOGGER = LoggerFactory.getLogger(MessageController.class);
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
        LOGGER.info("[/topic/chat/room/{}]으로 ", message.getRoomId());
        LOGGER.info("[{}] 가 [{}]를 보냈습니다.", message.getSender(), message.getRoomId(), message.getMessage());
    }
}
