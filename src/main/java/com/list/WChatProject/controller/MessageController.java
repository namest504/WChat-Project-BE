package com.list.WChatProject.controller;

import com.list.WChatProject.chat.ChatMessage;
import com.list.WChatProject.chat.MessageType;
import com.list.WChatProject.exception.CustomException;
import com.list.WChatProject.service.ChatService;
import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;


@RestController
public class MessageController {

    private SimpMessageSendingOperations sendingOperations;
    private ChatService chatService;
    private final Bucket bucket;
    private final Logger LOGGER = LoggerFactory.getLogger(MessageController.class);

    public MessageController(ChatService chatService,SimpMessageSendingOperations sendingOperations) {
        //10분에 10개의 요청을 처리할 수 있는 Bucket 생성
        Bandwidth limit = Bandwidth.classic(1, Refill.greedy(1, Duration.ofSeconds(1)));
        this.bucket = Bucket.builder()
                .addLimit(limit)
                .build();
        this.chatService = chatService;
        this.sendingOperations = sendingOperations;
    }
    //Refill.intervally toekn = 2, 1회충전시 2개의 토큰을 충전
    //Duration.ofSeconds = 1, 1초마다 토큰을 충전
//    Refill refill = Refill.intervally(2, Duration.ofSeconds(1));

    //Bandwidth capacity = Bucket의 총 크기는 3
//    Bandwidth limit = Bandwidth.classic(3, refill);

    //총크기가 3이며 1초마다 2개의 Token을 충전하는 Bucket 생성
//    Bucket bucket = Bucket.builder().addLimit(limit).build();

    //bucket.tryConsume(1)
    @MessageMapping("/chat/message")
    public void enter(ChatMessage message) {
//        if (message.getMessage() == null) {
//            throw new CustomException(HttpStatus.BAD_REQUEST, "전송할 메세지가 없습니다.");
//        }
        if (bucket.tryConsume(1)) {
            LOGGER.info("사용 가능한 토큰 수 {}", bucket.getAvailableTokens());
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
            LOGGER.info("[{}] 가 [{}]를 보냈습니다.", message.getSender(), message.getMessage());
        } else {
            throw new CustomException(HttpStatus.TOO_MANY_REQUESTS, "메세지 전송량이 너무 많습니다.");
        }

    }
}
