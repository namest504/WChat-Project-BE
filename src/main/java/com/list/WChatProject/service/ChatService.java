package com.list.WChatProject.service;

import com.list.WChatProject.controller.ChatRoomController;
import com.list.WChatProject.entity.ChatRoom;
import com.list.WChatProject.entity.QChatRoom;
import com.list.WChatProject.exception.CustomException;
import com.list.WChatProject.repository.ChatRoomRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

import static com.list.WChatProject.dto.ChatRoomDto.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class ChatService {

    private final ChatRoomRepository chatRoomRepository;
    private final PasswordEncoder passwordEncoder;
    private final JPAQueryFactory jpaQueryFactory;

    private final Logger LOGGER = LoggerFactory.getLogger(ChatService.class);

//    private Map<String, ChatRoom> chatRooms;

//    @PostConstruct
//    //의존관게 주입완료되면 실행되는 코드
//    private void init() {
//        chatRooms = new LinkedHashMap<>();
//    }

    //채팅방 하나 불러오기
    public boolean checkRoomEnter(ChatRoomRequestDto chatRoomRequestDto) {
        ChatRoom chatRoom = chatRoomRepository.findById(chatRoomRequestDto.getRoomId())
                .orElseThrow(() -> new CustomException(HttpStatus.BAD_REQUEST, "채팅방이 존재하지 않습니다."));
        if (chatRoom.getCountPeople() >= chatRoom.getMaxPeople()) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "방 인원이 가득 찼습니다.");
        }
        if (chatRoom.isSecret() && !passwordEncoder.matches(chatRoomRequestDto.getRoomPassword(), chatRoom.getPassword())) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "방 비밀번호가 일치하지 않습니다.");
        }
        return true;
    }

    //채팅방 불러오기
    public List<ChatRoom> findAllRoom() {
        //채팅방 최근 생성 순으로 반환
//        List<ChatRoom> result = new ArrayList<>(chatRooms.values());
        List<ChatRoom> result = chatRoomRepository.findAll();

        if (result.isEmpty()) {
            throw new CustomException(HttpStatus.NO_CONTENT, "채팅방이 없습니다.");
        }
//        Collections.reverse(result);

        return result;
    }

    public ChatRoomPageResponseDto pageChatRoom(int page) {
        PageRequest pageRequest = PageRequest.of(page, 8, Sort.Direction.DESC, "createAt");
        Page<ChatRoom> pageChatRoom = chatRoomRepository.findAll(pageRequest);
        return getChatRoomPageResponseDto(pageChatRoom);
    }

    private ChatRoomPageResponseDto getChatRoomPageResponseDto(Page<ChatRoom> chatRooms) {
        Page<ChatRoomResponseDto> toMap = chatRooms.map(
                chatRoom -> new ChatRoomResponseDto(chatRoom.getRoomId(), chatRoom.getRoomName(), chatRoom.getCountPeople(), chatRoom.getMaxPeople(), chatRoom.isSecret()));
        return new ChatRoomPageResponseDto(true, toMap.getContent(), toMap.getTotalPages(), toMap.getTotalElements());
    }

    //채팅방 이름 검색
    public List<ChatRoom> findRoomByRoomName(String roomName) {
//        List<ChatRoom> chatRooms = chatRoomRepository.findChatRoomsByRoomNameContaining(roomName);

//        for (int i = 0; i < 3; i++) {
//            chatRoomRepository.save(ChatRoom.builder()
//                    .roomId("roomId" + i)
//                    .roomName("roomName" + i)
//                    .countPeople(i + 2)
//                    .maxPeople(i + 3)
//                    .isSecret(false)
//                    .build());
//        }
        QChatRoom qChatRoom = new QChatRoom("chatroom");

        List<ChatRoom> chatRoomList = jpaQueryFactory
                .selectFrom(qChatRoom)
                .where(qChatRoom.roomName.contains(roomName))
                .fetch();

        log.info("출력 시작");
        for (ChatRoom chatRoom : chatRoomList) {
            log.info("{}",chatRoom.getRoomName());
        }
        return chatRoomList;
    }

    //채팅방 생성
    public ChatRoom createRoom(ChatRoomCreateRequestDto chatRoomCreateRequestDto) {
//        ChatRoom chatRoom = ChatRoom.create(name);
        if (!chatRoomCreateRequestDto.isSecret()) {
            ChatRoom chatRoom = ChatRoom.builder()
                    .roomName(chatRoomCreateRequestDto.getRoomName())
                    .roomId(UUID.randomUUID().toString())
                    .maxPeople(chatRoomCreateRequestDto.getMaxPeople())
                    .isSecret(chatRoomCreateRequestDto.isSecret())
                    .countPeople(0)
                    .createAt(LocalDateTime.now())
                    .build();

            return chatRoomRepository.save(chatRoom);
        } else if (chatRoomCreateRequestDto.getRoomPassword() != null) {
            ChatRoom chatRoom = ChatRoom.builder()
                    .roomName(chatRoomCreateRequestDto.getRoomName())
                    .roomId(UUID.randomUUID().toString())
                    .maxPeople(chatRoomCreateRequestDto.getMaxPeople())
                    .isSecret(chatRoomCreateRequestDto.isSecret())
                    .password(passwordEncoder.encode(chatRoomCreateRequestDto.getRoomPassword()))
                    .countPeople(0)
                    .createAt(LocalDateTime.now())
                    .build();

            return chatRoomRepository.save(chatRoom);
        } else {
            throw new CustomException(HttpStatus.BAD_REQUEST, "방 생성에 실패하였습니다.");
        }

//        chatRooms.put(chatRoom.getRoomId(), chatRoom);

    }

    @Transactional
    public boolean countPeopleChatRoom(String roomId, String type) {
//        ChatRoom chatRoom = chatRooms.get(roomId);
        ChatRoom chatRoom = chatRoomRepository.findByRoomId(roomId)
                .orElseThrow(() -> new CustomException(HttpStatus.BAD_REQUEST, "방이 없습니다."));

        switch (type) {
            case "SUBSCRIBE":
                chatRoom.setCountPeople(chatRoom.getCountPeople() + 1);
                chatRoomRepository.save(chatRoom);
                break;
            case "DISCONNECT":
                chatRoom.setCountPeople(chatRoom.getCountPeople() - 1);
                chatRoomRepository.save(chatRoom);
                break;
            default:
                throw new CustomException(HttpStatus.BAD_REQUEST, "타입 지정이 되어야 합니다.");
        }
        if (chatRoom.getCountPeople() == 0) {
            //채팅방 인원이 0 명이면 true 반환
            return true;
//            chatRoomRepository.deleteById(roomId);
        }
        //채팅방에 인원이 남아있으면 false 반환
        return false;
    }
}
