package br.com.codelift.speed.domain.entity;

import br.com.codelift.speed.domain.vo.Address;
import br.com.codelift.speed.domain.vo.Email;
import br.com.codelift.speed.domain.vo.Phone;
import br.com.codelift.speed.exception.BusinessException;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class Customer {

    private final UUID id;
    private final UUID createdByUserId;
    private final String cnpj;
    private final String companyName;
    private final Phone phoneNumber;
    private final Email email;
    private final Address address;
    private final Set<UUID> orderIds = new HashSet<>();

    public static Customer create(
            UUID id,
            UUID createdByUserId,
            String cnpj,
            String companyName,
            Phone phoneNumber,
            String email,
            Address address,
            UUID orderId
    ) {
        validateClientId(id);
        validateUserId(createdByUserId);
        validateCnpj(cnpj);
        validateCompanyName(companyName);

        return new Customer(
                id,
                createdByUserId,
                cnpj, companyName,
                phoneNumber,
                Email.create(email),
                address,
                orderId
        );
    }

    private static void validateClientId(UUID id) {
        if (id == null) throw new BusinessException("Client id cannot be null");
    }

    private static void validateUserId(UUID userId) {
        if (userId == null) throw new BusinessException("User id cannot be null");
    }

    private static void validateCnpj(String cnpj) {
        if (cnpj == null) throw new BusinessException("Insert a valid cnpj");

        if (!cnpj.matches("^\\d{2}.\\d{3}.\\d{3}.\\d{4}-\\d{2}$")) {
            throw new BusinessException("Insert a valid cnpj");
        }
    }

    private static void validateCompanyName(String companyName) {
        if (companyName == null) throw new BusinessException("Company name cannot be null");
    }

    private Customer(
            UUID id,
            UUID createdByUserId,
            String cnpj,
            String companyName,
            Phone phoneNumber,
            Email email,
            Address address,
            UUID orderId
    ) {
        this.id = id;
        this.createdByUserId = createdByUserId;
        this.cnpj = cnpj;
        this.companyName = companyName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.orderIds.add(orderId);
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

    public Phone getPhoneNumber() {
        return phoneNumber;
    }

    public Set<UUID> getOrderIds() {
        return orderIds;
    }

    public Email getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }
}
