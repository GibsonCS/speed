package br.com.codelift.speed.core.usecase;

import br.com.codelift.speed.core.domain.entity.User;
import br.com.codelift.speed.core.domain.repository.RoleRepository;
import br.com.codelift.speed.core.domain.repository.UserRepository;
import br.com.codelift.speed.exception.BusinessException;
import br.com.codelift.speed.infrastructure.web.dto.UserRequest;
import br.com.codelift.speed.infrastructure.web.dto.UserResponse;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
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

        Set<UUID> roleIds = new HashSet<>();

        roleRepository.findByName("USER").ifPresent(r -> {
            roleIds.add(r.getId());
        });

        User user = User.create(
                UUID.randomUUID(),
                userRequest.name(),
                userRequest.lastname(),
                userRequest.email(),
                userRequest.password(),
                roleIds
        );

        userRepository.save(user);

        return new UserResponse(user.getId().getValue());
    }
}
