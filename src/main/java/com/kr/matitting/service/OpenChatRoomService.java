package com.kr.matitting.service;

import com.kr.matitting.dto.ChatMessageDto;
import com.kr.matitting.dto.ChatResponseDto;
import com.kr.matitting.dto.OpenChatRoomDto;
import com.kr.matitting.dto.ResponseChatRoomDto;
import com.kr.matitting.entity.ChatMessage;
import com.kr.matitting.entity.ChatRoom;
import com.kr.matitting.repository.ChatMessageRepository;
import com.kr.matitting.repository.ChatRoomRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OpenChatRoomService {
    private final ChatRoomRepository chatRoomRepository;
    private final ChatMessageRepository chatMessageRepository;


    public OpenChatRoomDto getChatRoom(Long chatRoomId) {
        // chatRoomId 를 불러오고 그 다음에 그거 채팅 정보까지 주는걸로 해야함

        // 예외 처리 추가
        ChatRoom chatRoom = chatRoomRepository.findById(chatRoomId).orElseThrow();

        List<ChatMessage> chatList = chatMessageRepository.findByChatRoomId(chatRoomId);
        List<ChatResponseDto> chatMessageDtos = chatList.stream().map(ChatMessage::toDto).toList();

        ResponseChatRoomDto chatRoomDto = chatRoom.toDto();

        return new OpenChatRoomDto(
            chatRoomDto.getId(),
            chatRoomDto.getChatRoomUsers(),
            chatRoomDto.getTitle(),
            chatRoomDto.getChatRoomType(),
            chatRoomDto.getPartyId(),
            chatRoomDto.getParticipantCount(),
            chatMessageDtos);
    }
}
