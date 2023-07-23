package io.eternalwind.connectplus.presentation.controllers;

import java.time.ZonedDateTime;
import java.util.UUID;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.eternalwind.connectplus.domain.models.Sex;
import io.eternalwind.connectplus.presentation.viewmodels.GetRecommendedUserVMs.RecommendedUser;
import io.eternalwind.connectplus.presentation.viewmodels.LikeUserVMs.Like;
import io.eternalwind.connectplus.presentation.viewmodels.ListMatchedUserVMs.MatchedUser;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/match")
public class MatchController {

    @PostMapping("/getRecommendedUser")
    public Flux<RecommendedUser> getRecommendedUser() {
        return Flux.just(
                new RecommendedUser(UUID.randomUUID(), "Alice", Sex.FEMALE, Sex.BOTH, "hi", null, 100),
                new RecommendedUser(UUID.randomUUID(), "Jarkey", Sex.MALE, Sex.MALE, "wassup", null, 9));
    }
    
    @PostMapping("/likeUser")
    public Mono<Void> likeUser(@RequestBody Like like) {
        return Mono.empty();
    }

    @PostMapping("/listMatchedUser")
    public Flux<MatchedUser> listMatchedUser() {
        return Flux.just(
                new MatchedUser(UUID.randomUUID(), "Alice", Sex.FEMALE, Sex.BOTH, "hi", null, 100, ZonedDateTime.now()),
                new MatchedUser(UUID.randomUUID(), "Jarkey", Sex.MALE, Sex.MALE, "wassup", null, 9,
                        ZonedDateTime.now()));
    }
    
}
