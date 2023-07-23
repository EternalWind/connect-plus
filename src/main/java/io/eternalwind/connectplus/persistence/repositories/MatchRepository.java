package io.eternalwind.connectplus.persistence.repositories;

import com.google.cloud.spring.data.firestore.FirestoreReactiveRepository;

import io.eternalwind.connectplus.persistence.dao.Match;
import reactor.core.publisher.Flux;

public interface MatchRepository extends FirestoreReactiveRepository<Match> {
    Flux<Match> findByInitUserId(String initUserId);

    Flux<Match> findByReceivingUserId(String receivingUserId);
}
