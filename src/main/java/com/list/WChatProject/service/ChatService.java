package com.list.WChatProject.service;

import com.list.WChatProject.entity.ChatRoom;
import com.list.WChatProject.exception.CustomException;
import com.list.WChatProject.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
//    private Map<String, ChatRoom> chatRooms;

//    @PostConstruct
//    //의존관게 주입완료되면 실행되는 코드
//    private void init() {
//        chatRooms = new LinkedHashMap<>();
//    }

    //채팅방 하나 불러오기
    public boolean checkPassword(ChatRoomRequestDto chatRoomRequestDto) {
        ChatRoom chatRoom = chatRoomRepository.findById(chatRoomRequestDto.getRoomId())
                .orElseThrow(() -> new CustomException(HttpStatus.NO_CONTENT, "채팅방이 존재하지 않습니다."));
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

    //채팅방 하나 불러오기
    public List<ChatRoom> findRoomByRoomName(String roomName) {
        List<ChatRoom> chatRooms = chatRoomRepository.findChatRoomsByRoomNameContaining(roomName)
                .orElseThrow(() -> new CustomException(HttpStatus.BAD_REQUEST, "방이 없습니다."));
        return chatRooms;
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
    public void countPeopleChatRoom(String roomId, String messageType) {
//        ChatRoom chatRoom = chatRooms.get(roomId);
        ChatRoom chatRoom = chatRoomRepository.findByRoomId(roomId);
        if (messageType == "ENTER") {
            chatRoom.setCountPeople(chatRoom.getCountPeople() + 1);
            chatRoomRepository.save(chatRoom);
        } else if (messageType == "EXIT") {
            chatRoom.setCountPeople(chatRoom.getCountPeople() - 1);
            if (chatRoom.getCountPeople() == 0) {
                chatRoomRepository.deleteById(roomId);
            }
            chatRoomRepository.save(chatRoom);
        } else {
            throw new CustomException(HttpStatus.BAD_REQUEST, "메세지 타입 지정이 되어야 합니다.");
        }
    }
}
