package br.com.codelift.speed.infrastructure.persistence.repository.user;

import br.com.codelift.speed.infrastructure.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaUserRepository extends JpaRepository<UserEntity, UUID> {
}
