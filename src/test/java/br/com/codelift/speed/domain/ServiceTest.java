package br.com.codelift.speed.domain;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ServiceTest {

    UUID VALID_ID = UUID.randomUUID();
    String VALID_NAME = "SERVICE01";
    String VALID_DESCRIPTION = "FAKE DESCRIPTION";
    BigDecimal VALID_PRICE = new BigDecimal("65123.30");

    @Test
    void shouldCreateValidService() {
        Service service = Service.create(
                VALID_ID,
                VALID_NAME,
                VALID_DESCRIPTION,
                VALID_PRICE,
                ServiceStatus.ENABLE
        );

        assertEquals(VALID_ID, service.getId());
        assertEquals(VALID_NAME, service.getName());
        assertEquals(VALID_DESCRIPTION, service.getDescription());
        assertEquals(VALID_PRICE, service.getPrice());
    }

}