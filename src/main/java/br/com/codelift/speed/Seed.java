package br.com.codelift.speed;


import br.com.codelift.speed.infrastructure.persistence.entity.RoleEntity;
import br.com.codelift.speed.infrastructure.persistence.repository.JpaRoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.UUID;

@SpringBootApplication
public class Seed implements CommandLineRunner {

    JpaRoleRepository jpaRoleRepository;

    public Seed(JpaRoleRepository jpaRoleRepository) {
        this.jpaRoleRepository = jpaRoleRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        RoleEntity userRole = new RoleEntity(
                "USER",
                UUID.fromString("318df173-32e1-4865-b380-e3f2343955b4")
        );

        RoleEntity adminRole = new RoleEntity(
                "ADMIN",
                UUID.fromString("2dbe3e72-69ff-418b-95a5-93f1b0710221")
        );

        jpaRoleRepository.save(userRole);
        jpaRoleRepository.save(adminRole);
    }
}
