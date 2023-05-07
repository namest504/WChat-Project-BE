package com.list.WChatProject.service;

import com.list.WChatProject.chat.ChatRoom;
import com.list.WChatProject.chat.MessageType;
import com.list.WChatProject.exception.CustomException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;
@Service
@Slf4j
@RequiredArgsConstructor
public class ChatService {

    private Map<String, ChatRoom> chatRooms;

    @PostConstruct
    //의존관게 주입완료되면 실행되는 코드
    private void init() {
        chatRooms = new LinkedHashMap<>();
    }

    //채팅방 불러오기
    public List<ChatRoom> findAllRoom() {
        //채팅방 최근 생성 순으로 반환
        List<ChatRoom> result = new ArrayList<>(chatRooms.values());
        Collections.reverse(result);

        return result;
    }

    //채팅방 하나 불러오기
    public ChatRoom findById(String roomId) {
        return chatRooms.get(roomId);
    }

    //채팅방 생성
    public ChatRoom createRoom(String name) {
        ChatRoom chatRoom = ChatRoom.create(name);
        chatRooms.put(chatRoom.getRoomId(), chatRoom);
        return chatRoom;
    }

    public void countPeopleChatRoom(String roomId, String messageType) {
        ChatRoom chatRoom = chatRooms.get(roomId);
        if (messageType == "ENTER"){
            chatRoom.setCountPeople(chatRoom.getCountPeople() + 1);
        } else if (messageType == "EXIT") {
            chatRoom.setCountPeople(chatRoom.getCountPeople() - 1);
        } else {
            throw new CustomException(HttpStatus.BAD_REQUEST, "메세지 타입 지정이 되어야 합니다.");
        }
    }
}
