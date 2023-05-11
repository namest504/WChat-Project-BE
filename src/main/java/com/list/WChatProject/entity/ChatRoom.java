package com.list.WChatProject.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "chatroom")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatRoom {

    @Id
    @Column(name = "chatroom_id")
    private String roomId;
    @Column
    private String roomName;
    @Column
    private String password;
    @Column
    private int countPeople;
    @Column
    private int maxPeople;
    @Column
    private boolean isSecret;
    @Column
    private LocalDateTime createAt;

    @JsonIgnore
    @Builder.Default
    @OneToMany(mappedBy = "chatroom", cascade = CascadeType.ALL)
    List<Session> sessions = new ArrayList<>();


//    public static ChatRoom create(String name) {
//        ChatRoom room = new ChatRoom();
//        room.roomId = UUID.randomUUID().toString();
//        room.roomName = name;
//        room.countPeople = 0;
//        return room;
//    }
}
