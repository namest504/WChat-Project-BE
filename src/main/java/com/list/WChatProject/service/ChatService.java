package com.list.WChatProject.service;

import com.list.WChatProject.chat.ChatRoom;
import com.list.WChatProject.chat.MessageType;
import com.list.WChatProject.exception.CustomException;
import com.list.WChatProject.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.*;
@Service
@Slf4j
@RequiredArgsConstructor
public class ChatService {

    private final ChatRoomRepository chatRoomRepository;
//    private Map<String, ChatRoom> chatRooms;

//    @PostConstruct
//    //의존관게 주입완료되면 실행되는 코드
//    private void init() {
//        chatRooms = new LinkedHashMap<>();
//    }

    //채팅방 불러오기
    public List<ChatRoom> findAllRoom() {
        //채팅방 최근 생성 순으로 반환
//        List<ChatRoom> result = new ArrayList<>(chatRooms.values());
        List<ChatRoom> result = chatRoomRepository.findAll();
//        Collections.reverse(result);

        return result;
    }

    //채팅방 하나 불러오기
    public ChatRoom findById(String roomId) {
        return chatRoomRepository.findByRoomId(roomId);

    }

    //채팅방 생성
    public ChatRoom createRoom(String name) {
//        ChatRoom chatRoom = ChatRoom.create(name);
        ChatRoom chatRoom = ChatRoom.builder()
                .roomName(name)
                .roomId(UUID.randomUUID().toString())
                .countPeople(0)
                .build();
//        chatRooms.put(chatRoom.getRoomId(), chatRoom);
        return chatRoomRepository.save(chatRoom);
    }

    @Transactional
    public void countPeopleChatRoom(String roomId, String messageType) {
//        ChatRoom chatRoom = chatRooms.get(roomId);
        ChatRoom chatRoom = chatRoomRepository.findByRoomId(roomId);
        if (messageType == "ENTER"){
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
