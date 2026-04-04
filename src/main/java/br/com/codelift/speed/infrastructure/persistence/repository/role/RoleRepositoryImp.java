package br.com.codelift.speed.infrastructure.persistence.repository.role;

import br.com.codelift.speed.core.domain.entity.Role;
import br.com.codelift.speed.core.domain.repository.RoleRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class RoleRepositoryImp implements RoleRepository {

    private final JpaRoleRepository jpaRoleRepository;

    public RoleRepositoryImp(JpaRoleRepository jpaRoleRepository) {
        this.jpaRoleRepository = jpaRoleRepository;
    }

    @Override
    public Optional<Role> findByName(String name) {
        return jpaRoleRepository.findByName(name)
                .map(roleEntity -> new Role(roleEntity.getId(), roleEntity.getName()));
    }
}
