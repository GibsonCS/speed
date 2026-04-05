package br.com.codelift.speed.core.domain.repository;

import br.com.codelift.speed.core.domain.entity.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository {

    Optional<User> findById(UUID id);

    void save(User user);

    Optional<User> findByEmail(String email);

    void delete(UUID id);
}
