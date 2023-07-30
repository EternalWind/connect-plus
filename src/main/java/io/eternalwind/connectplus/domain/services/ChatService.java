package io.eternalwind.connectplus.domain.services;

import java.util.UUID;

import com.google.cloud.Timestamp;

import io.eternalwind.connectplus.domain.models.MessageType;
import io.eternalwind.connectplus.persistence.dao.Message;
import io.eternalwind.connectplus.persistence.dao.MessageAcknowledge;
import io.eternalwind.connectplus.persistence.repositories.ForumMembershipRepository;
import io.eternalwind.connectplus.persistence.repositories.MessageAcknowledgeRepository;
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
    private final MessageAcknowledgeRepository messageAcknowledgeRepository;

    public Mono<Message> sendMessageToUser(String msg, UUID senderId, UUID receiverId) {
        final var senderIdStr = senderId.toString();
        final var receiverIdStr = receiverId.toString();

        final var isMatched = matchService.isMatchedWith(senderId, receiverId).filter(t -> t);

        return isMatched.flatMap(r -> storeUserMessage(Message.builder()
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

        return isMember.flatMap(r -> storeForumMessage(
                Message.builder()
                        .msg(msg)
                        .senderId(senderIdStr)
                        .receiverId(forumIdStr)
                        .timestamp(Timestamp.now())
                        .messageType(MessageType.FORUM)
                        .build()))
                .switchIfEmpty(Mono.error(new NotMemberException()));
    }

    public Flux<Message> getLatestMessages(UUID receiverId) {
        return messageAcknowledgeRepository.findByUserId(receiverId.toString())
                .flatMap(
                        ack -> messageAcknowledgeRepository.delete(ack).then(messageRepository.findById(ack.getMessageId())));
    }

    private Mono<Message> storeUserMessage(Message message) {
        return messageRepository.save(message)
                .flatMap(savedMessage -> messageAcknowledgeRepository.save(MessageAcknowledge.builder()
                        .messageId(savedMessage.getId())
                        .userId(savedMessage.getReceiverId())
                        .build()).thenReturn(savedMessage));
    }

    private Mono<Message> storeForumMessage(Message message) {
        return messageRepository.save(message)
                .flatMap(savedMessage -> forumMembershipRepository.findByForumId(message.getReceiverId())
                        .filter(membership -> !membership.getMemberId().equals(message.getSenderId()))
                        .flatMap(membership -> messageAcknowledgeRepository.save(MessageAcknowledge.builder()
                                .messageId(savedMessage.getId())
                                .userId(membership.getMemberId())
                                .build()))
                        .then(Mono.just(savedMessage)));
    }
}
