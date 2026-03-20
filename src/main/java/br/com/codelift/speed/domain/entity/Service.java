package br.com.codelift.speed.domain.entity;

import br.com.codelift.speed.domain.ServiceStatus;
import br.com.codelift.speed.exception.BusinessException;

import java.math.BigDecimal;
import java.util.UUID;

public class Service {

    private final UUID id;
    private final String name;
    private final String description;
    private BigDecimal price;
    private final ServiceStatus status;

    private Service(UUID id, String name, String description, BigDecimal price, ServiceStatus status) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.status = status;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public ServiceStatus getActive() {
        return status;
    }

    public static Service create(
            UUID id,
            String name,
            String description,
            BigDecimal price,
            ServiceStatus status
    ) {
        validateName(name);
        validatePrice(price);
        validateDescription(description);

        return new Service(id, name, description, price, status);
    }

    private static void validateName(String name) {
        if (name == null || name.isBlank()) throw new BusinessException("Name cannot be null");
    }

    private static void validatePrice(BigDecimal price) {
        if (price.compareTo(BigDecimal.valueOf(50)) < 0) {
            throw new BusinessException("Value cannot be less than R$50,00");
        }
    }

    private static void validateDescription(String description) {
        if (description == null || description.isBlank()) {
            throw new BusinessException("Description cannot be null");
        }
    }

    public void disableService() {
        if (this.status == ServiceStatus.DISABLE) {
            throw new BusinessException("Service is already disable");
        }
    }

    public void updatePrice(BigDecimal newPrice) {
        validatePrice(price);
        this.price = newPrice;
    }
}
