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

    public String getPassword() {
        return password;
    }

    public static User create(String name, String lastname, String email, String password) {
        validateName(name);
        validateLastName(lastname);
        validateEmail(email);
        validatePassword(password);

        return new User(name, lastname, email, password);
    }

    private static void validateName(String name) {
        if (name.isEmpty()) throw new BusinessException("Name not must be null");
        if (name.length() < 3) throw new BusinessException("Name not must be less than 3 characters");
        if (name.length() > 20) throw new BusinessException("Name not must be more than 20 characters");

        if (!name.matches("^[a-zA-Z]*$")) {
            throw new BusinessException("Name should not contain special characters");
        }
    }

    private static void validateLastName(String lastname) {
        if (lastname.isEmpty()) throw new BusinessException("Lastname not must be null");
        if (lastname.length() < 3) throw new BusinessException("Lastname not must be less than 3 characters");
        if (lastname.length() > 20) throw new BusinessException("Lastname not must be more than 20 characters");

        if (!lastname.matches("^[a-zA-Z]*$")) {
            throw new BusinessException("Lastname should not contain special characters");
        }
    }

    private static void validateEmail(String email) {
        if (email.isEmpty()) throw new BusinessException("Email should not be empty");
        if (!email.matches("^(.+)@(.+)$")) throw new BusinessException("Invalid email");
    }

    private static void validatePassword(String password) {
        if (password.isEmpty()) throw new BusinessException("password should not be empty");

        if (password.length() < 6 || password.length() > 12) {
            throw new BusinessException("password must be between 6 and 12 characters");
        }
    }
}
