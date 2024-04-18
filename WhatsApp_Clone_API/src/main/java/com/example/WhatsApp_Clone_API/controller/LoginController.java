package com.example.WhatsApp_Clone_API.controller;


import com.example.WhatsApp_Clone_API.domain.user.DadosAutenticacao;
import com.example.WhatsApp_Clone_API.domain.user.User;
import com.example.WhatsApp_Clone_API.infra.security.TokenDadosJwt;
import com.example.WhatsApp_Clone_API.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity login(@RequestBody @Valid DadosAutenticacao dados){
        var authToken = new UsernamePasswordAuthenticationToken(dados.phone_number(), dados.password());
        var authentication = manager.authenticate(authToken);
        var tokenJwt = tokenService.gerarToken((User) authentication.getPrincipal());

        return ResponseEntity.ok(authentication.getPrincipal());

    }
}
