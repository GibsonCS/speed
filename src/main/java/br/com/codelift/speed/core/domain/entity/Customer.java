package br.com.codelift.speed.domain.entity;

import br.com.codelift.speed.domain.vo.Address;
import br.com.codelift.speed.domain.vo.Email;
import br.com.codelift.speed.domain.vo.Id;
import br.com.codelift.speed.domain.vo.Phone;
import br.com.codelift.speed.exception.BusinessException;

import java.util.UUID;

public class Customer {

    private final Id id;
    private final Id createdByUserId;
    private final String cnpj;
    private final String companyName;
    private final Phone phoneNumber;
    private final Email email;
    private final Address address;

    public static Customer create(
            UUID id,
            UUID createdByUserId,
            String cnpj,
            String companyName,
            Phone phoneNumber,
            String email,
            Address address
    ) {
        validateCnpj(cnpj);
        validateCompanyName(companyName);

        return new Customer(
                Id.create(id),
                Id.create(createdByUserId),
                cnpj, companyName,
                phoneNumber,
                Email.create(email),
                address
        );
    }

    private static void validateCnpj(String cnpj) {
        if (cnpj == null) {
            throw new BusinessException("Insert a valid cnpj");
        }

        String regex = "^\\d{2}.\\d{3}.\\d{3}.\\d{4}-\\d{2}$";

        if (!cnpj.matches(regex)) {
            throw new BusinessException("Insert a valid cnpj");
        }
    }

    private static void validateCompanyName(String companyName) {
        if (companyName == null) {
            throw new BusinessException("Company name cannot be null");
        }
    }

    private Customer(
            Id id,
            Id createdByUserId,
            String cnpj,
            String companyName,
            Phone phoneNumber,
            Email email,
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

    public Email getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }
}
