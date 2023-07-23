package io.eternalwind.connectplus.presentation.controllers;

import java.time.ZonedDateTime;
import java.util.UUID;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.eternalwind.connectplus.domain.models.Sex;
import io.eternalwind.connectplus.domain.services.MatchService;
import io.eternalwind.connectplus.presentation.mappers.UserMapper;
import io.eternalwind.connectplus.presentation.viewmodels.GetRecommendedUserVMs.RecommendedUser;
import io.eternalwind.connectplus.presentation.viewmodels.LikeUserVMs.Like;
import io.eternalwind.connectplus.presentation.viewmodels.ListMatchedUserVMs.MatchedUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/match")
@RequiredArgsConstructor
@Slf4j
public class MatchController extends BaseController {
    private final MatchService matchService;
    private final UserMapper userMapper;

    @PostMapping("/getRecommendedUser")
    public Flux<RecommendedUser> getRecommendedUser(@RequestHeader(USER_HEADER) UUID userId) {
        log.info("get recommended users for {}", userId);
        return matchService.getRecommendedUsers(userId, 10).map(userMapper::toRecommendedUser);
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
