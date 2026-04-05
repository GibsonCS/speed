package br.com.codelift.speed.core.domain.entity;

import br.com.codelift.speed.core.domain.vo.Email;
import br.com.codelift.speed.core.exception.BusinessException;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    private final UUID VALID_ID = UUID.randomUUID();
    private final String VALID_NAME = "Gibson";
    private final String VALID_LASTNAME = "Silva";
    private final String VALID_PASSWORD = "125Adm$";
    private final String VALID_EMAIL = "gibson@gmail.com";
    private final Set<UUID> VALID_ROLEID = new HashSet<>();

    @Test
    void shouldThrowExceptionWhenNameIsEmpty() {
        VALID_ROLEID.add(UUID.fromString("318df173-32e1-4865-b380-e3f2343955b4"));
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
        VALID_ROLEID.add(UUID.fromString("318df173-32e1-4865-b380-e3f2343955b4"));
        assertThrows(BusinessException.class, () -> User.create(
                VALID_ID,
                ("an"),
                VALID_LASTNAME,
                VALID_EMAIL,
                VALID_PASSWORD,
                VALID_ROLEID
        ));
    }

    @Test
    void shouldThrowExceptionWhenNameIsGreaterThanTwentyCharacters() {
        VALID_ROLEID.add(UUID.fromString("318df173-32e1-4865-b380-e3f2343955b4"));
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
        VALID_ROLEID.add(UUID.fromString("318df173-32e1-4865-b380-e3f2343955b4"));
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
        VALID_ROLEID.add(UUID.fromString("318df173-32e1-4865-b380-e3f2343955b4"));
        assertThrows(BusinessException.class, () -> User.create(
                VALID_ID,
                VALID_NAME,
                (""),
                VALID_EMAIL,
                VALID_PASSWORD,
                VALID_ROLEID
        ));
    }

    @Test
    void shouldThrowExceptionWhenLastNameIsLessThanThreeCharacters() {
        VALID_ROLEID.add(UUID.fromString("318df173-32e1-4865-b380-e3f2343955b4"));
        assertThrows(BusinessException.class, () -> User.create(
                VALID_ID,
                VALID_NAME,
                ("as"),
                VALID_EMAIL,
                VALID_PASSWORD,
                VALID_ROLEID
        ));
    }

    @Test
    void shouldThrowExceptionWhenLastNameIsGreaterThanTwentyCharacters() {
        VALID_ROLEID.add(UUID.fromString("318df173-32e1-4865-b380-e3f2343955b4"));
        assertThrows(BusinessException.class, () -> User.create(
                VALID_ID,
                VALID_NAME,
                ("djkfjaskdfddfldalasdknkdfmsd"),
                VALID_EMAIL,
                VALID_PASSWORD,
                VALID_ROLEID
        ));
    }

    @Test
    void shouldThrowExceptionWhenLastNameContainsSpecialCharacters() {
        VALID_ROLEID.add(UUID.fromString("318df173-32e1-4865-b380-e3f2343955b4"));
        assertThrows(BusinessException.class, () -> User.create(
                VALID_ID,
                VALID_NAME,
                ("asdasd!@"),
                VALID_EMAIL,
                VALID_PASSWORD,
                VALID_ROLEID
        ));
    }

    @Test
    void shouldThrowExceptionWhenEmailIsEmpty() {
        VALID_ROLEID.add(UUID.fromString("318df173-32e1-4865-b380-e3f2343955b4"));
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
        VALID_ROLEID.add(UUID.fromString("318df173-32e1-4865-b380-e3f2343955b4"));
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
        VALID_ROLEID.add(UUID.fromString("318df173-32e1-4865-b380-e3f2343955b4"));
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
        VALID_ROLEID.add(UUID.fromString("318df173-32e1-4865-b380-e3f2343955b4"));
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
        VALID_ROLEID.add(UUID.fromString("318df173-32e1-4865-b380-e3f2343955b4"));

        User user = User.create(
                VALID_ID,
                VALID_NAME,
                VALID_LASTNAME,
                VALID_EMAIL,
                VALID_PASSWORD,
                VALID_ROLEID
        );

        assertEquals(VALID_ID, user.getId().getValue());
        assertEquals(VALID_NAME, user.getName().getValue());
        assertEquals(VALID_LASTNAME, user.getLastname().getValue());
        assertEquals(Email.create(VALID_EMAIL), user.getEmail());
    }

    @Test
    void shouldAddRole() {
        VALID_ROLEID.add(UUID.fromString("318df173-32e1-4865-b380-e3f2343955b4"));

        User user = User.create(
                VALID_ID,
                VALID_NAME,
                VALID_LASTNAME,
                VALID_EMAIL,
                VALID_PASSWORD,
                VALID_ROLEID
        );

        user.addRole(VALID_ID);

        assertTrue(user.getRoleIds().contains(VALID_ID));
    }

    @Test
    void shouldNotAddRoleThatHasAlreadyExists() {
        VALID_ROLEID.add(UUID.fromString("318df173-32e1-4865-b380-e3f2343955b4"));

        User user = User.create(
                VALID_ID,
                VALID_NAME,
                VALID_LASTNAME,
                VALID_EMAIL,
                VALID_PASSWORD,
                VALID_ROLEID
        );

        user.addRole(UUID.fromString("4859cafd-f302-4f9e-9efb-58d77761d097"));

        assertThrows(BusinessException.class, () -> user.addRole(UUID.fromString(
                "4859cafd-f302-4f9e-9efb-58d77761d097"
        )));
    }
}