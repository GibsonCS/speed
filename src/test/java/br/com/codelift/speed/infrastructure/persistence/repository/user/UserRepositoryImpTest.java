package br.com.codelift.speed.infrastructure.persistence.repository.user;

import br.com.codelift.speed.core.domain.entity.User;
import br.com.codelift.speed.core.domain.repository.RoleRepository;
import br.com.codelift.speed.core.domain.repository.UserRepository;
import br.com.codelift.speed.core.exception.BusinessException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class UserRepositoryImpTest {

    UUID VALID_ID = UUID.randomUUID();
    Set<UUID> VALID_ROLE_IDS = new HashSet<>();

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    User user;

    @BeforeEach
    void setup() {
        user = User.create(
                VALID_ID,
                "Gibsonz",
                "silva",
                "gibson@gibson.com.br",
                "1234568",
                VALID_ROLE_IDS
        );
    }

    @Test
    void shouldSaveAnUser() {

        var roleEntity = roleRepository.findByName("USER");

        roleEntity.ifPresent(r -> VALID_ROLE_IDS.add(r.getId()));

        userRepository.save(user);

        Optional<User> userSaved = userRepository.findById(VALID_ID);

        userSaved.ifPresent(u -> {
            assertEquals(VALID_ID, u.getId().getValue());
        });
    }

    @Test
    void shouldDeleteAnUser() {

        userRepository.save(user);

        userRepository.delete(VALID_ID);

        assertThrows(BusinessException.class, () -> userRepository.findById(VALID_ID));
    }

    @Test
    void shouldGetUser() {

        userRepository.save(user);

        Optional<User> userFounded = userRepository.findById(user.getId().getValue());

        userFounded.ifPresent(value -> assertEquals(user.getId().getValue(), value.getId().getValue()));
    }
}