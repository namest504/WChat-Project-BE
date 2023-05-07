package com.list.WChatProject.entity;

public enum AccountType {
    BASIC("BASIC"), KAKAO("KAKAO");

    private String value;

    AccountType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
