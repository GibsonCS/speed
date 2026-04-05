package br.com.codelift.speed.core.domain.entity;

import br.com.codelift.speed.core.domain.entity.enums.ServiceStatus;
import br.com.codelift.speed.core.exception.BusinessException;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

        assertEquals(VALID_ID, service.getId().getValue());
        assertEquals(VALID_NAME, service.getName());
        assertEquals(VALID_DESCRIPTION, service.getDescription());
        assertEquals(VALID_PRICE, service.getPrice());
    }

    @Test
    void shouldNotCreateServiceWithEmptyName() {
        assertThrows(BusinessException.class, () -> Service.create(
                VALID_ID,
                "",
                VALID_DESCRIPTION,
                VALID_PRICE,
                ServiceStatus.ENABLE
        ));
    }

    @Test
    void shouldNotCreateServiceWithInvalidPrice() {
        assertThrows(BusinessException.class, () -> Service.create(
                VALID_ID,
                VALID_NAME,
                VALID_DESCRIPTION,
                new BigDecimal("49.99"),
                ServiceStatus.ENABLE
        ));
    }

    @Test
    void shouldNotCreateServiceWithNullOrEmptyDescription() {
        assertThrows(BusinessException.class, () -> Service.create(
                VALID_ID,
                VALID_NAME,
                "",
                VALID_PRICE,
                ServiceStatus.ENABLE
        ));
    }

    @Test
    void shouldThrowABusinessExceptionIfTheServiceIsAlreadyDisable() {
        Service service = Service.create(
                VALID_ID,
                VALID_NAME,
                VALID_DESCRIPTION,
                VALID_PRICE,
                ServiceStatus.DISABLE
        );

        assertThrows(BusinessException.class, service::disableService);
        assertEquals(ServiceStatus.DISABLE, service.getActive());
    }

    @Test
    void shouldUpdatePrice() {
        Service service = Service.create(
                VALID_ID,
                VALID_NAME,
                VALID_DESCRIPTION,
                VALID_PRICE,
                ServiceStatus.ENABLE
        );

        service.updatePrice(new BigDecimal("3333.33"));

        assertEquals(new BigDecimal("3333.33"), service.getPrice());
    }
}