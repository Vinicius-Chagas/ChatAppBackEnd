package com.example.WhatsApp_Clone_API.controller;

import com.example.WhatsApp_Clone_API.domain.chat.*;
import com.example.WhatsApp_Clone_API.domain.messages.*;
import com.example.WhatsApp_Clone_API.domain.user.User;
import com.example.WhatsApp_Clone_API.domain.user.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.util.UriBuilder;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ChatController {
    private final SimpMessagingTemplate messagingTemplate;
    private final MessageService messageService;
    private final ChatRepository chatRepository;
    private final UserRepository userRepository;
    private final ChatService chatService;

    @MessageMapping("/chat")
    public void processMessage(
            @Payload DadosMessage dadosMessage
    ) {
        User receiver = userRepository.findUserByPhone(dadosMessage.receiverID());
        User sender = userRepository.findUserByPhone(dadosMessage.senderID());
        Long chatid = Long.valueOf(dadosMessage.chatID());
        Chats chat = chatRepository.findById(chatid).orElse(null);

        Message message = new Message(null,chat, dadosMessage.content(), sender, receiver, LocalDateTime.now());

        Message savedMsg = messageService.save(message);

        messagingTemplate.convertAndSendToUser(
                savedMsg.getReceiver_id().getPhone_number(), "/queue/messages",
                new MessageNotification(savedMsg.getID(),savedMsg.getSender_id(),savedMsg.getReceiver_id(),savedMsg.getMessage(), savedMsg.getCreated().toString())

        );
        System.out.println("Processing complete");
    }

    @GetMapping("/messages/{chatId}")
    public ResponseEntity<ArrayList<Message>> findMessages(
            @PathVariable("chatId") Long chatId
    ){
        var messages = messageService.findChatMessages(chatId);

        return ResponseEntity.ok(messages);
    }

    @GetMapping("/chats/{id}")
    public ResponseEntity<List<DadosChat>> findChats(@PathVariable("id") Long id){
        User user = userRepository.findById(id).orElse(null);
        List<DadosChat> chat = chatRepository.findAllBySender_idOrReceiver_id(user,user)
                .stream()
                .peek(item ->
                        item.messages()
                                .add(messageService.findLastChatMessage(item.id())
                                )
                ).toList();

        return ResponseEntity.ok(chat);
    }

    @PostMapping("/createChat")
    public ResponseEntity<DadosChat> createChat(@RequestBody @Valid DadosCreateChat dados) throws Exception {
        User sender = userRepository.findUserByPhone(dados.senderNumber());
        User receiver = userRepository.findUserByPhone(dados.receiverNumber());
        if(sender != null && receiver != null){
            Chats savedChat = chatService.createChat(sender, receiver);

            return ResponseEntity.created(null).body(DadosChat.builder()
                    .id(savedChat.getID())
                    .receiver_id(savedChat.getReceiver_id())
                    .sender_id(savedChat.getSender_id())
                    .created(savedChat.getCreated())
                    .messages(new ArrayList<Message>()).build());
        }
        else{
            throw new Exception("Receiver not found");
        }
    }
}
