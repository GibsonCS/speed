package br.com.codelift.speed.infrastructure.persistence.repository.user;

import br.com.codelift.speed.core.domain.entity.User;
import br.com.codelift.speed.core.domain.repository.UserRepository;
import br.com.codelift.speed.infrastructure.persistence.entity.RoleEntity;
import br.com.codelift.speed.infrastructure.persistence.entity.UserEntity;
import br.com.codelift.speed.infrastructure.persistence.repository.role.JpaRoleRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class UserRepositoryImp implements UserRepository {

    private final JpaUserRepository jpaUserRepository;
    private final JpaRoleRepository jpaRoleRepository;

    public UserRepositoryImp(JpaUserRepository jpaUserRepository, JpaRoleRepository jpaRoleRepository) {
        this.jpaUserRepository = jpaUserRepository;
        this.jpaRoleRepository = jpaRoleRepository;
    }

    @Override
    public Optional<User> findById(UUID id) {

        return jpaUserRepository.findById(id).map(userEntity -> {

            Set<UUID> roleIds = userEntity.getRoles()
                    .stream()
                    .map(RoleEntity::getId)
                    .collect(Collectors.toSet());

            return User.create(
                    userEntity.getId(),
                    userEntity.getName(),
                    userEntity.getLastname(),
                    userEntity.getEmail(),
                    userEntity.getPassword(),
                    roleIds
            );
        });
    }

    @Override
    public void save(User user) {
        UserEntity userEntity = new UserEntity();

        Set<UUID> roleIds = user.getRoleIds();

        List<RoleEntity> roles = jpaRoleRepository.findAllById(roleIds);

        userEntity.setId(user.getId().getValue());
        userEntity.setName(user.getName().getValue());
        userEntity.setLastname(user.getLastname().getValue());
        userEntity.setEmail(user.getEmail().getValue());
        userEntity.setPassword(user.getEncryptedPassword());
        roles.forEach(r -> userEntity.getRoles().add(r));

        jpaUserRepository.save(userEntity);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return Optional.empty();
    }
}
