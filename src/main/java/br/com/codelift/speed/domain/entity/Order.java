package br.com.codelift.speed.domain.entity;

import br.com.codelift.speed.domain.entity.enums.OrderStatus;
import br.com.codelift.speed.domain.vo.Id;
import br.com.codelift.speed.exception.BusinessException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Order {

    private final Id id;
    private final Id createdByUserId;
    private final Id customerId;
    private final LocalDateTime orderDate;
    private BigDecimal orderTotal;
    private OrderStatus status;
    private final Map<Id, OrderItem> orderItems = new HashMap<>();

    public static Order create(
            UUID id,
            UUID createdByUserId,
            UUID customerId
    ) {
        return new Order(
                Id.create(id),
                Id.create(createdByUserId),
                Id.create(customerId),
                BigDecimal.valueOf(0),
                OrderStatus.DRAFT,
                LocalDateTime.now()
        );
    }

    public void submit() {

        if (this.orderItems.isEmpty()) {
            throw new BusinessException("Cannot submit an empty order");
        }

        this.status = OrderStatus.IN_ANALISE;
    }

    public void addItem(OrderItem orderItem) {

        if (this.orderItems.containsKey(orderItem.getId())) {
            throw new BusinessException("Item has already been added");
        }

        if (this.status == OrderStatus.CANCELED) {
            throw new BusinessException("Cannot add item in a canceled order");
        }

        this.orderItems.put(orderItem.getId(), orderItem);
    }

    public void cancel() {
        this.status = OrderStatus.CANCELED;
    }

    private Order(
            Id id,
            Id createdByUserId,
            Id customerId,
            BigDecimal orderTotal,
            OrderStatus status,
            LocalDateTime orderDate
    ) {
        this.id = id;
        this.createdByUserId = createdByUserId;
        this.customerId = customerId;
        this.orderTotal = orderTotal;
        this.status = status;
        this.orderDate = orderDate;
    }

    public Id getId() {
        return id;
    }

    public Id getCreatedByUserId() {
        return createdByUserId;
    }

    public Id getCustomerId() {
        return customerId;
    }

    public BigDecimal getOrderTotal() {
        return orderTotal;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public Map<Id, OrderItem> getOrderItems() {
        return orderItems;
    }
}
