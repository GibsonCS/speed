package br.com.codelift.speed.infrastructure.web.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateUserRequest(
        @NotBlank(message = "should not be blank")
        String name,
        @NotBlank
        String lastname,
        @NotBlank
        String email,
        @NotBlank
        String password
) {
}
