package com.kr.matitting.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatRequestDto {
    private String content;

    private Long senderId;
}
