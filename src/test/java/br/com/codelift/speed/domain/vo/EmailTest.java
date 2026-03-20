package br.com.codelift.speed.domain.vo;

import br.com.codelift.speed.exception.BusinessException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class EmailTest {

    String VALID_EMAIL = "gibson@gmail.com";

    @Test
    void shouldCreateAValidEmail() {

        Email email = Email.create(VALID_EMAIL);

        assertEquals(VALID_EMAIL, email.getValue());
    }

    @Test
    void shouldNotCreateAInvalidEmail() {

        assertThrows(BusinessException.class, () -> Email.create("gibs,df%@gdas.com"));
    }
}