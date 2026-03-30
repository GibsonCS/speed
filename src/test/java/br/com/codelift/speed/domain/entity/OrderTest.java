package br.com.codelift.speed.domain.entity;

import br.com.codelift.speed.domain.entity.enums.OrderItemStatus;
import br.com.codelift.speed.domain.entity.enums.OrderStatus;
import br.com.codelift.speed.domain.vo.Address;
import br.com.codelift.speed.exception.BusinessException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    UUID VALID_ID = UUID.randomUUID();
    UUID VALID_CREATED_BY_USER_ID = UUID.randomUUID();
    UUID VALID_CUSTOMER_ID = UUID.randomUUID();
    UUID _ID = UUID.randomUUID();
    UUID VALID_SERVICE_ID = UUID.fromString("918f1cce-bd74-4289-9220-4fa4cb6678b6");
    OrderItemStatus VALID_ORDER_ITEM_STATUS = OrderItemStatus.PENDING;
    LocalDate VALID_EXECUTION_DATE = LocalDate.of(2526, 12, 5);
    BigDecimal VALID_CHARGED_PRICE = BigDecimal.valueOf(5000.90);

    Address VALID_EXECUTION_ADDRESS = Address.create(
            "Street 01",
            "neighborhood 01",
            "RJ",
            "21532-000"
    );

    Order order;
    OrderItem orderItem;

    @BeforeEach
    void setup() {
        order = Order.create(
                VALID_ID,
                VALID_CREATED_BY_USER_ID,
                VALID_CUSTOMER_ID
        );

        orderItem = OrderItem.create(
                VALID_ID,
                VALID_SERVICE_ID,
                VALID_EXECUTION_ADDRESS,
                VALID_ORDER_ITEM_STATUS,
                VALID_EXECUTION_DATE,
                VALID_CHARGED_PRICE
        );
    }

    @Test
    void shouldCreateAValidOrder() {
        assertEquals(VALID_ID, order.getId().getValue());
        assertEquals(VALID_CREATED_BY_USER_ID, order.getCreatedByUserId().getValue());
        assertEquals(VALID_CUSTOMER_ID, order.getCustomerId().getValue());
        assertEquals(order.getStatus(), OrderStatus.DRAFT);
    }

    @Test
    void shouldSubmittedAnOrder() {

        order.addItem(orderItem);
        order.submit();

        assertEquals(OrderStatus.IN_ANALISE, order.getStatus());
    }

    @Test
    void shouldNotSubmitAnEmptyOrder() {
        assertThrows(BusinessException.class, order::submit);
    }

    @Test
    void shouldAddANewItem() {

        order.addItem(orderItem);

        assertTrue(order.getOrderItems().containsKey(orderItem.getId()));
    }

    @Test
    void shouldNotAddDuplicatedItem() {

        order.addItem(orderItem);

        assertThrows(BusinessException.class, () -> order.addItem(orderItem));
    }

    @Test
    void shouldNotAddItemInACanceledOrder() {

        order.cancel();

        assertThrows(BusinessException.class, () -> order.addItem(orderItem));
    }

    @Test
    void shouldPayAnOrder() {

        order.pay();

        assertEquals(OrderStatus.PAID, order.getStatus());
    }

    @Test
    void shouldNotPayAnOrderThatHasAlreadyBeenPaid() {

        order.pay();

        assertThrows(BusinessException.class, order::pay);
    }

    @Test
    void shouldNotPayAnOrderThatHasAlreadyCanceled() {

        order.cancel();

        assertThrows(BusinessException.class, order::pay);
    }

    @Test
    void shouldNotPayForAnOrderThatIsUnderReview() {

        order.analyze();

        assertThrows(BusinessException.class, order::pay);
    }

    @Test
    void shouldCancelAnOrder() {
        order.cancel();

        assertEquals(OrderStatus.CANCELED, order.getStatus());
    }

    @Test
    void shouldNotCancelAnOrderThatIsCanceled() {

        order.cancel();

        assertThrows(BusinessException.class, order::cancel);
    }

    @Test
    void shouldConfirmAnOrder() {

        order.addItem(orderItem);

        order.confirm();

        assertEquals(OrderStatus.CONFIRMED, order.getStatus());
    }

    @Test
    void shouldNotToConfirmAnOrderThatHasAlreadyConfirmed() {

        order.addItem(orderItem);

        order.confirm();

        assertThrows(BusinessException.class, order::confirm);
    }

    @Test
    void shouldNotAddItemInAnOrderHasAlreadyConfirmed() {

        order.addItem(orderItem);

        order.confirm();

        assertThrows(BusinessException.class, () -> order.addItem(orderItem));
    }

    @Test
    void shouldNotConfirmOrderWithoutItems() {

        assertThrows(BusinessException.class, order::confirm);
    }

    @Test
    void shouldNotAddItemInAPaidOrder() {

        order.pay();

        assertThrows(BusinessException.class, () -> order.addItem(orderItem));
    }

    @Test
    void shouldRejectAnOrder() {

        order.reject();

        assertEquals(OrderStatus.REJECTED, order.getStatus());
    }
}