package io.eternalwind.connectplus.presentation.controllers;

import java.time.Instant;
import java.util.UUID;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.eternalwind.connectplus.domain.models.MessageType;
import io.eternalwind.connectplus.domain.services.ChatService;
import io.eternalwind.connectplus.persistence.repositories.ForumRepository;
import io.eternalwind.connectplus.presentation.mappers.ForumMapper;
import io.eternalwind.connectplus.presentation.mappers.MessageMapper;
import io.eternalwind.connectplus.presentation.viewmodels.GetLatestMsgVMs.ForumMessage;
import io.eternalwind.connectplus.presentation.viewmodels.GetLatestMsgVMs.UserMessage;
import io.eternalwind.connectplus.presentation.viewmodels.JoinForumVMs.Join;
import io.eternalwind.connectplus.presentation.viewmodels.ListForumVMs.Forum;
import io.eternalwind.connectplus.presentation.viewmodels.MsgToUserVMs.SendingMessage;
import io.eternalwind.connectplus.presentation.viewmodels.MsgToUserVMs.SentMessage;
import io.eternalwind.connectplus.presentation.viewmodels.SendMsgToForumVMs.SendingForumMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/chat")
@RequiredArgsConstructor
@Slf4j
public class ChatController extends BaseController {
    private final ChatService chatService;
    private final MessageMapper messageMapper;
    private final ForumRepository forumRepository;
    private final ForumMapper forumMapper;

    @PostMapping("/msgToUser")
    public Mono<SentMessage> msgToUser(@RequestHeader(USER_HEADER) UUID userId,
            @RequestBody SendingMessage sendingMessage) {
        log.info("sending message {}", sendingMessage);
        return chatService.sendMessageToUser(sendingMessage.msg(), userId, sendingMessage.userId())
                .map(messageMapper::toSentMessage);
    }

    @PostMapping("/getLatestUserMsg")
    public Flux<UserMessage> getLatestUserMsg(@RequestHeader(USER_HEADER) UUID userId) {
        log.info("getting latest user message for {}", userId);
        return chatService.getLatestMessages(userId, MessageType.USER).map(messageMapper::toUserMessage);
    }

    @PostMapping("/listForum")
    public Flux<Forum> listForum() {
        return forumRepository.findAll().map(forumMapper::toListForumVM);
    }
    
    @PostMapping("/joinForum")
    public Mono<Void> joinForum(@RequestHeader(USER_HEADER) UUID userId, @RequestBody Join join) {
        return Mono.empty();
    }

    @PostMapping("/sendMsgToForum")
    public Mono<Void> sendMsgToForum(@RequestBody SendingForumMessage sendingForumMessage) {
        return Mono.empty();
    }

    @PostMapping("/getLatestForumMsg")
    public Flux<ForumMessage> getLatestForumMsg(@RequestHeader(USER_HEADER) UUID userId) {
        return Flux.just(
            new ForumMessage(UUID.randomUUID(), "Hey!", Instant.now(), "Jin")
        );
    }
}
