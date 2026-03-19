package br.com.codelift.speed.domain;

import br.com.codelift.speed.domain.vo.Address;
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
        validateUserId(createdByUserId);
        validateCnpj(cnpj);
        validateCompanyName(companyName);
        validateEmail(email);

        return new Client(id, createdByUserId, cnpj, companyName, phoneNumber, email, address);
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

    private static void validateEmail(String email) {
        if (email == null) throw new BusinessException("Email cannot be null");

        if (!email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,}$")) {
            throw new BusinessException("Insert a valid email");
        }
    }
}
