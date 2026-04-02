package br.com.codelift.speed.domain.vo;

import br.com.codelift.speed.core.domain.vo.Id;
import br.com.codelift.speed.exception.BusinessException;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class IdTest {

    UUID VALID_ID = UUID.randomUUID();

    @Test
    void shouldReturnAValidId() {

        Id id = Id.create(VALID_ID);

        assertEquals(VALID_ID, id.getValue());
    }

    @Test
    void shouldNotCreateWithNullId() {
        assertThrows(BusinessException.class, () -> Id.create(null));
    }
}