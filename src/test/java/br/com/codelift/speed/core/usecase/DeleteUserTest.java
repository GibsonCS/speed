package br.com.codelift.speed.core.usecase;

import br.com.codelift.speed.core.domain.entity.User;
import br.com.codelift.speed.core.domain.repository.UserRepository;
import br.com.codelift.speed.core.domain.vo.Email;
import br.com.codelift.speed.core.domain.vo.Name;
import br.com.codelift.speed.infrastructure.web.dto.UserRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class DeleteUserTest {

    String VALID_NAME = Name.create("Gibson").getValue();
    String VALID_LASTNAME = Name.create("Cruz").getValue();
    String VALID_EMAIL = Email.create("gibson.cruz@gmail.com").getValue();
    String VALID_PASSWORD = "123456789";
    Set<UUID> VALID_ROLE = new HashSet<>();

    UserRequest userRequest;

    User user;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private DeleteUser deleteUser;

    @BeforeEach
    void setup() {

        userRequest = new UserRequest(
                VALID_NAME,
                VALID_LASTNAME,
                VALID_EMAIL,
                VALID_PASSWORD
        );

        VALID_ROLE.add(UUID.randomUUID());

        user = User.create(
                UUID.randomUUID(),
                VALID_NAME,
                VALID_LASTNAME,
                VALID_EMAIL,
                VALID_PASSWORD,
                VALID_ROLE
        );
    }

    @Test
    void shouldDeleteAnUser() {

        Mockito
                .when(userRepository.findById(user.getId().getValue()))
                .thenReturn(Optional.of(user))
        ;

        deleteUser.execute(user.getId().getValue());

        Mockito.verify(userRepository).delete(user.getId().getValue());
    }

}