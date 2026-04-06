package br.com.codelift.speed.core.usecase;

import br.com.codelift.speed.core.domain.entity.User;
import br.com.codelift.speed.core.domain.repository.RoleRepository;
import br.com.codelift.speed.core.domain.repository.UserRepository;
import br.com.codelift.speed.core.exception.BusinessException;
import br.com.codelift.speed.infrastructure.web.dto.CreateUserRequest;
import br.com.codelift.speed.infrastructure.web.dto.CreateUserResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CreateUser {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    
    public CreateUserResponse execute(CreateUserRequest createUserRequest) {

        if (userRepository.findByEmail(createUserRequest.email()).isPresent()) {
            throw new BusinessException("User not exists");
        }

        Set<UUID> roleIds = new HashSet<>();

        roleRepository.findByName("USER").ifPresent(r -> {
            roleIds.add(r.getId());
        });

        User user = User.create(
                UUID.randomUUID(),
                createUserRequest.name(),
                createUserRequest.lastname(),
                createUserRequest.email(),
                createUserRequest.password(),
                roleIds
        );

        userRepository.save(user);

        return new CreateUserResponse(user.getId().getValue());
    }
}
