package com.kr.matitting.entity;

import com.kr.matitting.constant.ChatRoomRole;
import com.kr.matitting.dto.ChatRoomUserDto;
import com.kr.matitting.dto.UserDto;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChatRoomUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="chatroom_id")
    private ChatRoom chatRoom;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    private ChatRoomRole chatRoomRole;

    public ChatRoomUser(ChatRoom chatRoom, User user, ChatRoomRole chatRoomRole) {
        this.chatRoom = chatRoom;
        this.user = user;
        this.chatRoomRole = chatRoomRole;
    }

    public ChatRoomUserDto toDto() {
         // DTO에는 유저의 정보를 포함을 시켜야 한다. 프로필 이름 성별
        String nickName = user.getNickname();
        int age = user.getAge();
        String imgUrl = user.getImgUrl();
        String city = user.getCity();
        return new ChatRoomUserDto(id , nickName, age, imgUrl, city);
    }
}
