package br.com.codelift.speed.core.usecase;

import br.com.codelift.speed.core.domain.entity.User;
import br.com.codelift.speed.core.domain.repository.UserRepository;
import br.com.codelift.speed.core.domain.vo.Email;
import br.com.codelift.speed.core.domain.vo.Name;
import org.junit.jupiter.api.Assertions;
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
class UpdateUserTest {

    String VALID_NAME = Name.create("Gibson").getValue();
    String VALID_LASTNAME = Name.create("Cruz").getValue();
    String VALID_EMAIL = Email.create("gibson.cruz@gmail.com").getValue();
    String VALID_PASSWORD = "123456789";
    Set<UUID> VALID_ROLE = new HashSet<>();

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UpdateUser updateUser;

    User user;


    @BeforeEach
    void setup() {

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
    void shouldUpdateAnUser() {

        Mockito.when(userRepository.findById(user.getId().getValue())).thenReturn(Optional.of(user));
        
        var updatedUser = updateUser.execute(user);

        Assertions.assertEquals(updatedUser.getName().getValue(), user.getName().getValue());
        Assertions.assertEquals(updatedUser.getLastname().getValue(), user.getLastname().getValue());
        Assertions.assertEquals(updatedUser.getEmail().getValue(), user.getEmail().getValue());
    }
}