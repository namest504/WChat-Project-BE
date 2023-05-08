package com.list.WChatProject.controller;

import com.list.WChatProject.chat.ChatRoom;
import com.list.WChatProject.exception.CustomException;
import com.list.WChatProject.security.jwt.MemberPrincipal;
import com.list.WChatProject.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static com.list.WChatProject.dto.ChatRoomDto.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/chat")
public class ChatRoomController {

    private final ModelMapper modelMapper;
    private final ChatService chatService;
    private final Logger LOGGER = LoggerFactory.getLogger(ChatRoomController.class);

    // 모든 채팅방 목록 반환
    @GetMapping("/rooms")
    public ChatRoomResponseDtos room(@AuthenticationPrincipal MemberPrincipal memberPrincipal) {

        if (memberPrincipal.getMember().getNickName() == null) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "닉네임이 설정되지 않았습니다.");
        }
        List<ChatRoom> rooms = chatService.findAllRoom();

        List<ChatRoomResponseDto> resultList = rooms
                .stream()
                .map(list -> modelMapper.map(list, ChatRoomResponseDto.class))
                .collect(Collectors.toList());

        return new ChatRoomResponseDtos(true ,resultList);
    }

    // 채팅방 생성
    @PostMapping("/create")
    public ChatRoomResponseDto createRoom(@AuthenticationPrincipal MemberPrincipal memberPrincipal,@RequestBody ChatRoomCreateRequestDto chatRoomCreateRequestDto) {
        LOGGER.info("[{}] 님이 [{}] 방을 생성하였습니다.", memberPrincipal.getMember().getName(), chatRoomCreateRequestDto.getMaxPeople());
        ChatRoom room = chatService.createRoom(chatRoomCreateRequestDto);
        return new ChatRoomResponseDto(room.getRoomId(), room.getRoomName(), room.getCountPeople(), room.getMaxPeople(), room.isSecret());
    }

    //특정 채팅방 조회
    @GetMapping("/room/{roomName}")
    public ChatRoomResponseDtos findRoomName(@PathVariable String roomName) {
        List<ChatRoom> chatRooms = chatService.findRoomByRoomName(roomName);
        List<ChatRoomResponseDto> resultList = chatRooms
                .stream()
                .map(list -> modelMapper.map(list, ChatRoomResponseDto.class))
                .collect(Collectors.toList());
        return new ChatRoomResponseDtos(true,resultList);
    }
}