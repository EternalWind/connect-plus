package io.eternalwind.connectplus.controllers;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.eternalwind.connectplus.controllers.viewmodels.GetLatestMsgVMs.ForumMessage;
import io.eternalwind.connectplus.controllers.viewmodels.GetLatestMsgVMs.LatestMessages;
import io.eternalwind.connectplus.controllers.viewmodels.GetLatestMsgVMs.UserMessage;
import io.eternalwind.connectplus.controllers.viewmodels.JoinForumVMs.Join;
import io.eternalwind.connectplus.controllers.viewmodels.ListForumVMs.Forum;
import io.eternalwind.connectplus.controllers.viewmodels.MsgToUserVMs.SendingMessage;
import io.eternalwind.connectplus.controllers.viewmodels.MsgToUserVMs.SentMessage;
import io.eternalwind.connectplus.controllers.viewmodels.SendMsgToForumVMs.SendingForumMessage;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/chat")
public class ChatController {
    @PostMapping("/msgToUser")
    public Mono<SentMessage> msgToUser(@RequestBody SendingMessage sendingMessage) {
        return Mono.just(new SentMessage(ZonedDateTime.now()));
    }

    @PostMapping("/listForum")
    public Flux<Forum> listForum() {
        return Flux.just(
                new Forum(UUID.randomUUID(), "Travel", "This is a forum for traveling", ZonedDateTime.now()),
                new Forum(UUID.randomUUID(), "Food", "Let's talk about food!", ZonedDateTime.now()));
    }
    
    @PostMapping("/joinForum")
    public Mono<Void> joinForum(@RequestBody Join join) {
        return Mono.empty();
    }

    @PostMapping("/sendMsgToForum")
    public Mono<Void> sendMsgToForum(@RequestBody SendingForumMessage sendingForumMessage) {
        return Mono.empty();
    }

    @PostMapping("/getLatestMsg")
    public Mono<LatestMessages> getLatestMsg() {
        return Mono.just(
            new LatestMessages(
                List.of(
                    new UserMessage(UUID.randomUUID(), "How are you?", ZonedDateTime.now()),
                    new UserMessage(UUID.randomUUID(), "Not bad.", ZonedDateTime.now())
                ), 
                List.of(
                    new ForumMessage(UUID.randomUUID(), "Hi, everyone", ZonedDateTime.now(), "Bob"),
                    new ForumMessage(UUID.randomUUID(), "Piss off", ZonedDateTime.now(), "Alice")
                ))
        );
    }
}
