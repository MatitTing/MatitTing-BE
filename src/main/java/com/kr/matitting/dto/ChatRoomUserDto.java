package com.kr.matitting.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@Setter
public class ChatRoomUserDto {
    private Long id;
    private String nickName;
    private int age;
    private String imgUrl;
    private String city;

    public ChatRoomUserDto(Long id, String nickName, int age, String imgUrl, String city) {
        this.id = id;
        this.nickName = nickName;
        this.age = age;
        this.imgUrl = imgUrl;
        this.city = city;
    }
}
