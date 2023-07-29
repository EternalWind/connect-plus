package io.eternalwind.connectplus.persistence.repositories;

import com.google.cloud.spring.data.firestore.FirestoreReactiveRepository;

import io.eternalwind.connectplus.persistence.dao.ForumMembership;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface ForumMembershipRepository extends FirestoreReactiveRepository<ForumMembership> {
    Flux<ForumMembership> findByForumId(String forumId);

    Flux<ForumMembership> findByMemberId(String memberId);

    Mono<ForumMembership> findByForumIdAndMemberId(String forumId, String memberId);
}
