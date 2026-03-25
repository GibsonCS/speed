package br.com.codelift.speed.domain.entity;

import br.com.codelift.speed.domain.entity.enums.OrderItemStatus;
import br.com.codelift.speed.domain.entity.enums.OrderStatus;
import br.com.codelift.speed.domain.vo.Address;
import br.com.codelift.speed.exception.BusinessException;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    UUID VALID_ID = UUID.randomUUID();
    UUID VALID_CREATED_BY_USER_ID = UUID.randomUUID();
    UUID VALID_CUSTOMER_ID = UUID.randomUUID();
    LocalDateTime VALID_ORDER_DATE = LocalDateTime.now().plusDays(1);
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
                VALID_CUSTOMER_ID,
                VALID_ORDER_DATE
        );

        assertEquals(VALID_ID, order.getId().getValue());
        assertEquals(VALID_CREATED_BY_USER_ID, order.getCreatedByUserId().getValue());
        assertEquals(VALID_CUSTOMER_ID, order.getCustomerId().getValue());
        assertSame(order.getStatus(), OrderStatus.PENDING);
    }

    @Test
    void shouldNotCreateOrderWithInvalidDate() {
        assertThrows(BusinessException.class, () -> Order.create(VALID_ID,
                VALID_CREATED_BY_USER_ID,
                VALID_CUSTOMER_ID,
                LocalDateTime.now().minusDays(1)
        ));
    }

    @Test
    void shouldNotCreateOrderWithOrderTotalLessThan100() {
        assertThrows(BusinessException.class, () -> Order.create(VALID_ID,
                VALID_CREATED_BY_USER_ID,
                VALID_CUSTOMER_ID,
                LocalDateTime.now().minusDays(1)
        ));
    }
}