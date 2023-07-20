package io.eternalwind.connectplus.domain.services;

import io.eternalwind.connectplus.persistence.dao.User;
import io.eternalwind.connectplus.persistence.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;

    public Mono<User> authenticate(String username, String authToken) {
        return userRepository.findByUsername(username).doOnSuccess(user -> authenticate(user, authToken));
    }

    private void authenticate(User user, String authToken) {
        if (user != null) {

        } else {
            throw new UserNotExistException();
        }
    }
}
