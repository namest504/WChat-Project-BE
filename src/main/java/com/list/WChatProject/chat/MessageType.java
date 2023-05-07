package com.list.WChatProject.chat;

public enum MessageType {
    ENTER("ENTER"), TALK("TALK"), EXIT("EXIT");

    private String value;

    MessageType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
