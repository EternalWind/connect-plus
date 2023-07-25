package io.eternalwind.connectplus.domain.services;

import java.util.List;
import java.util.UUID;

import io.eternalwind.connectplus.domain.models.MatchStatus;
import io.eternalwind.connectplus.persistence.dao.Match;
import io.eternalwind.connectplus.persistence.dao.User;
import io.eternalwind.connectplus.persistence.repositories.MatchRepository;
import io.eternalwind.connectplus.persistence.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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

    public Mono<Match> initiateMatch(UUID initUserId, UUID receivingUser) {
        final var initUserIdStr = initUserId.toString();
        final var receivingUserIdStr = receivingUser.toString();

        final var matchInitByReceiver = matchRepository
                .findByInitUserIdAndReceivingUserId(receivingUserIdStr, initUserIdStr)
                .flatMap(match -> {
                    final var matched = match.toBuilder().matchStatus(MatchStatus.MATCHED).build();
                    return matchRepository.save(matched);
                });

        final var matchInitBySelf = matchRepository
                .findByInitUserIdAndReceivingUserId(initUserIdStr, receivingUserIdStr);

        final var newMatch = Mono.from(matchRepository.save(Match.builder()
                .initUserId(initUserIdStr)
                .receivingUserId(receivingUserIdStr)
                .matchStatus(MatchStatus.PENDING)
                .build()));

        return matchInitByReceiver.switchIfEmpty(matchInitBySelf.switchIfEmpty(newMatch));
    }
    
    public Flux<User> getMatchedUsers(UUID userId) {
        final var userIdStr = userId.toString();
        final var matches = Flux.merge(matchRepository.findByInitUserId(userIdStr), matchRepository.findByReceivingUserId(userIdStr))
                .filter(match -> match.getMatchStatus().equals(MatchStatus.MATCHED));
                
        final var matchedUserIds = matches
                .flatMapIterable(match -> List.of(match.getInitUserId(), match.getReceivingUserId()))
                .distinct();

        return matchedUserIds.flatMap(userRepository::findById);
    }
}
