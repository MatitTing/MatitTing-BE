package com.kr.matitting.controller;

import com.kr.matitting.dto.ChatRequestDto;
import com.kr.matitting.dto.ChatResponseDto;
import com.kr.matitting.service.ChatService;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import static org.hibernate.query.sqm.tree.SqmNode.log;

@Controller
public class ChatController {
    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }


    @MessageMapping("/chat/{roomNo}")
    @SendTo("sub/chat/{roomNo}")
    public ChatResponseDto chat(ChatRequestDto chatRequestDto,
                                @DestinationVariable(value = "roomNo") Long chatRoomNo) {

        return chatService.recordHistory(chatRoomNo, chatRequestDto);
    }
}
