package br.com.codelift.speed.core.domain.vo;

import br.com.codelift.speed.core.exception.BusinessException;

import java.util.Objects;

public class Name {
    private final String value;

    private Name(String value) {
        this.value = value;
    }

    public static Name create(String value) {
        validate(value);
        return new Name(value);
    }

    private static void validate(String name) {
        if (name == null || name.isBlank()) throw new BusinessException("Name cannot be null");
        if (name.length() < 3) throw new BusinessException("Name cannot be less than 3 characters");
        if (name.length() > 20) throw new BusinessException("Name cannot be more than 20 characters");

        if (!name.matches("^[a-zA-ZÀ-ÿ ]+$")) {
            throw new BusinessException("Name cannot contain special characters");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Name name)) return false;
        return Objects.equals(value, name.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }

    public String getValue() {
        return value;
    }
}
