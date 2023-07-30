package io.eternalwind.connectplus.presentation.controllers;

import java.util.UUID;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.eternalwind.connectplus.domain.services.ChatService;
import io.eternalwind.connectplus.domain.services.ForumService;
import io.eternalwind.connectplus.persistence.repositories.ForumRepository;
import io.eternalwind.connectplus.presentation.mappers.ForumMapper;
import io.eternalwind.connectplus.presentation.mappers.MessageMapper;
import io.eternalwind.connectplus.presentation.viewmodels.CreateForumVMs.CreatingForum;
import io.eternalwind.connectplus.presentation.viewmodels.GetLatestMsgVMs.LatestMessage;
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
    private final ForumService forumService;
    private final ForumRepository forumRepository;
    private final ForumMapper forumMapper;

    @PostMapping("/msgToUser")
    public Mono<SentMessage> msgToUser(@RequestHeader(USER_HEADER) UUID userId,
            @RequestBody SendingMessage sendingMessage) {
        log.info("sending message {}", sendingMessage);
        return chatService.sendMessageToUser(sendingMessage.msg(), userId, sendingMessage.userId())
                .map(messageMapper::toSentMessage);
    }

    @PostMapping("/getLatestMsg")
    public Flux<LatestMessage> getLatestMsg(@RequestHeader(USER_HEADER) UUID userId) {
        log.info("getting latest message for {}", userId);
        return chatService.getLatestMessages(userId).map(messageMapper::toLatestMessage);
    }

    @PostMapping("/createForum")
    public Mono<Forum> createForum(@RequestHeader(USER_HEADER) UUID userId, CreatingForum creatingForum) {
        log.info("creating forum with {} by {}", creatingForum, userId);
        return forumService.create(forumMapper.fromCreatingForum(creatingForum), userId)
                .map(forumMapper::toListForumVM);
    }

    @PostMapping("/listForum")
    public Flux<Forum> listForum() {
        log.info("list forums");
        return forumRepository.findAll().map(forumMapper::toListForumVM);
    }
    
    @PostMapping("/joinForum")
    public Mono<Void> joinForum(@RequestHeader(USER_HEADER) UUID userId, @RequestBody Join join) {
        log.info("{} joining forum {}", userId, join);
        return forumService.join(userId, join.forumId()).then();
    }

    @PostMapping("/sendMsgToForum")
    public Mono<Void> sendMsgToForum(@RequestHeader(USER_HEADER) UUID userId, @RequestBody SendingForumMessage sendingForumMessage) {
        log.info("{} sending forum message {}", userId, sendingForumMessage);
        return chatService.sendMessageToForum(sendingForumMessage.msg(), userId, sendingForumMessage.forumId()).then();
    }
}
