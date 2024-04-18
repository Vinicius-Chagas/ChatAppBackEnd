package com.example.WhatsApp_Clone_API.domain.messages;

import com.example.WhatsApp_Clone_API.domain.user.User;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MessageNotification {
    //field names modified to be alike with the front end interface
    private Long id;
    private User sender_id;
    private User receiver_id;
    private String message;
    private String created;
}
