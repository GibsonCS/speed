package br.com.codelift.speed.domain.repository;

import br.com.codelift.speed.domain.entity.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository {

    Optional<User> findById(UUID id);

    void save(User user);

    Optional<User> findByEmail(String email);
}
