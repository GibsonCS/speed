package br.com.codelift.speed.domain.vo;

import java.util.Objects;

public class Email {

    private String value;

    public static Email create(String value) {

        return new Email(value);
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
