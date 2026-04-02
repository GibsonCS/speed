package br.com.codelift.speed.core.usecase;

import br.com.codelift.speed.core.domain.entity.User;
import br.com.codelift.speed.core.domain.repository.UserRepository;
import br.com.codelift.speed.exception.BusinessException;
import br.com.codelift.speed.infrastructure.web.dto.UserRequest;
import br.com.codelift.speed.infrastructure.web.dto.UserResponse;

import java.util.UUID;

public class CreateUser {

    UserRepository userRepository;

    public CreateUser(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponse execute(UserRequest userRequest) {

        if (userRepository.findByEmail(userRequest.email()).isPresent()) {
            throw new BusinessException("User not exists");
        }

        UUID id = UUID.randomUUID();
        UUID roleId = UUID.fromString("318df173-32e1-4865-b380-e3f2343955b4");

        User user = User.create(
                id,
                userRequest.name(),
                userRequest.lastname(),
                userRequest.email(),
                userRequest.password(),
                roleId
        );

        userRepository.save(user);

        return new UserResponse(user.getId().getValue());
    }
}
