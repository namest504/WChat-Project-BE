package com.list.WChatProject.security;

import com.list.WChatProject.chat.ChatMessage;
import com.list.WChatProject.chat.MessageType;
import com.list.WChatProject.entity.Member;
import com.list.WChatProject.entity.Session;
import com.list.WChatProject.exception.CustomException;
import com.list.WChatProject.repository.ChatRoomRepository;
import com.list.WChatProject.repository.MemberRepository;
import com.list.WChatProject.repository.SessionRepository;
import com.list.WChatProject.service.ChatService;
import com.list.WChatProject.service.JwtService;
import com.list.WChatProject.service.SessionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompConversionException;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@RequiredArgsConstructor
@Component
@Slf4j
public class StompHandler implements ChannelInterceptor {
    private final JwtService jwtService;
    private final ChatService chatService;
    private final SessionService sessionService;
    private final MemberRepository memberRepository;
    private final SessionRepository sessionRepository;
    private final ChatRoomRepository chatRoomRepository;
    private SimpMessageSendingOperations sendingOperations;

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
        switch (accessor.getCommand()) {
            case CONNECT:
//                log.info("토큰 검증 직전 = [ {} ]", accessor.getFirstNativeHeader("Authorization"));
                jwtService.validateAccessToken(accessor.getFirstNativeHeader("Authorization"));
                break;
            case SUBSCRIBE:
//                log.info("SUBSCRIBE 시작 {}", accessor.getSessionId());
                String roomId = accessor.getFirstNativeHeader("roomId");
                Long uidFromToken = jwtService.getUidFromToken(accessor.getFirstNativeHeader("Authorization"));
//                log.info("accessor.getSessionId() : {}", accessor.getSessionId());
//                log.info("setEnterInfo 동작 전 값 확인 : {} // {} // {}", accessor.getSessionId(), roomId, uidFromToken);
                // SUBSCRIBE 동작시 session에 현재 유저 아이디 uid와, 메세지 헤더 정보의 roomId를 매핑해서 DB에 저장
                Long sessionId = sessionService.setEnterInfo(accessor.getSessionId(), roomId, uidFromToken);
                chatService.countPeopleChatRoom(roomId, "SUBSCRIBE");
                log.info("SUBSCRIBE : [ {} ] [ {} ] [ {} ]", sessionId, accessor.getSessionId(), roomId);
                break;

            case UNSUBSCRIBE:
                log.info("UNSUBSCRIBE 시작 {}", accessor.getSessionId());
                Session session = sessionRepository.findSessionByNowSessionId(accessor.getSessionId())
                        .orElseThrow(() -> new StompConversionException("올바른 세션이 아닙니다."));
//                log.info("DISCONNECT {} 현재 인원수 {}", accessor.getSessionId(), session.getChatRoom().getCountPeople());
                boolean checkCountPeople = chatService.countPeopleChatRoom(session.getChatRoom().getRoomId(), "DISCONNECT");
//                log.info("DISCONNECT {} 결과 인원수 {}", accessor.getSessionId(), session.getChatRoom().getCountPeople());
                sessionRepository.delete(session);
                if (checkCountPeople) {
                    chatRoomRepository.deleteById(session.getChatRoom().getRoomId());
                }
//                log.info("DISCONNECT : [ {} ] [ {} ] [ {} ]",session.getId(), uidFromToken, session.getChatRoom().getRoomId());
                break;

            case DISCONNECT:
                log.info("DISCONNECT 시작 {}", accessor.getSessionId());
                session = sessionRepository.findSessionByNowSessionId(accessor.getSessionId())
                        .orElseThrow(() -> new StompConversionException("올바른 세션이 아닙니다."));
//                log.info("DISCONNECT {} 현재 인원수 {}", accessor.getSessionId(), session.getChatRoom().getCountPeople());
                checkCountPeople = chatService.countPeopleChatRoom(session.getChatRoom().getRoomId(), "DISCONNECT");
//                log.info("DISCONNECT {} 결과 인원수 {}", accessor.getSessionId(), session.getChatRoom().getCountPeople());
                sessionRepository.delete(session);
                if (checkCountPeople) {
                    chatRoomRepository.deleteById(session.getChatRoom().getRoomId());
                }
//                log.info("DISCONNECT : [ {} ] [ {} ] [ {} ]",session.getId(), uidFromToken, session.getChatRoom().getRoomId());
                break;

            default:
                break;
        }
        return message;
    }

}


