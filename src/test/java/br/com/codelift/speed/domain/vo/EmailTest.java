package br.com.codelift.speed.domain.vo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EmailTest {

    String VALID_EMAIL = "gibson@gmail.com";

    @Test
    void shouldCreateAValidEmail() {

        Email email = Email.create(VALID_EMAIL);

        assertEquals(VALID_EMAIL, email.getValue());
    }

}