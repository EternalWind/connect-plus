package io.eternalwind.connectplus.persistence.repositories;

import com.google.cloud.spring.data.firestore.FirestoreReactiveRepository;

import io.eternalwind.connectplus.persistence.dao.MessageAcknowledge;
import reactor.core.publisher.Flux;

public interface MessageAcknowledgeRepository extends FirestoreReactiveRepository<MessageAcknowledge> {
    Flux<MessageAcknowledge> findByUserId(String userId);
}
