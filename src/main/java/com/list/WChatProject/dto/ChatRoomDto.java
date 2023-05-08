package com.list.WChatProject.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

public class ChatRoomDto {

    @Getter
    @Setter
    @AllArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public static class ChatRoomResponseDto {
        private String roomId;
        private String roomName;
        private int countPeople;
        private int maxPeople;
        private boolean isSecret;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public static class ChatRoomResponseDtos {
        private boolean success;
        private List<ChatRoomResponseDto> chatRoomResponseDtos;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ChatRoomCreateRequestDto {
        private String name;
        private String password;
        private int maxPeople;
        private boolean isSecret;
    }


}
