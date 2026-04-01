package br.com.codelift.speed.usecase;

import br.com.codelift.speed.domain.vo.Email;
import br.com.codelift.speed.domain.vo.Name;
import br.com.codelift.speed.usecase.infrastructure.web.dto.UserRequest;
import br.com.codelift.speed.usecase.infrastructure.web.dto.UserResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class CreateUserTest {

    String VALID_NAME = Name.create("Gibson").getValue();
    String VALID_LASTNAME = Name.create("Cruz").getValue();
    String VALID_EMAIL = Email.create("gibson.cruz@gmail.com").getValue();
    String VALID_PASSWORD = "123456789";

    CreateUser createUser;
    UserRequest userRequest;

    @BeforeEach
    void setup() {
        createUser = new CreateUser();
        userRequest = new UserRequest(
                VALID_NAME,
                VALID_LASTNAME,
                VALID_EMAIL,
                VALID_PASSWORD
        );
    }

    @Test
    void shouldCreateNewUser() {

        UserResponse userResponse = createUser.execute(userRequest);

        assertNotNull(userResponse.id());
    }
}