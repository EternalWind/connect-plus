package io.eternalwind.connectplus.presentation.controllers;

import java.util.UUID;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public Mono<Void> likeUser(@RequestHeader(USER_HEADER) UUID userId, @RequestBody Like like) {
        return matchService.initiateMatch(userId, like.userId()).then();
    }

    @PostMapping("/listMatchedUser")
    public Flux<MatchedUser> listMatchedUser(@RequestHeader(USER_HEADER) UUID userId) {
        return matchService.getMatchedUsers(userId).map(userMapper::toMatchedUser);
    }
    
}
