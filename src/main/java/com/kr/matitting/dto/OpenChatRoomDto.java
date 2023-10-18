package com.kr.matitting.dto;

import com.kr.matitting.constant.ChatRoomType;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class OpenChatRoomDto {
    private Long id;
    private List<ChatRoomUserDto> chatRoomUsers;
    private String title;
    private ChatRoomType chatRoomType;
    private Long partyId;
    private Long participantCount;
    private List<ChatResponseDto> chatMessageDtos;

    public OpenChatRoomDto(Long id, List<ChatRoomUserDto> chatRoomUsers, String title, ChatRoomType chatRoomType, Long partyId, Long participantCount, List<ChatResponseDto> chatMessageDtos) {

        this.id = id;
        this.chatRoomUsers = chatRoomUsers;
        this.title = title;
        this.chatRoomType = chatRoomType;
        this.partyId = partyId;
        this.participantCount = participantCount;
        this.chatMessageDtos = chatMessageDtos;
    }
}
