package io.eternalwind.connectplus.domain.services;

import java.util.UUID;

import io.eternalwind.connectplus.persistence.dao.User;
import io.eternalwind.connectplus.persistence.repositories.MatchRepository;
import io.eternalwind.connectplus.persistence.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
public class MatchService {
    private final UserRepository userRepository;
    private final MatchRepository matchRepository;

    public Flux<User> getRecommendedUsers(UUID forUserId, int limit) {
        final var forUserIdStr = forUserId.toString();
        final var userInitiatedMatches = matchRepository.findByInitUserId(forUserIdStr);

        return userRepository.findAll()
                .filter(user -> !user.getId().equals(forUserIdStr))
                .filterWhen(user -> userInitiatedMatches.all(match -> !match.getReceivingUserId().equals(user.getId())))
                .take(limit);
    }
}
