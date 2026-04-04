package br.com.codelift.speed.infrastructure.persistence.repository.user;

import br.com.codelift.speed.core.domain.entity.User;
import br.com.codelift.speed.core.domain.repository.UserRepository;
import br.com.codelift.speed.exception.BusinessException;
import br.com.codelift.speed.infrastructure.persistence.entity.UserEntity;
import br.com.codelift.speed.infrastructure.persistence.repository.mapper.UserMapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class UserRepositoryImp implements UserRepository {

    private final JpaUserRepository jpaUserRepository;
    private final UserMapper userMapper;

    public UserRepositoryImp(JpaUserRepository jpaUserRepository, UserMapper userMapper) {
        this.jpaUserRepository = jpaUserRepository;
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
        jpaUserRepository.save(userMapper.toEntity(user));
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return Optional.empty();
    }
}
