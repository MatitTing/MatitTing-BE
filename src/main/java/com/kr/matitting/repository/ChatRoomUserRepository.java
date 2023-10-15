package com.kr.matitting.repository;

import com.kr.matitting.entity.ChatRoomUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ChatRoomUserRepository extends JpaRepository<ChatRoomUser, Long> {
    Optional<ChatRoomUser> findById(Long id);

    List<ChatRoomUser> findByUserId(Long userId);
}
