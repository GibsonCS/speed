package br.com.codelift.speed.domain.entity;

import br.com.codelift.speed.domain.entity.enums.OrderItemStatus;
import br.com.codelift.speed.domain.entity.enums.OrderStatus;
import br.com.codelift.speed.domain.vo.Address;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OrderTest {

    UUID VALID_ID = UUID.randomUUID();
    UUID VALID_CREATED_BY_USER_ID = UUID.randomUUID();
    UUID VALID_CUSTOMER_ID = UUID.randomUUID();
    UUID _ID = UUID.randomUUID();
    UUID VALID_SERVICE_ID = UUID.fromString("918f1cce-bd74-4289-9220-4fa4cb6678b6");
    OrderItemStatus _STATUS = OrderItemStatus.PENDING;
    LocalDate VALID_EXECUTION_DATE = LocalDate.of(2526, 12, 5);
    BigDecimal VALID_CHARGED_PRICE = BigDecimal.valueOf(5000.90);

    Address VALID_EXECUTION_ADDRESS = Address.create(
            "Street 01",
            "neighborhood 01",
            "RJ",
            "21532-000"
    );

    @Test
    void shouldCreateAValidOrder() {
        Order order = Order.create(
                VALID_ID,
                VALID_CREATED_BY_USER_ID,
                VALID_CUSTOMER_ID
        );

        assertEquals(VALID_ID, order.getId().getValue());
        assertEquals(VALID_CREATED_BY_USER_ID, order.getCreatedByUserId().getValue());
        assertEquals(VALID_CUSTOMER_ID, order.getCustomerId().getValue());
        assertEquals(order.getStatus(), OrderStatus.DRAFT);
    }

    @Test
    void shouldSubmittedAnOrder() {
        Order order = Order.create(
                VALID_ID,
                VALID_CREATED_BY_USER_ID,
                VALID_CUSTOMER_ID
        );

        order.submit();

        assertEquals(OrderStatus.SUBMITTED, order.getStatus());
    }
}