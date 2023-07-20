package io.eternalwind.connectplus.persistence.repositories;

import com.google.cloud.spring.data.firestore.FirestoreReactiveRepository;

import io.eternalwind.connectplus.persistence.dao.User;
import reactor.core.publisher.Mono;

public interface UserRepository extends FirestoreReactiveRepository<User> {
    Mono<User> findByUsername(String username);
}
