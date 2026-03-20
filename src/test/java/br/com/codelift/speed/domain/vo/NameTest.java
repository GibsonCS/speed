package br.com.codelift.speed.domain.vo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NameTest {

    String VALID_NAME = "Enterprise name";

    @Test
    void shouldCreateAValidName() {

        Name name = Name.create(VALID_NAME);

        assertEquals(VALID_NAME, name.getValue());
    }

}