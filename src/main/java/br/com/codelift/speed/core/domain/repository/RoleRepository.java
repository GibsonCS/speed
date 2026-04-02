package br.com.codelift.speed.core.domain.repository;

import br.com.codelift.speed.core.domain.entity.Role;

import java.util.Optional;

public interface RoleRepository {
    Optional<Role> findByName(String name);
}
