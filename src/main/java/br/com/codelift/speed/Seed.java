package br.com.codelift.speed;


import br.com.codelift.speed.core.domain.repository.RoleRepository;
import br.com.codelift.speed.core.domain.repository.UserRepository;
import br.com.codelift.speed.core.domain.vo.Email;
import br.com.codelift.speed.core.domain.vo.Name;
import br.com.codelift.speed.core.usecase.CreateUser;
import br.com.codelift.speed.infrastructure.persistence.entity.RoleEntity;
import br.com.codelift.speed.infrastructure.persistence.repository.JpaRoleRepository;
import br.com.codelift.speed.infrastructure.persistence.repository.UserRepositoryImp;
import br.com.codelift.speed.infrastructure.web.dto.UserRequest;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.UUID;

@SpringBootApplication
public class Seed implements CommandLineRunner {

    JpaRoleRepository jpaRoleRepository;

    UserRepository userRepositoryImp;

    RoleRepository roleRepository;


    public Seed(JpaRoleRepository jpaRoleRepository, UserRepositoryImp userRepositoryImp, RoleRepository roleRepository) {
        this.jpaRoleRepository = jpaRoleRepository;
        this.userRepositoryImp = userRepositoryImp;
        this.roleRepository = roleRepository;
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

        CreateUser createUser = new CreateUser(userRepositoryImp, roleRepository);

        String VALID_NAME = Name.create("Gibson").getValue();
        String VALID_LASTNAME = Name.create("Cruz").getValue();
        String VALID_EMAIL = Email.create("gibson.cruz@gmail.com").getValue();
        String VALID_PASSWORD = "123456789";

        UserRequest userRequest = new UserRequest(
                VALID_NAME,
                VALID_LASTNAME,
                VALID_EMAIL,
                VALID_PASSWORD
        );

        createUser.execute(userRequest);
    }
}
