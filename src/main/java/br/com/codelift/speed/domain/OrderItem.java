package br.com.codelift.speed.domain;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public class OrderItem {

    private final UUID serviceId;
    private final Address executionAddress;
    private final OrderItemStatus status;
    private final Instant executionDate;
    private final BigDecimal chargedPrice;

    private OrderItem(
            UUID serviceId,
            Address executionAddress,
            OrderItemStatus status,
            Instant executionDate,
            BigDecimal chargedPrice
    ) {
        this.serviceId = serviceId;
        this.executionAddress = executionAddress;
        this.status = status;
        this.executionDate = executionDate;
        this.chargedPrice = chargedPrice;
    }

    public UUID getServiceId() {
        return serviceId;
    }

    public Address getexecutionAddress() {
        return executionAddress;
    }

    public OrderItemStatus getStatus() {
        return status;
    }

    public Instant getExecutionDate() {
        return executionDate;
    }

    public BigDecimal getChargedPrice() {
        return chargedPrice;
    }
}
