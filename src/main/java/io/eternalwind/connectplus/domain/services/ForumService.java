package io.eternalwind.connectplus.domain.services;

import java.util.UUID;

import com.google.cloud.Timestamp;

import io.eternalwind.connectplus.persistence.dao.Forum;
import io.eternalwind.connectplus.persistence.dao.ForumMembership;
import io.eternalwind.connectplus.persistence.repositories.ForumMembershipRepository;
import io.eternalwind.connectplus.persistence.repositories.ForumRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class ForumService {
    private final ForumRepository forumRepository;
    private final ForumMembershipRepository forumMembershipRepository;

    public Mono<Forum> join(UUID userId, UUID forumId) {
        final var userIdStr = userId.toString();
        final var forumIdStr = forumId.toString();

        final var isForumExist = forumRepository.findById(forumIdStr);

        final var joinForum = forumMembershipRepository.findByForumIdAndMemberId(forumIdStr, userIdStr)
                .switchIfEmpty(forumMembershipRepository.save(ForumMembership.builder()
                        .id(UUID.randomUUID().toString())
                        .forumId(forumIdStr)
                        .memberId(userIdStr)
                        .build()))
                .flatMap(membership -> forumRepository.findById(membership.getForumId()));

        return isForumExist.flatMap(unused -> joinForum).switchIfEmpty(Mono.error(new ForumNotExistException()));
    }

    public Mono<Forum> create(Forum forum, UUID creatorId) {
        return forumRepository.save(forum.toBuilder().id(UUID.randomUUID().toString()).createdOn(Timestamp.now()).build())
                .flatMap(createdForum -> forumMembershipRepository.save(ForumMembership.builder()
                        .id(UUID.randomUUID().toString())
                        .forumId(createdForum.getId())
                        .memberId(creatorId.toString())
                        .build())
                        .thenReturn(createdForum));
    }
}
