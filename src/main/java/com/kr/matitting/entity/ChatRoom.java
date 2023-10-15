package com.kr.matitting.entity;

import com.kr.matitting.constant.ChatRoomRole;
import com.kr.matitting.constant.ChatRoomType;
import com.kr.matitting.dto.ResponseChatRoomDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Setter
public class ChatRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chatRoom_id")
    private Long id;

    // 채팅방 참가자
    @OneToMany(mappedBy = "chatRoom")
    private List<ChatRoomUser> chatRoomUsers = new ArrayList<>();

    // 채팅방 인원
    private Long participantCount;

    // 채팅방 이름(파티 제목과 동일)
    private String title;

    // 파티 아이디
    private Long partyId;

    // 1대1 채팅 , 다대다 채팅
    @Enumerated(EnumType.STRING)
    private ChatRoomType chatRoomType;

    public ChatRoom(List<ChatRoomUser> chatRoomUsers, Long participantCount,
                    String title, Long partyId, ChatRoomType chatRoomType) {
        this.chatRoomUsers = chatRoomUsers;
        this.participantCount = participantCount;
        this.title = title;
        this.partyId = partyId;
        this.chatRoomType = chatRoomType;
    }

    // 채팅방 인원 추가 및 참가자 인원 카운트
    // 방장이랑 채팅을 해야하기 때문에 방장롤이 필요하다
    // 즉 1대1 신청을 하면 그 파티의 장과 대화를 해야함
    public void addParticipant(ChatRoom chatRoom, User user, ChatRoomRole chatRoomRole) {
        ChatRoomUser chatRoomUser = new ChatRoomUser(chatRoom, user, chatRoomRole);

        this.chatRoomUsers.add(chatRoomUser);
        this.participantCount = (long) this.chatRoomUsers.size();
        user.addChatRoomUser(chatRoomUser);
    }

    public void toDto() {
        return new ResponseChatRoomDto();  
    }
}
