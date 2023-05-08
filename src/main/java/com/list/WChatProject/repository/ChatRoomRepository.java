package com.list.WChatProject.repository;

import com.list.WChatProject.entity.ChatRoom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, String> {
    ChatRoom findByRoomId(String roomId);
    Optional<List<ChatRoom>> findChatRoomsByRoomNameContaining(String roomName);
    Page<ChatRoom> findAllPage(PageRequest pageRequest);
}
