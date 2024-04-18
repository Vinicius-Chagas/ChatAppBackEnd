package com.example.WhatsApp_Clone_API.controller;

import com.example.WhatsApp_Clone_API.domain.user.DadosDetalhamentoUser;
import com.example.WhatsApp_Clone_API.domain.user.DadosUser;
import com.example.WhatsApp_Clone_API.domain.user.User;
import com.example.WhatsApp_Clone_API.domain.user.UserRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Controller
@RequestMapping("/signin")
public class UserController {

    @Autowired
    private UserRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity createUser(@RequestBody @Valid DadosUser dados, UriComponentsBuilder uriBuilder){
        User user = new User(dados);
        repository.save(user);
        var uri = uriBuilder.path("/user/{id}").buildAndExpand(user.getID()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoUser(user));
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> findUsers(){
        return ResponseEntity.ok(repository.findAll());
    }

}
