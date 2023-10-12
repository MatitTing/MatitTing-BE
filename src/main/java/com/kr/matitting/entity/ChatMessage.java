package com.kr.matitting.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessage {
    @Id
    @GeneratedValue
    private Long id;

    // 채팅방 id
    private Long chatRoomId;

    // 보내는 사람
    private Long senderId;

    // 내용
    private String content;
}
