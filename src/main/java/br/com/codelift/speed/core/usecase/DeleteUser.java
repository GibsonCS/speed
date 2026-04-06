package br.com.codelift.speed.core.usecase;

import br.com.codelift.speed.core.domain.repository.UserRepository;
import br.com.codelift.speed.core.exception.BusinessException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class DeleteUser {

    private final UserRepository userRepository;
    
    public void execute(UUID userId) {

        userRepository.findById(userId)
                .ifPresentOrElse(_user -> userRepository.delete(userId), () -> {
                    throw new BusinessException("User not exists");
                });

    }
}
