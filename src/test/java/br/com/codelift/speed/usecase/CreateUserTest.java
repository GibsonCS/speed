package br.com.codelift.speed.usecase;

import br.com.codelift.speed.core.domain.entity.Role;
import br.com.codelift.speed.core.domain.entity.User;
import br.com.codelift.speed.core.domain.repository.RoleRepository;
import br.com.codelift.speed.core.domain.repository.UserRepository;
import br.com.codelift.speed.core.domain.vo.Email;
import br.com.codelift.speed.core.domain.vo.Name;
import br.com.codelift.speed.core.usecase.CreateUser;
import br.com.codelift.speed.exception.BusinessException;
import br.com.codelift.speed.infrastructure.web.dto.UserRequest;
import br.com.codelift.speed.infrastructure.web.dto.UserResponse;
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

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class CreateUserTest {

    String VALID_NAME = Name.create("Gibson").getValue();
    String VALID_LASTNAME = Name.create("Cruz").getValue();
    String VALID_EMAIL = Email.create("gibson.cruz@gmail.com").getValue();
    String VALID_PASSWORD = "123456789";
    Set<UUID> VALID_ROLE = new HashSet<>();

    @Mock
    UserRepository userRepository;

    @Mock
    RoleRepository roleRepository;

    @InjectMocks
    CreateUser createUser;

    UserRequest userRequest;

    User user;

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
    void shouldCreateNewUser() {

        Role role = new Role(UUID.fromString("318df173-32e1-4865-b380-e3f2343955b4"), "USER");

        Mockito.when(userRepository.findByEmail(VALID_EMAIL)).thenReturn(Optional.empty());

        Mockito.when(roleRepository.findByName("USER")).thenReturn(Optional.of(role));

        UserResponse userResponse = createUser.execute(userRequest);

        assertNotNull(userResponse.id());
    }

    @Test
    void shouldNotCreateAnUserWithDuplicatedEmail() {

        Mockito.when(userRepository.findByEmail(VALID_EMAIL)).thenReturn(Optional.of(user));

        assertThrows(BusinessException.class, () -> createUser.execute(userRequest));
    }
}