package com.kr.matitting.constant;

public enum ChatRoomType {
    ONE_ON_ONE("1대1 채팅"), GROUP("다대다 채팅");

    private String value;


    ChatRoomType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
