package br.com.codelift.speed.domain;

import java.math.BigDecimal;
import java.util.UUID;

public class Service {

    private final UUID id;
    private final String name;
    private final String description;
    private final BigDecimal price;
    private final Boolean active;

    private Service(UUID id, String name, String description, BigDecimal price, Boolean active) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.active = active;
    }

    public static Service create(UUID id, String name, String description, BigDecimal price, Boolean active) {

        return new Service(id, name, description, price, active);
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

    public Boolean getActive() {
        return active;
    }
}
