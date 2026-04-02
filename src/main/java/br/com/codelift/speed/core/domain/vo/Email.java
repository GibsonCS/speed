package br.com.codelift.speed.core.domain.vo;

import br.com.codelift.speed.exception.BusinessException;

import java.util.Objects;

public class Email {

    private final String value;

    public static Email create(String value) {
        validate(value);

        return new Email(value);
    }

    private static void validate(String email) {
        if (email == null || email.isBlank()) throw new BusinessException("Email cannot be empty");

        String regex = "^[a-zA-Z0-9]+([._-]?[a-zA-Z0-9]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z]{2,})+$";

        if (!email.matches(regex)) throw new BusinessException("Invalid email");
    }

    private Email(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Email email)) return false;
        return Objects.equals(value, email.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }

    public String getValue() {
        return value;
    }
}
