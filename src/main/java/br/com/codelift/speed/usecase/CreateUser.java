package br.com.codelift.speed.usecase;

import br.com.codelift.speed.domain.entity.User;
import br.com.codelift.speed.domain.vo.Email;
import br.com.codelift.speed.domain.vo.Name;
import br.com.codelift.speed.usecase.infrastructure.web.dto.UserRequest;
import br.com.codelift.speed.usecase.infrastructure.web.dto.UserResponse;

import java.util.UUID;

public class CreateUser {

    public UserResponse execute(UserRequest userRequest) {

        UUID id = UUID.randomUUID();
        Name name = Name.create(userRequest.name());
        Name lastname = Name.create(userRequest.lastname());
        Email email = Email.create(userRequest.email());
        String password = userRequest.password();
        UUID roleId = UUID.fromString("318df173-32e1-4865-b380-e3f2343955b4");

        User user = User.create(id, name, lastname, email, password, roleId);

        return new UserResponse(user.getId().getValue());
    }
}
