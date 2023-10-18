package com.kr.matitting.service;

import com.kr.matitting.dto.ChatRequestDto;
import com.kr.matitting.dto.ChatResponseDto;
import com.kr.matitting.entity.ChatMessage;
import com.kr.matitting.entity.ChatRoom;
import com.kr.matitting.entity.ChatRoomUser;
import com.kr.matitting.repository.ChatMessageRepository;
import com.kr.matitting.repository.ChatRoomRepository;
import com.kr.matitting.repository.ChatRoomUserRepository;
import org.springframework.stereotype.Service;

@Service
public class ChatService {
    private final ChatMessageRepository chatMessageRepository;
    private final ChatRoomUserRepository chatRoomUserRepository;
    private final ChatRoomRepository chatRoomRepository;

    public ChatService(ChatMessageRepository chatMessageRepository,
                       ChatRoomUserRepository chatRoomUserRepository,
                       ChatRoomRepository chatRoomRepository) {
        this.chatMessageRepository = chatMessageRepository;
        this.chatRoomUserRepository = chatRoomUserRepository;
        this.chatRoomRepository = chatRoomRepository;
    }

    public ChatResponseDto recordHistory(Long chatRoomNo, ChatRequestDto chatRequestDto) {

        // 예외처리 필요함
        ChatRoomUser chatRoomUser = chatRoomUserRepository.findById(chatRequestDto.getSenderId())
            .orElseThrow();
        ChatRoom chatRoom = chatRoomRepository.findById(chatRoomNo)
            .orElseThrow();

        ChatMessage chatMessage = new ChatMessage(chatRoom, chatRoomUser, chatRequestDto.getContent());
        chatMessageRepository.save(chatMessage);

        return chatMessage.toDto();
    }
}
