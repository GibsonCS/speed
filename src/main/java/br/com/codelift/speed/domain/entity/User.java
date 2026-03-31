package br.com.codelift.speed.domain.entity;

import br.com.codelift.speed.domain.vo.Email;
import br.com.codelift.speed.domain.vo.Id;
import br.com.codelift.speed.domain.vo.Name;
import br.com.codelift.speed.exception.BusinessException;

import java.util.Set;
import java.util.UUID;

public class User {

    private final Id id;
    private final Name name;
    private final Name lastname;
    private final Email email;
    private final String encryptedPassword;
    private final Set<UUID> roleIds;

    public static User create(
            UUID id,
            Name name,
            Name lastname,
            Email email,
            String password,
            Set<UUID> roleIds
    ) {
        validatePassword(password);

        return new User(
                Id.create(id),
                name,
                lastname,
                email,
                password,
                roleIds
        );
    }

    private static void validatePassword(String password) {
        if (password == null || password.isBlank()) {
            throw new BusinessException("password cannot be empty");
        }

        if (password.length() < 6 || password.length() > 12) {
            throw new BusinessException("password cannot be between 6 and 12 characters");
        }
    }

    public void addRole(UUID roleId) {
        if (this.roleIds.contains(roleId)) {
            throw new BusinessException("Role already exists");
        }
        this.roleIds.add(roleId);
    }

    private User(
            Id id,
            Name name,
            Name lastname,
            Email email,
            String password,
            Set<UUID> roleIds
    ) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.encryptedPassword = password;
        this.roleIds = roleIds;
    }

    public Id getId() {
        return id;
    }

    public Name getName() {
        return name;
    }

    public Name getLastname() {
        return lastname;
    }

    public Email getEmail() {
        return email;
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public Set<UUID> getRoleIds() {
        return roleIds;
    }
}
