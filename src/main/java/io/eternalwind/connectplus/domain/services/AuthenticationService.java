package io.eternalwind.connectplus.domain.services;

import java.util.UUID;

import org.apache.commons.lang3.tuple.Pair;

import io.eternalwind.connectplus.persistence.dao.User;
import io.eternalwind.connectplus.persistence.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;

    public Mono<Pair<UUID, String>> authenticate(String username, String authToken) {
        return userRepository.findByUsername(username)
                .map(user -> Pair.of(UUID.fromString(user.getId()), authenticate(user, authToken)))
                .switchIfEmpty(Mono.error(new UserNotExistException()));
    }

    private String authenticate(User user, String authToken) {
        return "fake_token";
    }
}
