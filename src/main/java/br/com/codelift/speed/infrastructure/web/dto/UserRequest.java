package br.com.codelift.speed.infrastructure.web.dto;

public record UserRequest(

        String name,
        String lastname,
        String email,
        String password
) {
}
