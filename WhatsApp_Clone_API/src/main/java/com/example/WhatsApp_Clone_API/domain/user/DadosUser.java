package com.example.WhatsApp_Clone_API.domain.user;

import jakarta.validation.constraints.NotEmpty;

public record DadosUser(@NotEmpty String phone_number, @NotEmpty String password) {
}
