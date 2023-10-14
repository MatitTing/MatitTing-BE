package com.kr.matitting.entity;

import com.kr.matitting.dto.ChatResponseDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ChatMessage{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chatMessage_id")
    private Long id;

    // 채팅방 id
    @ManyToOne
    @JoinColumn(name = "chatroom_id")
    private ChatRoom chatRoom;
    private ChatRoomUser chatRoomUser;

    // 보내는 사람
    @ManyToOne
    @JoinColumn(name = "chatRoomUser_id")
    private ChatRoomUser sender;

    // 내용
    private String content;

    @CreationTimestamp
    private LocalDateTime createdAt;

    public ChatMessage(ChatRoom chatRoom, ChatRoomUser chatRoomUser, String content) {
        this.chatRoom = chatRoom;
        this.chatRoomUser = chatRoomUser;
        this.content = content;
    }

    public ChatResponseDto toDto() {
        return new ChatResponseDto(id, content, chatRoom.getId(), chatRoomUser.getId(), createdAt);
    }
}
