package br.com.codelift.speed.infrastructure.persistence.repository.mapper;

import br.com.codelift.speed.core.domain.entity.User;
import br.com.codelift.speed.infrastructure.persistence.entity.RoleEntity;
import br.com.codelift.speed.infrastructure.persistence.entity.UserEntity;
import br.com.codelift.speed.infrastructure.persistence.repository.role.JpaRoleRepository;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Component
public class UserMapper {

    private final JpaRoleRepository jpaRoleRepository;

    public UserMapper(JpaRoleRepository jpaRoleRepository) {
        this.jpaRoleRepository = jpaRoleRepository;
    }

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

    public UserEntity toEntity(User user) {

        Set<RoleEntity> roles = new HashSet<>(jpaRoleRepository.findAllById(user.getRoleIds()));

        return new UserEntity(
                user.getId().getValue(),
                user.getName().getValue(),
                user.getLastname().getValue(),
                user.getEmail().getValue(),
                user.getEncryptedPassword(),
                roles
        );
    }
}
