package com.list.WChatProject.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.list.WChatProject.entity.AccountType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

public class MemberDto {

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class RegisterRequestDto {
        private String Id;
        private String userId;
        private String NickName;
        private String password;
        private AccountType accountType;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public static class NickNameResponseDto {
        private String nickName;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public static class ChangeNickNameResponseDto {
        private boolean success;
        private LocalDateTime changeAt;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public static class NickNameResponseDtos {
        private boolean success;
        private List<NickNameResponseDto> nickNameResponseDtoList;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public static class DeleteMemberResponseDto {
        private boolean success;
    }
}
