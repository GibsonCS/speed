package br.com.codelift.speed.core.usecase;

import br.com.codelift.speed.core.domain.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DeleteUser {

    private final UserRepository userRepository;

    public DeleteUser(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void execute(UUID userId) {

        userRepository.findById(userId).ifPresent(_user -> userRepository.delete(userId));
        
    }

}
