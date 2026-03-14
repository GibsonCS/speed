package br.com.codelift.speed.domain;

import br.com.codelift.speed.exception.BusinessException;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;

class ClientTest {

    private final UUID VALID_ID = UUID.randomUUID();
    private final UUID VALID_createdByUserId = UUID.randomUUID();
    private final String VALID_CNPJ = "01500124000-15";
    private final String VALID_COMPANYNAME = "EMPRESAX";
    private final Phone VALID_PHONENUMBER = Phone.create("(21)99654-4074");
    private final String VALID_EMAIL = "gibson@gibson.com";
    private final Address VALID_ADDRESS = Address.create("Street one", "nb01", "Rj", "26165445");

    @Test
    void shoudThrowBusinessExceptionIfClientIdIsNull() {

        assertThrows(BusinessException.class, () -> Client.create(
                null,
                VALID_createdByUserId,
                VALID_CNPJ,
                VALID_COMPANYNAME,
                VALID_PHONENUMBER,
                VALID_EMAIL,
                VALID_ADDRESS
        ));
    }

}