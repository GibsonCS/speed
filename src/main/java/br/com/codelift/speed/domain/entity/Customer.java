package br.com.codelift.speed.domain.entity;

import br.com.codelift.speed.domain.vo.Address;
import br.com.codelift.speed.domain.vo.Email;
import br.com.codelift.speed.domain.vo.Id;
import br.com.codelift.speed.domain.vo.Phone;
import br.com.codelift.speed.exception.BusinessException;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class Customer {

    private final Id id;
    private final Id createdByUserId;
    private final String cnpj;
    private final String companyName;
    private final Phone phoneNumber;
    private final Email email;
    private final Address address;
    private final Set<Id> orderIds = new HashSet<>();

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
        validateCnpj(cnpj);
        validateCompanyName(companyName);

        return new Customer(
                Id.create(id),
                Id.create(createdByUserId),
                cnpj, companyName,
                phoneNumber,
                Email.create(email),
                address,
                Id.create(orderId)
        );
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
            Id id,
            Id createdByUserId,
            String cnpj,
            String companyName,
            Phone phoneNumber,
            Email email,
            Address address,
            Id orderId
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

    public Id getId() {
        return id;
    }

    public Id getCreatedByUserId() {
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

    public Set<Id> getOrderIds() {
        return orderIds;
    }

    public Email getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }
}
