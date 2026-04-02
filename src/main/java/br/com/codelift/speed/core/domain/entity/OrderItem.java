package br.com.codelift.speed.core.domain.entity;

import br.com.codelift.speed.core.domain.entity.enums.OrderItemStatus;
import br.com.codelift.speed.core.domain.vo.Address;
import br.com.codelift.speed.core.domain.vo.Id;
import br.com.codelift.speed.exception.BusinessException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class OrderItem {

    private final Id id;
    private final Id serviceId;
    private final Address executionAddress;
    private final OrderItemStatus status;
    private final LocalDate executionDate;
    private final BigDecimal chargedPrice;

    public static OrderItem create(
            UUID id,
            UUID serviceId,
            Address executionAddress,
            OrderItemStatus status,
            LocalDate executionDate,
            BigDecimal chargedPrice
    ) {
        validateExecutionDate(executionDate);
        validateChargedPrice(chargedPrice);

        return new OrderItem(
                Id.create(id),
                Id.create(serviceId),
                executionAddress,
                status,
                executionDate,
                chargedPrice
        );
    }

    private static void validateServiceId(UUID serviceId) {
        if (serviceId == null) {
            throw new BusinessException("Service id cannot be null");
        }
    }

    private static void validateExecutionDate(LocalDate executionDate) {
        if (executionDate.isBefore(LocalDate.now())) {
            throw new BusinessException("Invalid date");
        }
    }

    private static void validateChargedPrice(BigDecimal chargedPrice) {
        if (chargedPrice.compareTo(BigDecimal.valueOf(0)) <= 0) {
            throw new BusinessException("Charged price must be more than 0");
        }
    }

    private OrderItem(
            Id id,
            Id serviceId,
            Address executionAddress,
            OrderItemStatus status,
            LocalDate executionDate,
            BigDecimal chargedPrice
    ) {
        this.id = id;
        this.serviceId = serviceId;
        this.executionAddress = executionAddress;
        this.status = status;
        this.executionDate = executionDate;
        this.chargedPrice = chargedPrice;
    }

    public Id getId() {
        return id;
    }

    public Address getExecutionAddress() {
        return executionAddress;
    }

    public Id getServiceId() {
        return serviceId;
    }

    public Address getexecutionAddress() {
        return executionAddress;
    }

    public OrderItemStatus getStatus() {
        return status;
    }

    public LocalDate getExecutionDate() {
        return executionDate;
    }

    public BigDecimal getChargedPrice() {
        return chargedPrice;
    }
}
