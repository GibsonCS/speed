package br.com.codelift.speed.infrastructure.persistence.repository.user;

import br.com.codelift.speed.core.domain.entity.User;
import br.com.codelift.speed.core.domain.repository.UserRepository;
import br.com.codelift.speed.exception.BusinessException;
import br.com.codelift.speed.infrastructure.persistence.entity.RoleEntity;
import br.com.codelift.speed.infrastructure.persistence.entity.UserEntity;
import br.com.codelift.speed.infrastructure.persistence.repository.mapper.UserMapper;
import br.com.codelift.speed.infrastructure.persistence.repository.role.JpaRoleRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
public class UserRepositoryImp implements UserRepository {

    private final JpaUserRepository jpaUserRepository;
    private final JpaRoleRepository jpaRoleRepository;
    private final UserMapper userMapper;

    public UserRepositoryImp(JpaUserRepository jpaUserRepository, JpaRoleRepository jpaRoleRepository, UserMapper userMapper) {
        this.jpaUserRepository = jpaUserRepository;
        this.jpaRoleRepository = jpaRoleRepository;
        this.userMapper = userMapper;
    }

    @Override
    public Optional<User> findById(UUID id) {
        
        Optional<UserEntity> userEntity = jpaUserRepository.findById(id);

        if (userEntity.isEmpty()) {
            throw new BusinessException("User not exists!");
        }

        return Optional.of(userMapper.toDomain(userEntity.get()));
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
