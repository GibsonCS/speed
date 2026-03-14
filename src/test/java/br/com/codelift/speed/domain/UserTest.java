package br.com.codelift.speed.domain;

import br.com.codelift.speed.exception.BusinessException;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UserTest {

    private final UUID VALID_ID = UUID.randomUUID();
    private final String VALID_NAME = "Gibson";
    private final String VALID_LASTNAME = "Silva";
    private final String VALID_PASSWORD = "125Adm$";
    private final String VALID_EMAIL = "gibson@gmail.com";
    private final UUID VALID_ROLEID = UUID.randomUUID();

    @Test
    void shouldThrowExceptionWhenNameIsEmpty() {
        assertThrows(BusinessException.class, () -> User.create(
                VALID_ID,
                "",
                VALID_LASTNAME,
                VALID_EMAIL,
                VALID_PASSWORD,
                VALID_ROLEID
        ));
    }

    @Test
    void shouldThrowExceptionWhenNameIsLessThanThreeCharacters() {
        assertThrows(BusinessException.class, () -> User.create(
                VALID_ID,
                "an",
                VALID_LASTNAME,
                VALID_EMAIL,
                VALID_PASSWORD,
                VALID_ROLEID
        ));
    }

    @Test
    void shouldThrowExceptionWhenNameIsGreaterThanTwentyCharacters() {
        assertThrows(BusinessException.class, () -> User.create(
                VALID_ID,
                "asdfdsxfdadfdassdfdgfa",
                VALID_LASTNAME,
                VALID_EMAIL,
                VALID_PASSWORD,
                VALID_ROLEID
        ));
    }

    @Test
    void shouldThrowExceptionWhenNameContainsSpecialCharacters() {
        assertThrows(BusinessException.class, () -> User.create(
                VALID_ID,
                "asdasd!@",
                VALID_LASTNAME,
                VALID_EMAIL,
                VALID_PASSWORD,
                VALID_ROLEID
        ));
    }

    @Test
    void shouldThrowExceptionWhenLastNameIsEmpty() {
        assertThrows(BusinessException.class, () -> User.create(
                VALID_ID,
                VALID_NAME,
                "",
                VALID_EMAIL,
                VALID_PASSWORD,
                VALID_ROLEID
        ));
    }

    @Test
    void shouldThrowExceptionWhenLastNameIsLessThanThreeCharacters() {
        assertThrows(BusinessException.class, () -> User.create(
                VALID_ID,
                VALID_NAME,
                "as",
                VALID_EMAIL,
                VALID_PASSWORD,
                VALID_ROLEID
        ));
    }

    @Test
    void shouldThrowExceptionWhenLastNameIsGreaterThanTwentyCharacters() {
        assertThrows(BusinessException.class, () -> User.create(
                VALID_ID,
                VALID_NAME,
                "djkfjaskdfddfldalasdknkdfmsd",
                VALID_EMAIL,
                VALID_PASSWORD,
                VALID_ROLEID
        ));
    }

    @Test
    void shouldThrowExceptionWhenLastNameContainsSpecialCharacters() {
        assertThrows(BusinessException.class, () -> User.create(
                VALID_ID,
                VALID_NAME,
                "asdasd!@",
                VALID_EMAIL,
                VALID_PASSWORD,
                VALID_ROLEID
        ));
    }

    @Test
    void shouldThrowExceptionWhenEmailIsEmpty() {
        assertThrows(BusinessException.class, () -> User.create(
                VALID_ID, VALID_NAME,
                VALID_LASTNAME,
                "",
                VALID_PASSWORD,
                VALID_ROLEID
        ));
    }

    @Test
    void shouldThrowExceptionWhenEmailIsInvalid() {
        assertThrows(BusinessException.class, () -> User.create(
                VALID_ID, VALID_NAME,
                VALID_LASTNAME,
                "asdfcom",
                VALID_PASSWORD,
                VALID_ROLEID
        ));
    }

    @Test
    void shouldThrowExceptionWhenPasswordIsEmpty() {
        assertThrows(BusinessException.class, () -> User.create(
                VALID_ID, VALID_NAME,
                VALID_LASTNAME,
                VALID_EMAIL,
                "",
                VALID_ROLEID
        ));
    }

    @Test
    void shouldThrowExceptionWhenPasswordIsNotBetweenSixAndTwelveCharacters() {
        assertThrows(BusinessException.class, () -> User.create(
                VALID_ID, VALID_NAME,
                VALID_LASTNAME,
                VALID_EMAIL,
                "12345",
                VALID_ROLEID
        ));
    }

    @Test
    void shouldCreateValidUser() {

        User user = User.create(VALID_ID, VALID_NAME, VALID_LASTNAME, VALID_EMAIL, VALID_PASSWORD, VALID_ROLEID);

        assertEquals(VALID_NAME, user.getName());
        assertEquals(VALID_LASTNAME, user.getLastname());
        assertEquals(VALID_EMAIL, user.getEmail());
    }
}