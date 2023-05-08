package com.list.WChatProject.chat;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

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


//    public static ChatRoom create(String name) {
//        ChatRoom room = new ChatRoom();
//        room.roomId = UUID.randomUUID().toString();
//        room.roomName = name;
//        room.countPeople = 0;
//        return room;
//    }
}
