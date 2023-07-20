package io.eternalwind.connectplus.presentation.controllers;

import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.eternalwind.connectplus.persistence.repositories.UserRepository;
import io.eternalwind.connectplus.presentation.mappers.UserMapper;
import io.eternalwind.connectplus.presentation.viewmodels.GetUserVMs.UserInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserRepository userRepository;
private final UserMapper userMapper;

    @PutMapping("/uploadAvatar")
    public Mono<Void> uploadAvatar(@RequestParam("avatar") MultipartFile avatar) {
        return Mono.empty();
    }

    @GetMapping("/{id}")
    public Mono<UserInfo> getUser(@PathVariable("id") UUID id) {
        log.info("get user with {}", id);
        return userRepository.findById(id.toString()).map(userMapper::toUserInfo);
    }
}
