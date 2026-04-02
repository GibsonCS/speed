package br.com.codelift.speed.infrastructure.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.UUID;

@Entity
@Table(name = "roles")
@Data
public class RoleEntity {

    @Id
    private UUID id;
    private String name;

    public RoleEntity() {
    }

    public RoleEntity(String name, UUID id) {
        this.name = name;
        this.id = id;
    }
}
