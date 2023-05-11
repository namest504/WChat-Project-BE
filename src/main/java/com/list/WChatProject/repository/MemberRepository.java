package com.list.WChatProject.repository;

import com.list.WChatProject.entity.AccountType;
import com.list.WChatProject.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByUserId(String userId);
    Boolean existsByUserId(String userId);
    Boolean existsByUserIdAndAccountType(String userId, AccountType accountType);
    Boolean existsByNickName(String nickName);
    Optional<Member> findByUserIdAndAccountType(String userId, AccountType accountType);

    @Query("select m.nickName from Member m where m.id in (select s.member.id from Session s where s.chatRoom.roomId = :roomId)")
    List<Member> findMemberInRoom(@Param("roomId") String roomId);
}
