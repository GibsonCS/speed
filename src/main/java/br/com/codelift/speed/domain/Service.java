package br.com.codelift.speed.domain;

import java.math.BigDecimal;
import java.util.UUID;

public class Service {

    private final UUID id;
    private final String name;
    private final String description;
    private final BigDecimal price;
    private final ServiceStatus status;

    private Service(UUID id, String name, String description, BigDecimal price, ServiceStatus status) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.status = status;
    }

    //
    public static Service create(
            UUID id,
            String name,
            String description,
            BigDecimal price,
            ServiceStatus status
    ) {

        return new Service(id, name, description, price, status);
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
}
