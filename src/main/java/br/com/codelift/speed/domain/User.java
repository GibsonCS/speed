package br.com.codelift.speed.domain;

import br.com.codelift.speed.exception.BusinessException;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public class User {

    @Id
    private UUID id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String lastname;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;

    protected User() {
    }

    private User(String name, String lastname, String email, String password) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
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

    public static User create(String name, String lastname, String email, String password) {
        validateName(name);
        validateLastName(lastname);
        validateEmail(email);
        validatePassword(password);

        return new User(name, lastname, email, password);
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
