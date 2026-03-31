package br.com.codelift.speed.domain.entity;

import br.com.codelift.speed.domain.vo.Email;
import br.com.codelift.speed.domain.vo.Name;
import br.com.codelift.speed.exception.BusinessException;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UserTest {

    private final UUID VALID_ID = UUID.randomUUID();
    private final Name VALID_NAME = Name.create("Gibson");
    private final Name VALID_LASTNAME = Name.create("Silva");
    private final String VALID_PASSWORD = "125Adm$";
    private final String VALID_EMAIL = "gibson@gmail.com";
    private final Set<UUID> VALID_ROLEIDS = new HashSet<>();

    @Test
    void shouldThrowExceptionWhenNameIsEmpty() {
        assertThrows(BusinessException.class, () -> User.create(
                VALID_ID,
                Name.create(""),
                VALID_LASTNAME,
                Email.create(VALID_EMAIL),
                VALID_PASSWORD,
                VALID_ROLEIDS
        ));
    }

    @Test
    void shouldThrowExceptionWhenNameIsLessThanThreeCharacters() {
        assertThrows(BusinessException.class, () -> User.create(
                VALID_ID,
                Name.create("an"),
                VALID_LASTNAME,
                Email.create(VALID_EMAIL),
                VALID_PASSWORD,
                VALID_ROLEIDS
        ));
    }

    @Test
    void shouldThrowExceptionWhenNameIsGreaterThanTwentyCharacters() {
        assertThrows(BusinessException.class, () -> User.create(
                VALID_ID,
                Name.create("asdfdsxfdadfdassdfdgfa"),
                VALID_LASTNAME,
                Email.create(VALID_EMAIL),
                VALID_PASSWORD,
                VALID_ROLEIDS
        ));
    }

    @Test
    void shouldThrowExceptionWhenNameContainsSpecialCharacters() {
        assertThrows(BusinessException.class, () -> User.create(
                VALID_ID,
                Name.create("asdasd!@"),
                VALID_LASTNAME,
                Email.create(VALID_EMAIL),
                VALID_PASSWORD,
                VALID_ROLEIDS
        ));
    }

    @Test
    void shouldThrowExceptionWhenLastNameIsEmpty() {
        assertThrows(BusinessException.class, () -> User.create(
                VALID_ID,
                VALID_NAME,
                Name.create(""),
                Email.create(VALID_EMAIL),
                VALID_PASSWORD,
                VALID_ROLEIDS
        ));
    }

    @Test
    void shouldThrowExceptionWhenLastNameIsLessThanThreeCharacters() {
        assertThrows(BusinessException.class, () -> User.create(
                VALID_ID,
                VALID_NAME,
                Name.create("as"),
                Email.create(VALID_EMAIL),
                VALID_PASSWORD,
                VALID_ROLEIDS
        ));
    }

    @Test
    void shouldThrowExceptionWhenLastNameIsGreaterThanTwentyCharacters() {
        assertThrows(BusinessException.class, () -> User.create(
                VALID_ID,
                VALID_NAME,
                Name.create("djkfjaskdfddfldalasdknkdfmsd"),
                Email.create(VALID_EMAIL),
                VALID_PASSWORD,
                VALID_ROLEIDS
        ));
    }

    @Test
    void shouldThrowExceptionWhenLastNameContainsSpecialCharacters() {
        assertThrows(BusinessException.class, () -> User.create(
                VALID_ID,
                VALID_NAME,
                Name.create("asdasd!@"),
                Email.create(VALID_EMAIL),
                VALID_PASSWORD,
                VALID_ROLEIDS
        ));
    }

    @Test
    void shouldThrowExceptionWhenEmailIsEmpty() {
        assertThrows(BusinessException.class, () -> User.create(
                VALID_ID, VALID_NAME,
                VALID_LASTNAME,
                Email.create(""),
                VALID_PASSWORD,
                VALID_ROLEIDS
        ));
    }

    @Test
    void shouldThrowExceptionWhenEmailIsInvalid() {
        assertThrows(BusinessException.class, () -> User.create(
                VALID_ID, VALID_NAME,
                VALID_LASTNAME,
                Email.create("asdfcom"),
                VALID_PASSWORD,
                VALID_ROLEIDS
        ));
    }

    @Test
    void shouldThrowExceptionWhenPasswordIsEmpty() {
        assertThrows(BusinessException.class, () -> User.create(
                VALID_ID, VALID_NAME,
                VALID_LASTNAME,
                Email.create(VALID_EMAIL),
                "",
                VALID_ROLEIDS
        ));
    }

    @Test
    void shouldThrowExceptionWhenPasswordIsNotBetweenSixAndTwelveCharacters() {
        assertThrows(BusinessException.class, () -> User.create(
                VALID_ID, VALID_NAME,
                VALID_LASTNAME,
                Email.create(VALID_EMAIL),
                "12345",
                VALID_ROLEIDS
        ));
    }

    @Test
    void shouldCreateValidUser() {

        User user = User.create(
                VALID_ID,
                VALID_NAME,
                VALID_LASTNAME,
                Email.create(VALID_EMAIL),
                VALID_PASSWORD,
                VALID_ROLEIDS
        );

        assertEquals(VALID_ID, user.getId().getValue());
        assertEquals(VALID_NAME, user.getName());
        assertEquals(VALID_LASTNAME, user.getLastname());
        assertEquals(Email.create(VALID_EMAIL), user.getEmail());
    }
}