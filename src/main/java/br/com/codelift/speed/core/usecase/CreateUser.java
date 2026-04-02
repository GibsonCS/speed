package br.com.codelift.speed.core.usecase;

import br.com.codelift.speed.core.domain.entity.User;
import br.com.codelift.speed.core.domain.repository.RoleRepository;
import br.com.codelift.speed.core.domain.repository.UserRepository;
import br.com.codelift.speed.exception.BusinessException;
import br.com.codelift.speed.infrastructure.web.dto.UserRequest;
import br.com.codelift.speed.infrastructure.web.dto.UserResponse;

import java.util.UUID;

public class CreateUser {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public CreateUser(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public UserResponse execute(UserRequest userRequest) {

        if (userRepository.findByEmail(userRequest.email()).isPresent()) {
            throw new BusinessException("User not exists");
        }

        UUID id = UUID.randomUUID();
        UUID roleId = roleRepository.findByName("USER").get().getId();

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
