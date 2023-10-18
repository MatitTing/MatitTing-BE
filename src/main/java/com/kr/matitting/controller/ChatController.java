package com.kr.matitting.controller;

import com.kr.matitting.dto.ChatRequestDto;
import com.kr.matitting.dto.ChatResponseDto;
import com.kr.matitting.dto.RequestOneOnOneChatRoomsDto;
import com.kr.matitting.dto.ResponseChatRoomDto;
import com.kr.matitting.service.ChatService;
import com.kr.matitting.service.GetOneOnOneChatRoomsService;
import com.kr.matitting.service.OpenChatRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ChatController {
    private final ChatService chatService;
    private final GetOneOnOneChatRoomsService getOneOnOneChatRoomsService;
    private final OpenChatRoomService openChatRoomService;

    @MessageMapping("chat/{roomNo}")
    @SendTo("sub/chat/{roomNo}")
    public ChatResponseDto sendChat(ChatRequestDto chatRequestDto,
                                @DestinationVariable(value = "roomNo") Long chatRoomNo) {
        return chatService.recordHistory(chatRoomNo, chatRequestDto);
    }

//    @MessageMapping("chat/{roomNo}/enter")
//    public ChatResponseDto enterChatRoom(@DestinationVariable(value = "roomNo") Long chatRoomNo) {
//        //
//        // 웹소켓은 HTTP통신이 아니기 때문에 SpringSecurity가 적용이 안된다. 인터셉터를 구현을 해야한다.
//        return chatService.enterChatRoom(chatRoomEnterDto, chatRoomNo);
//    }

    // 1대1 채팅방 목록 불러오기
    @GetMapping("/api/chatRooms/1on1")   //
    public List<ResponseChatRoomDto> oneOnOneChatRooms(
        RequestOneOnOneChatRoomsDto requestOneOnOneChatRoomsDto
    ) {

        return getOneOnOneChatRoomsService.getChatRooms(
            requestOneOnOneChatRoomsDto.getUserId());
    }

    // 채팅방을 불러와야 한다.
    @GetMapping("/api/chatRoom/{chatRoomId}/open")
    public ResponseChatRoomDto openChatRoom(
        @PathVariable(value = "chatRoomId") Long chatRoomId
    ) {
        return openChatRoomService.getChatRoom(chatRoomId);
    }
    // 채팅방 DTO에 마지막 메시지에 대한 정보를 담아야 한다.
}

    // 1대1 채팅 신청
