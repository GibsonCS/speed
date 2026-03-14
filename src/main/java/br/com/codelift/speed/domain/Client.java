package br.com.codelift.speed.domain;

import br.com.codelift.speed.exception.BusinessException;

import java.util.UUID;

public class Client {

    private final UUID id;
    private final UUID createdByUserId;
    private final String cnpj;
    private final String companyName;
    private final Phone phoneNumber;
    private final String email;
    private final Address address;

    private Client(
            UUID id,
            UUID createdByUserId,
            String cnpj,
            String companyName,
            Phone phoneNumber,
            String email,
            Address address
    ) {
        this.id = id;
        this.createdByUserId = createdByUserId;
        this.cnpj = cnpj;
        this.companyName = companyName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
    }

    public UUID getId() {
        return id;
    }

    public UUID getCreatedByUserId() {
        return createdByUserId;
    }

    public String getCnpj() {
        return cnpj;
    }

    public String getCompanyName() {
        return companyName;
    }

    public Phone getPhone() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    public static Client create(
            UUID id,
            UUID createdByUserId,
            String cnpj,
            String companyName,
            Phone phoneNumber,
            String email,
            Address address
    ) {
        validateClientId(id);

        return new Client(id, createdByUserId, cnpj, companyName, phoneNumber, email, address);
    }

    private static void validateClientId(UUID id) {
        if (id == null) throw new BusinessException("Client id cannot be null");
    }


}
