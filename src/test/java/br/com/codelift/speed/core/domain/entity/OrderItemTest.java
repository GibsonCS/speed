package br.com.codelift.speed.domain.entity;

import br.com.codelift.speed.core.domain.entity.OrderItem;
import br.com.codelift.speed.core.domain.entity.enums.OrderItemStatus;
import br.com.codelift.speed.core.exception.BusinessException;
import br.com.codelift.speed.core.domain.vo.Address;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class OrderItemTest {

    UUID VALID_ID = UUID.randomUUID();
    UUID VALID_SERVICE_ID = UUID.fromString("918f1cce-bd74-4289-9220-4fa4cb6678b6");
    OrderItemStatus VALID_STATUS = OrderItemStatus.PENDING;
    LocalDate VALID_EXECUTION_DATE = LocalDate.of(2526, 12, 5);
    BigDecimal VALID_CHARGED_PRICE = BigDecimal.valueOf(5000.90);

    Address VALID_EXECUTION_ADDRESS = Address.create(
            "Street 01",
            "neighborhood 01",
            "RJ",
            "21532-000"
    );


    @Test
    void shouldCreateOrderItem() {

        OrderItem orderItem = OrderItem.create(
                VALID_ID,
                VALID_SERVICE_ID,
                VALID_EXECUTION_ADDRESS,
                VALID_STATUS,
                VALID_EXECUTION_DATE,
                VALID_CHARGED_PRICE
        );

        assertEquals(VALID_SERVICE_ID, orderItem.getServiceId().getValue());
        assertEquals(VALID_EXECUTION_ADDRESS, orderItem.getexecutionAddress());
        assertEquals(VALID_STATUS, orderItem.getStatus());
        assertEquals(VALID_EXECUTION_DATE, orderItem.getExecutionDate());
        assertEquals(VALID_CHARGED_PRICE, orderItem.getChargedPrice());
    }

    @Test
    void shouldThrowBusinessExceptionIfTheServiceIdIsNull() {

        assertThrows(BusinessException.class, () -> OrderItem.create(
                null,
                VALID_SERVICE_ID,
                VALID_EXECUTION_ADDRESS,
                VALID_STATUS,
                VALID_EXECUTION_DATE,
                VALID_CHARGED_PRICE
        ));
    }

    @Test
    void shouldThrowBusinessExceptionIfTheExecutionDateIsInvalid() {

        assertThrows(BusinessException.class, () -> OrderItem.create(
                VALID_ID, VALID_SERVICE_ID,
                VALID_EXECUTION_ADDRESS,
                VALID_STATUS,
                LocalDate.of(2026, 3, 17),
                VALID_CHARGED_PRICE
        ));
    }

    @Test
    void shouldThrowBusinessExceptionToInvalidChargedPrice() {

        assertThrows(BusinessException.class, () -> OrderItem.create(
                VALID_ID, VALID_SERVICE_ID,
                VALID_EXECUTION_ADDRESS,
                VALID_STATUS,
                VALID_EXECUTION_DATE,
                BigDecimal.valueOf(0)
        ));
    }
}