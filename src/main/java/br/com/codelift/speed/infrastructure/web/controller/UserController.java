package br.com.codelift.speed.infrastructure.web.controller;


import br.com.codelift.speed.core.usecase.CreateUser;
import br.com.codelift.speed.core.usecase.DeleteUser;
import br.com.codelift.speed.infrastructure.web.dto.UserRequest;
import br.com.codelift.speed.infrastructure.web.dto.UserResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    private final CreateUser createUser;
    private final DeleteUser deleteUser;

    public UserController(CreateUser createUser, DeleteUser deleteUser) {
        this.createUser = createUser;
        this.deleteUser = deleteUser;
    }

    @PostMapping
    public ResponseEntity<UserResponse> handleCreateUser(@RequestBody @Valid UserRequest userRequest) {

        UserResponse userCreated = createUser.execute(userRequest);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(userCreated.id())
                .toUri();

        return ResponseEntity.created(location).body(userCreated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> handleDeleteUser(@PathVariable UUID id) {

        deleteUser.execute(id);

        return ResponseEntity.noContent().build();
    }
}
