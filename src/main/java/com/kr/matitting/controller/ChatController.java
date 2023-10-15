package com.kr.matitting.controller;

import com.kr.matitting.dto.ChatRequestDto;
import com.kr.matitting.dto.ChatResponseDto;
import com.kr.matitting.dto.RequestOneOnOneChatRoomsDto;
import com.kr.matitting.dto.ResponseChatRoomDto;
import com.kr.matitting.service.ChatService;
import com.kr.matitting.service.GetOneOnOneChatRoomsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ChatController {
    private final ChatService chatService;
    private final GetOneOnOneChatRoomsService getOneOnOneChatRoomsService;

    @MessageMapping("/chat/{roomNo}")
    @SendTo("sub/chat/{roomNo}")
    public ChatResponseDto chat(ChatRequestDto chatRequestDto,
                                @DestinationVariable(value = "roomNo") Long chatRoomNo) {

        return chatService.recordHistory(chatRoomNo, chatRequestDto);
    }


    // 1대1 채팅 신청
    // 1대1 채팅방 목록 불러오기
    @GetMapping("/api/chatRooms/1on1")   //
    public ResponseEntity<List<ResponseChatRoomDto>> oneOnOneChatRooms(
        RequestOneOnOneChatRoomsDto requestOneOnOneChatRoomsDto
    ) {

        List<ResponseChatRoomDto> responseChatRoomDtos = getOneOnOneChatRoomsService.getChatRooms(
            requestOneOnOneChatRoomsDto.getUserId());
        return
    }


}
