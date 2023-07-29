package io.eternalwind.connectplus.persistence.repositories;

import com.google.cloud.spring.data.firestore.FirestoreReactiveRepository;

import io.eternalwind.connectplus.persistence.dao.Forum;

public interface ForumRepository extends FirestoreReactiveRepository<Forum> {
    
}
