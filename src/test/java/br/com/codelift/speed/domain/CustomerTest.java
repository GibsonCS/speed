package br.com.codelift.speed.domain;

import br.com.codelift.speed.domain.entity.Customer;
import br.com.codelift.speed.domain.entity.Phone;
import br.com.codelift.speed.domain.vo.Address;
import br.com.codelift.speed.exception.BusinessException;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;

class CustomerTest {

    private final UUID VALID_ID = UUID.randomUUID();
    private final UUID VALID_createdByUserId = UUID.randomUUID();
    private final String VALID_CNPJ = "01.700.151.0001-15";
    private final String VALID_COMPANYNAME = "EMPRESAX";
    private final Phone VALID_PHONENUMBER = Phone.create("(21)99654-4074");
    private final String VALID_EMAIL = "gibson@gibson.com";
    private final Address VALID_ADDRESS = Address.create("Street one", "nb01", "Rj", "26165445");

    @Test
    void shoudThrowBusinessExceptionIfClientIdIsNull() {

        assertThrows(BusinessException.class, () -> Customer.create(
                null,
                VALID_createdByUserId,
                VALID_CNPJ,
                VALID_COMPANYNAME,
                VALID_PHONENUMBER,
                VALID_EMAIL,
                VALID_ADDRESS
        ));
    }

    @Test
    void shoudThrowBusinessExceptionIfUserIdIsNull() {

        assertThrows(BusinessException.class, () -> Customer.create(
                VALID_ID,
                null,
                VALID_CNPJ,
                VALID_COMPANYNAME,
                VALID_PHONENUMBER,
                VALID_EMAIL,
                VALID_ADDRESS
        ));
    }

    @Test
    void shoudThrowBusinessExceptionCnpjIsNull() {

        assertThrows(BusinessException.class, () -> Customer.create(
                VALID_ID,
                VALID_createdByUserId,
                null,
                VALID_COMPANYNAME,
                VALID_PHONENUMBER,
                VALID_EMAIL,
                VALID_ADDRESS
        ));
    }

    @Test
    void shoudThrowBusinessExceptionCnpjIsInvalid() {

        assertThrows(BusinessException.class, () -> Customer.create(
                VALID_ID,
                VALID_createdByUserId,
                "01.700.151.0001-150",
                VALID_COMPANYNAME,
                VALID_PHONENUMBER,
                VALID_EMAIL,
                VALID_ADDRESS
        ));
    }

    @Test
    void shoudThrowBusinessExceptionIfCompanyNameIsNull() {

        assertThrows(BusinessException.class, () -> Customer.create(
                VALID_ID,
                VALID_createdByUserId,
                VALID_CNPJ,
                null,
                VALID_PHONENUMBER,
                VALID_EMAIL,
                VALID_ADDRESS
        ));
    }

    @Test
    void shoudThrowBusinessExceptionIfEmailIsNull() {

        assertThrows(BusinessException.class, () -> Customer.create(
                VALID_ID,
                VALID_createdByUserId,
                VALID_CNPJ,
                VALID_COMPANYNAME,
                VALID_PHONENUMBER,
                null,
                VALID_ADDRESS
        ));
    }

    @Test
    void shoudThrowBusinessExceptionIfTheEmailIsInvalid() {

        assertThrows(BusinessException.class, () -> Customer.create(
                VALID_ID,
                VALID_createdByUserId,
                VALID_CNPJ,
                VALID_COMPANYNAME,
                VALID_PHONENUMBER,
                "jfngfdfcom",
                VALID_ADDRESS
        ));
    }

}