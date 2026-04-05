package br.com.codelift.speed.domain.vo;

import br.com.codelift.speed.core.exception.BusinessException;
import br.com.codelift.speed.core.domain.vo.Name;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class NameTest {

    String VALID_NAME = "Enterprise name";

    @Test
    void shouldCreateAValidName() {

        Name name = Name.create(VALID_NAME);

        Assertions.assertEquals(VALID_NAME, name.getValue());
    }

    @Test
    void shouldNotCreateAInvalidName() {

        Assertions.assertThrows(BusinessException.class, () -> Name.create(""));
    }
}