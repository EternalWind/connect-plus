package io.eternalwind.connectplus.domain.services;

import java.util.UUID;

import com.google.cloud.Timestamp;

import io.eternalwind.connectplus.domain.models.MessageType;
import io.eternalwind.connectplus.persistence.dao.Message;
import io.eternalwind.connectplus.persistence.repositories.ForumMembershipRepository;
import io.eternalwind.connectplus.persistence.repositories.MessageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Slf4j
public class ChatService {
    private final MatchService matchService;
    private final MessageRepository messageRepository;
    private final ForumMembershipRepository forumMembershipRepository;

    public Mono<Message> sendMessageToUser(String msg, UUID senderId, UUID receiverId) {
        final var senderIdStr = senderId.toString();
        final var receiverIdStr = receiverId.toString();

        final var isMatched = matchService.isMatchedWith(senderId, receiverId).filter(t -> t);

        return isMatched.flatMap(r -> messageRepository.save(
                Message.builder()
                        .msg(msg)
                        .senderId(senderIdStr)
                        .receiverId(receiverIdStr)
                        .timestamp(Timestamp.now())
                        .messageType(MessageType.USER)
                        .build()))
                .switchIfEmpty(Mono.error(new UserNotExistException()));
    }

    public Mono<Message> sendMessageToForum(String msg, UUID senderId, UUID forumId) {
        final var senderIdStr = senderId.toString();
        final var forumIdStr = forumId.toString();

        final var isMember = forumMembershipRepository.findByForumIdAndMemberId(forumIdStr, senderIdStr);

        return isMember.flatMap(r -> messageRepository.save(
                Message.builder()
                        .msg(msg)
                        .senderId(senderIdStr)
                        .receiverId(forumIdStr)
                        .timestamp(Timestamp.now())
                        .messageType(MessageType.FORUM)
                        .build()))
                .switchIfEmpty(Mono.error(new NotMemberException()));
    }

    public Flux<Message> getLatestMessages(UUID receiverId, MessageType messageType) {
        return messageRepository.findByReceiverIdAndMessageType(receiverId.toString(), messageType)
                .flatMap(message -> messageRepository.delete(message).thenReturn(message));
    }
}
