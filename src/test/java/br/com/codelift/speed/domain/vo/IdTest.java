package br.com.codelift.speed.domain.vo;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IdTest {

    UUID VALID_ID = UUID.randomUUID();

    @Test
    void shouldReturnAValidId() {

        Id id = Id.create(VALID_ID);

        assertEquals(VALID_ID, id.getValue());
    }

}