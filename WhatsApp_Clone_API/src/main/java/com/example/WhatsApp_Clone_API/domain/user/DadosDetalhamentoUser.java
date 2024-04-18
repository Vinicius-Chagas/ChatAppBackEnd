package com.example.WhatsApp_Clone_API.domain.user;

public record DadosDetalhamentoUser(String phone_number, String password) {
    public DadosDetalhamentoUser(User user){
        this(user.getPhone_number(),user.getPassword());
    }
}
