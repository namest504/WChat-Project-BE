package com.list.WChatProject.repository;

import com.list.WChatProject.entity.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {
    Optional<Session> findSessionByMemberId(Long memberId);
    Optional<Session> findSessionByNowSessionId(String nowSessionId);

//    @Query("select s.member.id from Session s where s.chatRoom.roomId = :roomId")
//    List<Session> findSessionInRoom(@Param("roomId") String roomId);
    List<Session> findSessionByChatRoom_RoomId(String roomId);
}
