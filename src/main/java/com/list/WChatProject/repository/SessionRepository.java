package com.list.WChatProject.repository;

import com.list.WChatProject.entity.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {
    Optional<Session> findSessionByMemberId(Long memberId);
    Optional<Session> findSessionByNowSessionId(String nowSessionId);
}
