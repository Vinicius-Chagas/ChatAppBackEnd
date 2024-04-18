package com.example.WhatsApp_Clone_API.domain.messages;

import com.example.WhatsApp_Clone_API.domain.chat.ChatService;
import com.example.WhatsApp_Clone_API.domain.chat.Chats;
import com.example.WhatsApp_Clone_API.domain.user.User;
import com.example.WhatsApp_Clone_API.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;
    private final ChatService chatService;
    private final UserRepository userRepository;

    public Message save(Message message) {
        var chat = chatService.getChat(message.getSender_id(), message.getReceiver_id(),message.getChat_id(), true);
        message.setChat_id(chat.orElse(null)); // verifica se há um chat, se nao tiver, retorna null

        return messageRepository.save(message);

    }

    public ArrayList<Message> findChatMessages(Long chatId){
        if(chatId != null){
            return messageRepository.findAllByChatID(chatId);
        }
        else {
            return new ArrayList<>();
        }
    }

    public Message findLastChatMessage(Long chatId){
        if(chatId != null){
            return messageRepository.findLastByChatID(chatId);
        }
        else {
            return null;
        }
    }
}
