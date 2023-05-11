package com.list.WChatProject.controller;

import com.list.WChatProject.entity.ChatRoom;
import com.list.WChatProject.entity.Member;
import com.list.WChatProject.exception.CustomException;
import com.list.WChatProject.security.jwt.MemberPrincipal;
import com.list.WChatProject.service.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static com.list.WChatProject.dto.ChatRoomDto.*;
import static com.list.WChatProject.dto.MemberDto.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/chat")
@Slf4j
public class ChatRoomController {

    private final ModelMapper modelMapper;
    private final ChatService chatService;
    private final Logger LOGGER = LoggerFactory.getLogger(ChatRoomController.class);

    // 모든 채팅방 목록 반환 pagination 추가되면 안 쓸 API
    @GetMapping("/rooms")
    public ChatRoomResponseDtoList room(@AuthenticationPrincipal MemberPrincipal memberPrincipal) {

        if (memberPrincipal.getMember().getNickName() == null) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "닉네임이 설정되지 않았습니다.");
        }
        List<ChatRoom> rooms = chatService.findAllRoom();

        List<ChatRoomResponseDto> resultList = rooms
                .stream()
                .map(list -> modelMapper.map(list, ChatRoomResponseDto.class))
                .collect(Collectors.toList());

        return new ChatRoomResponseDtoList(true ,resultList);
    }

    @GetMapping("/rooms/p/{page}")
    public ChatRoomPageResponseDto pageRoom(@PathVariable int page) {
        return chatService.pageChatRoom(page);
    }

    // 채팅방 생성
    @PostMapping("/create")
    public ChatRoomResponseDto createRoom(@AuthenticationPrincipal MemberPrincipal memberPrincipal,@RequestBody @Valid ChatRoomCreateRequestDto chatRoomCreateRequestDto) {
        if (chatRoomCreateRequestDto.isSecret() && chatRoomCreateRequestDto.getRoomPassword() == null) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "비밀방은 비밀번호가 필수입니다.");
        }
        if (chatRoomCreateRequestDto.getRoomName().length() > 20 || chatRoomCreateRequestDto.getRoomName() == null) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "방 제목 형식이 올바르지 않습니다.");
        }
        if (chatRoomCreateRequestDto.getMaxPeople() > 10 || chatRoomCreateRequestDto.getMaxPeople() <= 1) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "최대 인원수가 허용 범위가 아닙니다.");
        }
        ChatRoom room = chatService.createRoom(chatRoomCreateRequestDto);
        LOGGER.info("[{}] 님이 [{}] 방을 최대인원 [{}]으로 생성하였습니다.", memberPrincipal.getMember().getName(), chatRoomCreateRequestDto.getRoomName(), chatRoomCreateRequestDto.getMaxPeople());
        return new ChatRoomResponseDto(room.getRoomId(), room.getRoomName(), room.getCountPeople(), room.getMaxPeople(), room.isSecret());
    }

    //특정 채팅방 조회
    @GetMapping("/room")
    public ChatRoomResponseDtoList findRoomName(@RequestParam @Valid String roomName) {
        List<ChatRoomResponseDto> chatRoomResponseDtoList = chatService.findRoomByRoomName(roomName);
        if (chatRoomResponseDtoList.isEmpty() || chatRoomResponseDtoList == null) {
            return new ChatRoomResponseDtoList(false, chatRoomResponseDtoList);
        }
        return new ChatRoomResponseDtoList(true, chatRoomResponseDtoList);
    }

    @PostMapping("/room/enter")
    public ChatRoomBooleanResponseDto roomEnter(@RequestBody ChatRoomRequestDto chatRoomRequestDto) {
        boolean checkRoomEnter = chatService.checkRoomEnter(chatRoomRequestDto);
        return new ChatRoomBooleanResponseDto(checkRoomEnter);
    }

    // 방안엔 멤버 닉네임 불러오는 API
    @GetMapping("/room/users/{roomId}")
    public NickNameResponseDtos findMembersInRoom(@PathVariable String roomId) {
        log.info("findMembersInRoom = {}",roomId);

        List<Member> membersInRoom = chatService.findMembersInRoom(roomId);

        List<NickNameResponseDto> resultList = membersInRoom
                .stream()
                .map(list -> modelMapper.map(list, NickNameResponseDto.class))
                .collect(Collectors.toList());

        return new NickNameResponseDtos(true, resultList);
    }
}