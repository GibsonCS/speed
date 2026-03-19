package br.com.codelift.speed.domain;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OrderItemTest {

    UUID VALID_SERVICEID = UUID.fromString("918f1cce-bd74-4289-9220-4fa4cb6678b6");
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
                VALID_SERVICEID,
                VALID_EXECUTION_ADDRESS,
                VALID_STATUS,
                VALID_EXECUTION_DATE,
                VALID_CHARGED_PRICE
        );

        assertEquals(VALID_SERVICEID, orderItem.getServiceId());
        assertEquals(VALID_EXECUTION_ADDRESS, orderItem.getexecutionAddress());
        assertEquals(VALID_EXECUTION_DATE, orderItem.getExecutionDate());
    }

}