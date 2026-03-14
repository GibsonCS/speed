package br.com.codelift.speed.domain;

import br.com.codelift.speed.exception.BusinessException;

import java.util.Set;
import java.util.UUID;

public class User {

    private final UUID id;
    private final String name;
    private final String lastname;
    private final String email;
    private final String encryptedPassword;
    private final Set<UUID> roleIds;

    private User(UUID id, String name, String lastname, String email, String password, Set<UUID> roleIds) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.encryptedPassword = password;
        this.roleIds = roleIds;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public Set<UUID> getRoleIds() {
        return roleIds;
    }

    public static User create(UUID id, String name, String lastname, String email, String password, Set<UUID> roleIds) {
        validateName(name);
        validateLastName(lastname);
        validateEmail(email);
        validatePassword(password);

        return new User(id, name, lastname, email, password, roleIds);
    }

    public void addRole(UUID roleId) {
        if (this.roleIds.contains(roleId)) throw new BusinessException("Role already exists");

        this.roleIds.add(roleId);
    }

    private static void validateName(String name) {
        if (name == null || name.isBlank()) throw new BusinessException("Name cannot be null");
        if (name.length() < 3) throw new BusinessException("Name cannot be less than 3 characters");
        if (name.length() > 20) throw new BusinessException("Name cannot be more than 20 characters");

        if (!name.matches("^[a-zA-ZÀ-ÿ ]+$")) {
            throw new BusinessException("Name cannot contain special characters");
        }
    }

    private static void validateLastName(String lastname) {
        if (lastname == null || lastname.isBlank()) throw new BusinessException("Lastname cannot be null");
        if (lastname.length() < 3) throw new BusinessException("Lastname cannot be less than 3 characters");
        if (lastname.length() > 20) throw new BusinessException("Lastname cannot be more than 20 characters");

        if (!lastname.matches("^[a-zA-ZÀ-ÿ ]+$")) {
            throw new BusinessException("Lastname cannot contain special characters");
        }
    }

    private static void validateEmail(String email) {
        if (email == null || email.isBlank()) throw new BusinessException("Email cannot be empty");
        if (!email.matches("^(.+)@(.+)$")) throw new BusinessException("Invalid email");
    }

    private static void validatePassword(String password) {
        if (password == null || password.isBlank()) throw new BusinessException("password cannot be empty");

        if (password.length() < 6 || password.length() > 12) {
            throw new BusinessException("password cannot be between 6 and 12 characters");
        }
    }
}
