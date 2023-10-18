package com.kr.matitting.dto;

import com.kr.matitting.constant.ChatRoomType;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class ResponseChatRoomDto {
    private Long id;
    private List<ChatRoomUserDto> chatRoomUsers;
    private Long participantCount;
    private String title;
    private Long partyId;
    private ChatRoomType chatRoomType;


    public ResponseChatRoomDto(Long id, List<ChatRoomUserDto> chatRoomUsers, Long participantCount, String title, Long partyId, ChatRoomType chatRoomType) {
        this.id = id;
        this.chatRoomUsers = chatRoomUsers;
        this.participantCount = participantCount;
        this.title = title;
        this.partyId = partyId;
        this.chatRoomType = chatRoomType;
    }
}
