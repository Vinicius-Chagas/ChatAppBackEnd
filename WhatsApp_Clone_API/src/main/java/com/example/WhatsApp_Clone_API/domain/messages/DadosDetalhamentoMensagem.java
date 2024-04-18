package com.example.WhatsApp_Clone_API.domain.messages;

import com.example.WhatsApp_Clone_API.domain.chat.Chats;
import com.example.WhatsApp_Clone_API.domain.user.User;


import java.time.LocalDateTime;

public record DadosDetalhamentoMensagem(Long ID, Chats chat_id, String message, User sender_id, User receiver_id, LocalDateTime created) {
    public DadosDetalhamentoMensagem(Message mensagem){
        this(mensagem.getID(), mensagem.getChat_id(), mensagem.getMessage(), mensagem.getSender_id(), mensagem.getReceiver_id(), mensagem.getCreated());
    }
}
