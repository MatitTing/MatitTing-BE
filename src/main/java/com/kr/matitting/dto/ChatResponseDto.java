package com.kr.matitting.dto;

import jakarta.persistence.GeneratedValue;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@Setter
public class ChatResponseDto {
    private Long id;

    private String content;

    private Long chatRoomId;

    private Long senderId;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createdAt;

    public ChatResponseDto(Long id, String content, Long chatRoomId, Long senderId, LocalDateTime createdAt) {
        this.id = id;
        this.content = content;
        this.chatRoomId = chatRoomId;
        this.senderId = senderId;
        this.createdAt = createdAt;
    }
}
