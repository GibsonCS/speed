package br.com.codelift.speed.core.usecase;

import br.com.codelift.speed.core.domain.entity.User;
import br.com.codelift.speed.core.domain.repository.UserRepository;
import br.com.codelift.speed.core.exception.BusinessException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UpdateUser {

    private final UserRepository userRepository;

    public User execute(User user) {

        Optional<User> userFounded = userRepository.findById(user.getId().getValue());

        if (userFounded.isEmpty()) {
            throw new BusinessException("User not found");
        }

        userFounded.ifPresent(u -> {
            u.updateName(user.getName().getValue());
            u.updateLastname(user.getLastname().getValue());
            u.updateEmail(user.getEmail().getValue());

        });

        userRepository.save(userFounded.get());

        return userFounded.get();
    }
}
