package br.com.codelift.speed.infrastructure.persistence.repository;

import br.com.codelift.speed.infrastructure.persistence.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaRoleRepository extends JpaRepository<RoleEntity, UUID> {
}
