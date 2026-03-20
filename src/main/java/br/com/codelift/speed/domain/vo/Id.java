package br.com.codelift.speed.domain.vo;

import br.com.codelift.speed.exception.BusinessException;

import java.util.Objects;
import java.util.UUID;

public class Id {

    private final UUID value;
    
    private static void validate(UUID id) {
        if (id == null) throw new BusinessException("Id cannot be null");
    }

    private Id(UUID value) {
        this.value = value;
    }

    public UUID getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Id id)) return false;
        return Objects.equals(value, id.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }
}
