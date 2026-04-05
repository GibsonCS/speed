package br.com.codelift.speed.core.usecase;

import br.com.codelift.speed.core.domain.entity.User;
import br.com.codelift.speed.core.domain.repository.UserRepository;
import br.com.codelift.speed.core.exception.BusinessException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class GetUser {

    private final UserRepository userRepository;

    public GetUser(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public User execute(UUID userId) {

        Optional<User> userFounded = userRepository.findById(userId);

        if (userFounded.isEmpty()) {
            throw new BusinessException("User not founded");
        }
        
        return userFounded.get();
    }
}
