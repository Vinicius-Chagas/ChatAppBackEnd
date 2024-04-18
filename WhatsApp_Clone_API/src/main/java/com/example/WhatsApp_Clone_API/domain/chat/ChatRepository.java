package com.example.WhatsApp_Clone_API.domain.chat;

import com.example.WhatsApp_Clone_API.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ChatRepository extends JpaRepository<Chats, Long> {

    @Query("SELECT c FROM chats c WHERE c.sender_id = :senderId OR c.receiver_id = :receiverId")
    Optional<Chats> findBySender_idOrReceiver_id(User senderId, User receiverId);

    @Query("SELECT c FROM chats c WHERE c.sender_id = :senderId OR c.receiver_id = :receiverId")
    List<DadosChat> findAllBySender_idOrReceiver_id(User senderId, User receiverId);

    @Query("SELECT c.id, c.created, c.sender_id, c.receiver_id FROM chats c WHERE c.ID = :ID")
    Chats findByID(Long ID);
}
