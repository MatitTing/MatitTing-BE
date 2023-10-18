package com.kr.matitting.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserDto {
    private String nickname;
    private int age;
    private String imgUrl;
    private String city;

    public UserDto(String nickname, int age, String imgUrl, String city) {
        this.nickname = nickname;
        this.age = age;
        this.imgUrl = imgUrl;
        this.city = city;
    }
}
