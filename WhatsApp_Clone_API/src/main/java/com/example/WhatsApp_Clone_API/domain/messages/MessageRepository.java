package com.example.WhatsApp_Clone_API.domain.messages;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;
import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    @Query("SELECT m FROM messages m WHERE m.chat_id.id = :id")
    ArrayList<Message> findAllByChatID(Long id);

    @Query("SELECT m FROM messages m WHERE m.chat_id.id = :id ORDER BY m.chat_id.id ASC LIMIT 1")
    Message findLastByChatID(Long id);
}
