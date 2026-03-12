package br.com.codelift.speed.domain;

import static org.junit.jupiter.api.Assertions.*;

import br.com.codelift.speed.exception.BusinessException;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    private final String VALID_NAME = "Gibson";
    private final String VALID_LASTNAME = "Silva";
    private final String VALID_PASSWORD = "125Adm$";
    private final String VALID_EMAIL = "gibson@gmail.com";

    @Test
    @DisplayName("Should throw a business exception if the name is empty")
    void test1(){
        assertThrows(BusinessException.class,() -> User.create(
                "",
                VALID_LASTNAME,
                VALID_EMAIL,
                VALID_PASSWORD
        ));
    }

    @Test
    @DisplayName("Should throw a business exception if the name was less than 3 characters")
    void test2(){
        assertThrows(BusinessException.class,() -> User.create(
                "an",
                VALID_LASTNAME,
                VALID_EMAIL,
                VALID_PASSWORD
        ));
    }

    @Test
    @DisplayName("Should throw a business exception if the name was more than 20 characters")
    void test3(){
        assertThrows(BusinessException.class,() -> User.create(
                "asdfdsxfdadfdassdfdgfa",
                VALID_LASTNAME,
                VALID_EMAIL,
                VALID_PASSWORD
        ));
    }

    @Test
    @DisplayName("Should throw a business exception if the name contains special characters")
    void test4(){
        assertThrows(BusinessException.class,() -> User.create(
                "asdasd!@",
                VALID_LASTNAME,
                VALID_EMAIL,
                VALID_PASSWORD
        ));
    }

    @Test
    @DisplayName("Should throw a business exception if the lastname is empty")
    void test5(){
        assertThrows(BusinessException.class,() -> User.create(
                VALID_NAME,
                "",
                VALID_EMAIL,
                VALID_PASSWORD
        ));
    }

    @Test
    @DisplayName("Should throw a business exception if the lastname was less than 3 characters")
    void test6(){
        assertThrows(BusinessException.class,() -> User.create(
                VALID_NAME,
                "as",
                VALID_EMAIL,
                VALID_PASSWORD
        ));
    }

    @Test
    @DisplayName("Should throw a business exception if the lastname was more than 20 characters")
    void test7(){
        assertThrows(BusinessException.class,() -> User.create(
                VALID_NAME,
                "djkfjaskdfddfldalasdknkdfmsd",
                VALID_EMAIL,
                VALID_PASSWORD
        ));
    }

    @Test
    @DisplayName("Should throw a business exception if the lastname contains special characters")
    void test8(){
        assertThrows(BusinessException.class,() -> User.create(
                VALID_NAME,
                "asdasd!@",
                VALID_EMAIL,
                VALID_PASSWORD
        ));
    }

    @Test
    @DisplayName("Should throw a business exception if the email is empty")
    void test9(){
        assertThrows(BusinessException.class,() -> User.create(
                VALID_NAME,
                VALID_LASTNAME,
                "",
                VALID_PASSWORD
        ));
    }

    @Test
    @DisplayName("Should throw a business exception if the email is invalid")
    void test10(){
        assertThrows(BusinessException.class,() -> User.create(
                VALID_NAME,
                VALID_LASTNAME,
                "asdfcom",
                VALID_PASSWORD
        ));
    }

    @Test
    @DisplayName("Should throw a business exception if the password is empty")
    void test11(){
        assertThrows(BusinessException.class,() -> User.create(
                VALID_NAME,
                VALID_LASTNAME,
                VALID_EMAIL,
                ""
        ));
    }

    @Test
    @DisplayName("Should throw a business exception if the password not have between 6 and 12 characters")
    void test12(){
        assertThrows(BusinessException.class,() -> User.create(
                VALID_NAME,
                VALID_LASTNAME,
                VALID_EMAIL,
                "12345"
        ));
    }

    @Test
    @DisplayName("Should create a valid user")
    void test13(){

        User user = User.create(VALID_NAME,VALID_LASTNAME,VALID_EMAIL,VALID_PASSWORD);

        assertEquals(VALID_NAME, user.getName());
        assertEquals(VALID_LASTNAME, user.getLastname());
        assertEquals(VALID_EMAIL, user.getEmail());
    }
}