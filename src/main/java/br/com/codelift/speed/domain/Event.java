package br.com.codelift.speed.domain;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public class Event {
    private final UUID serviceId;
    private final Address address;
    private final EventStatus status;
    private final Instant eventDate;
    private final BigDecimal chargedPrice;

    public Event(UUID serviceId, Address address, EventStatus status, Instant eventDate, BigDecimal chargedPrice) {
        this.serviceId = serviceId;
        this.address = address;
        this.status = status;
        this.eventDate = eventDate;
        this.chargedPrice = chargedPrice;
    }

    public UUID getServiceId() {
        return serviceId;
    }

    public Address getAddress() {
        return address;
    }

    public EventStatus getStatus() {
        return status;
    }

    public Instant getEventDate() {
        return eventDate;
    }

    public BigDecimal getChargedPrice() {
        return chargedPrice;
    }
}
