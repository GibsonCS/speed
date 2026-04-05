package br.com.codelift.speed.infrastructure.web.controller;


import br.com.codelift.speed.core.domain.entity.User;
import br.com.codelift.speed.core.usecase.CreateUser;
import br.com.codelift.speed.core.usecase.DeleteUser;
import br.com.codelift.speed.core.usecase.GetUser;
import br.com.codelift.speed.infrastructure.web.dto.CreateUserRequest;
import br.com.codelift.speed.infrastructure.web.dto.CreateUserResponse;
import br.com.codelift.speed.infrastructure.web.dto.GetUserResponse;
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
    private final GetUser getUser;

    public UserController(CreateUser createUser, DeleteUser deleteUser, GetUser getUser) {
        this.createUser = createUser;
        this.deleteUser = deleteUser;
        this.getUser = getUser;
    }

    @PostMapping
    public ResponseEntity<CreateUserResponse> handleCreateUser(@RequestBody @Valid CreateUserRequest createUserRequest) {

        CreateUserResponse userCreated = createUser.execute(createUserRequest);

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

    @GetMapping("/{id}")
    public ResponseEntity<GetUserResponse> handleGetUser(@PathVariable UUID id) {

        User user = getUser.execute(id);

        return ResponseEntity
                .ok()
                .body(GetUserResponse.mapperToGetUserResponse(user));
    }
}
