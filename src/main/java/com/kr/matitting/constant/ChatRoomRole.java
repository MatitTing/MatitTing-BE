package com.kr.matitting.constant;

public enum ChatRoomRole {
    MANAGER("방장"), MEMBER("맴버");

    private String value;


    ChatRoomRole(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
