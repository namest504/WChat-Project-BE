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
                jwtService.validateAccessToken(accessor.getFirstNativeHeader("Authorization"));
                break;
            case SUBSCRIBE:
//                String roomId = chatService.getRoomId(Optional.ofNullable((String) message.getHeaders().get("simpDestination")).orElse("InvalidRoomId"));
                String roomId = accessor.getFirstNativeHeader("roomId");
                Long uidFromToken = jwtService.getUidFromToken(accessor.getFirstNativeHeader("Authorization"));
//                Member member = memberRepository.findById(uidFromToken)
//                        .orElseThrow(() -> new StompConversionException("유저 정보가 없습니다."));
//                log.info("accessor.getSessionId() : {}", accessor.getSessionId());
//                log.info("setEnterInfo 동작 전 값 확인 : {} // {} // {}", accessor.getSessionId(), roomId, uidFromToken);
                // SUBSCRIBE 동작시 session에 현재 유저 아이디 uid와, 메세지 헤더 정보의 roomId를 매핑해서 DB에 저장
                Long sessionId = sessionService.setEnterInfo(accessor.getSessionId(), roomId, uidFromToken);
//                sendingOperations.convertAndSend("/topic/chat/room/" + roomId,
//                        ChatMessage.builder()
//                                .type(MessageType.ENTER)
//                                .roomId(roomId)
//                                .sender(member.getNickName())
//                                .message(member.getNickName() + "님이 입장하였습니다.")
//                                .sendAt(LocalDateTime.now().toString())
//                                .build());
                chatService.countPeopleChatRoom(roomId, "SUBSCRIBE");
                log.info("SUBSCRIBE : [ {} ] [ {} ] [ {} ]", sessionId, accessor.getSessionId(), roomId);
                break;

            case DISCONNECT:
                // TODO : DISCONNECT 가 발생시 어떻게 처리할지?

                Session session = sessionRepository.findSessionByNowSessionId(accessor.getSessionId())
                        .orElseThrow(() -> new StompConversionException("올바른 세션이 아닙니다."));
//                sendingOperations.convertAndSend("/topic/chat/room/" + session.getChatRoom().getRoomId(),
//                        ChatMessage.builder()
//                                .type(MessageType.ENTER)
//                                .roomId(session.getChatRoom().getRoomId())
//                                .sender(session.getMember().getNickName())
//                                .message(session.getMember().getNickName() + "님이 입장하였습니다.")
//                                .sendAt(LocalDateTime.now().toString())
//                                .build());
                log.info("DISCONNECT {} 현재 인원수 {}", accessor.getSessionId(), session.getChatRoom().getCountPeople());
                boolean checkCountPeople = chatService.countPeopleChatRoom(session.getChatRoom().getRoomId(), "DISCONNECT");
                log.info("DISCONNECT {} 결과 인원수 {}", accessor.getSessionId(), session.getChatRoom().getCountPeople());
                sessionRepository.delete(session);
                if (checkCountPeople) {
                    chatRoomRepository.deleteById(session.getChatRoom().getRoomId());
                }
//                uidFromToken = jwtService.getUidFromToken(accessor.getFirstNativeHeader("Authorization"));
//                Session session = sessionRepository.findSessionByMemberId(uidFromToken)
//                        .orElseThrow(() -> new CustomException(HttpStatus.BAD_REQUEST, "세션 정보가 없습니다."));
//                chatService.countPeopleChatRoom(session.getChatRoom().getRoomId(), "DISCONNECT");
//                log.info("DISCONNECT : [ {} ] [ {} ] [ {} ]",session.getId(), uidFromToken, session.getChatRoom().getRoomId());
                break;
            default:
                break;
        }
//        if (StompCommand.CONNECT.equals(accessor.getCommand())) {
//            jwtService.validateAccessToken(accessor.getFirstNativeHeader("Authorization"));
//        }

//        public class StompHandler implements ChannelInterceptor {
//
//            private final JwtTokenProvider jwtTokenProvider;
//            private final ChatRoomRepository chatRoomRepository;
//            private final ChatService chatService;
//
//            // websocket을 통해 들어온 요청이 처리 되기전 실행된다.
//            @Override
//                if (StompCommand.CONNECT == accessor.getCommand()) { // websocket 연결요청
//                    String jwtToken = accessor.getFirstNativeHeader("token");
//                    log.info("CONNECT {}", jwtToken);
//                    // Header의 jwt token 검증
//                    jwtTokenProvider.validateToken(jwtToken);
//                } else if (StompCommand.SUBSCRIBE == accessor.getCommand()) { // 채팅룸 구독요청
//                    // header정보에서 구독 destination정보를 얻고, roomId를 추출한다.
//                    String roomId = chatService.getRoomId(Optional.ofNullable((String) message.getHeaders().get("simpDestination")).orElse("InvalidRoomId"));
//                    // 채팅방에 들어온 클라이언트 sessionId를 roomId와 맵핑해 놓는다.(나중에 특정 세션이 어떤 채팅방에 들어가 있는지 알기 위함)
//                    String sessionId = (String) message.getHeaders().get("simpSessionId");
//                    chatRoomRepository.setUserEnterInfo(sessionId, roomId);
//                    // 채팅방의 인원수를 +1한다.
//                    chatRoomRepository.plusUserCount(roomId);
//                    // 클라이언트 입장 메시지를 채팅방에 발송한다.(redis publish)
//                    String name = Optional.ofNullable((Principal) message.getHeaders().get("simpUser")).map(Principal::getName).orElse("UnknownUser");
//                    chatService.sendChatMessage(ChatMessage.builder().type(ChatMessage.MessageType.ENTER).roomId(roomId).sender(name).build());
//                    log.info("SUBSCRIBED {}, {}", name, roomId);
//                } else if (StompCommand.DISCONNECT == accessor.getCommand()) { // Websocket 연결 종료
//                    // 연결이 종료된 클라이언트 sesssionId로 채팅방 id를 얻는다.
//                    String sessionId = (String) message.getHeaders().get("simpSessionId");
//                    String roomId = chatRoomRepository.getUserEnterRoomId(sessionId);
//                    // 채팅방의 인원수를 -1한다.
//                    chatRoomRepository.minusUserCount(roomId);
//                    // 클라이언트 퇴장 메시지를 채팅방에 발송한다.(redis publish)
//                    String name = Optional.ofNullable((Principal) message.getHeaders().get("simpUser")).map(Principal::getName).orElse("UnknownUser");
//                    chatService.sendChatMessage(ChatMessage.builder().type(ChatMessage.MessageType.QUIT).roomId(roomId).sender(name).build());
//                    // 퇴장한 클라이언트의 roomId 맵핑 정보를 삭제한다.
//                    chatRoomRepository.removeUserEnterInfo(sessionId);
//                    log.info("DISCONNECTED {}, {}", sessionId, roomId);
//                }
//                return message;
//            }
//        }

        return message;
    }

}


