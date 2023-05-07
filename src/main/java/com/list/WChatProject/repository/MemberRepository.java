package com.list.WChatProject.repository;

import com.list.WChatProject.entity.AccountType;
import com.list.WChatProject.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByUserId(String userId);
    Boolean existsByUserId(String userId);
    Boolean existsByUserIdAndAccountType(String userId, AccountType accountType);
    Optional<Member> findByUserIdAndAccountType(String userId, AccountType accountType);
}
