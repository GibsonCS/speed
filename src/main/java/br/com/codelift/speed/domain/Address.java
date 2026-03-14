package br.com.codelift.speed.domain;

public class Address {

    private final String street;
    private final String neighborhood;
    private final Character uf;
    private final String zipCode;


    private Address(String street, String neighborhood, Character uf, String zipCode) {
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

    public Character getUf() {
        return uf;
    }

    public String getZipCode() {
        return zipCode;
    }
}
