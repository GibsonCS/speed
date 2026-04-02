package br.com.codelift.speed.core.domain.vo;

import java.util.Objects;

public class Address {

    private final String street;
    private final String neighborhood;
    private final String uf;
    private final String zipCode;

    public static Address create(
            String street,
            String neighborhood,
            String uf,
            String zipCode
    ) {
        return new Address(street, neighborhood, uf, zipCode);
    }

    private Address
            (String street,
             String neighborhood,
             String uf,
             String zipCode
            ) {
        this.street = street;
        this.neighborhood = neighborhood;
        this.uf = uf;
        this.zipCode = zipCode;
    }

    public String getStreet() {
        return street;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public String getUf() {
        return uf;
    }

    public String getZipCode() {
        return zipCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof Address address)) return false;

        return Objects.equals(street, address.street)
                && Objects.equals(neighborhood, address.neighborhood)
                && Objects.equals(uf, address.uf)
                && Objects.equals(zipCode, address.zipCode
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(street, neighborhood, uf, zipCode);
    }
}
