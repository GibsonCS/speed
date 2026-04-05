package br.com.codelift.speed.infrastructure.web.dto;

import br.com.codelift.speed.core.domain.entity.User;

import java.util.Set;
import java.util.UUID;

public record GetUserResponse(
        UUID id,
        String name,
        String lastname,
        String email,
        Set<UUID> roleIds
) {
    public static GetUserResponse mapperToGetUserResponse(User user) {
        return new GetUserResponse(
                user.getId().getValue(),
                user.getName().getValue(),
                user.getLastname().getValue(),
                user.getEmail().getValue(),
                user.getRoleIds()
        );
    }
}
