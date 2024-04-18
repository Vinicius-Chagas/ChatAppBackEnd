package com.example.WhatsApp_Clone_API.domain.chat;

import com.example.WhatsApp_Clone_API.domain.messages.Message;
import com.example.WhatsApp_Clone_API.domain.user.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;



@Builder
public record DadosChat(Long id, User receiver_id, User sender_id, @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime created, List<Message> messages) {

    public DadosChat(Chats chat){
        this(chat.getID(), chat.getReceiver_id(), chat.getSender_id(), chat.getCreated(), new ArrayList<Message>());
    }

}
