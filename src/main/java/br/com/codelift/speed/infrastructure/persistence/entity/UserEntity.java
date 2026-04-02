package br.com.codelift.speed.infrastructure.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "users")
@Data
public class UserEntity {

    @Id
    private UUID id;
    private String name;
    private String lastname;
    private String email;
    private String password;

    @ManyToMany
    private Set<RoleEntity> roles;
}
