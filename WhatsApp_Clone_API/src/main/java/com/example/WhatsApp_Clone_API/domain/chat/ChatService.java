package com.example.WhatsApp_Clone_API.domain.chat;

import com.example.WhatsApp_Clone_API.domain.messages.Message;
import com.example.WhatsApp_Clone_API.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChatService {
    private final ChatRepository chatRepository;

    public Optional<Chats> getChat(
            User senderId,
            User receiverId,
            Chats chatId,
            boolean createNewChatIfNotExists
    ) {
        return chatRepository.findById(chatId.getID())
                .or(() -> {
                    if(createNewChatIfNotExists){
                        Chats chat = createChat(senderId, receiverId);
                        return Optional.of(chat);
                    }
                    return Optional.empty();
                });
    }

    public Chats createChat(User senderId, User receiverId){
        return chatRepository.save(Chats.builder()
                .receiver_id(receiverId)
                .sender_id(senderId)
                .created(LocalDateTime.now())
                .build());

    }
}
