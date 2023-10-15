package com.kr.matitting.service;

import com.kr.matitting.dto.ResponseChatRoomDto;
import com.kr.matitting.entity.ChatRoom;
import com.kr.matitting.entity.ChatRoomUser;
import com.kr.matitting.repository.ChatRoomRepository;
import com.kr.matitting.repository.ChatRoomUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GetOneOnOneChatRoomsService {
    private final ChatRoomUserRepository chatRoomUserRepository;
    private final ChatRoomRepository chatRoomRepository;

    public List<ResponseChatRoomDto> getChatRooms(Long userId) {
        // chatRoomUser를 리스트로 찾은 다음에 for문 돌려서 하나씩 ChatRoom을 찾아서 DTO로 변경해주면 됨

        List<ChatRoom> chatRooms = new ArrayList<>();

        List<ChatRoomUser> chatRoomUsers = chatRoomUserRepository.findByUserId(userId);

        for (ChatRoomUser chatRoomUser : chatRoomUsers) {
            chatRooms.add(chatRoomUser.getChatRoom());
        }

        chatRooms.stream().forEach(chatRoom -> chatRoom.toDto());

        return null;
    }
}
