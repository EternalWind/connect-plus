package io.eternalwind.connectplus.persistence.repositories;

import com.google.cloud.spring.data.firestore.FirestoreReactiveRepository;

import io.eternalwind.connectplus.domain.models.MessageType;
import io.eternalwind.connectplus.persistence.dao.Message;
import reactor.core.publisher.Flux;


public interface MessageRepository extends FirestoreReactiveRepository<Message> {
    Flux<Message> findByReceiverIdAndMessageType(String receiverId, MessageType messageType);
}
