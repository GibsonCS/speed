package br.com.codelift.speed.infrastructure.persistence.repository.mapper;

import br.com.codelift.speed.core.domain.entity.User;
import br.com.codelift.speed.infrastructure.persistence.entity.UserEntity;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Component
public class UserMapper {

    public User toDomain(UserEntity userEntity) {

        Set<UUID> roleIds = new HashSet<>();

        userEntity.getRoles().forEach(r -> roleIds.add(r.getId()));

        return User.create(
                userEntity.getId(),
                userEntity.getName(),
                userEntity.getLastname(),
                userEntity.getEmail(),
                userEntity.getPassword(),
                roleIds
        );
    }
}
