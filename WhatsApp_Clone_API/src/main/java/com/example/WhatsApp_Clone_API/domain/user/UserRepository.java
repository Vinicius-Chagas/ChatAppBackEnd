package com.example.WhatsApp_Clone_API.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM user u WHERE u.phone_number = :phoneNumber")
    UserDetails findByPhone(String phoneNumber);

    @Query("SELECT u FROM user u WHERE u.phone_number = :phoneNumber")
    User findUserByPhone(String phoneNumber);

}
