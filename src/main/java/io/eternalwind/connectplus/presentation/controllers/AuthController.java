package io.eternalwind.connectplus.presentation.controllers;

import java.util.UUID;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.eternalwind.connectplus.domain.services.AuthenticationService;
import io.eternalwind.connectplus.domain.services.UserNotExistException;
import io.eternalwind.connectplus.persistence.repositories.UserRepository;
import io.eternalwind.connectplus.presentation.mappers.UserMapper;
import io.eternalwind.connectplus.presentation.viewmodels.LoginVMs.LoggedInUser;
import io.eternalwind.connectplus.presentation.viewmodels.LoginVMs.LoginUser;
import io.eternalwind.connectplus.presentation.viewmodels.SignUpVMs.NewUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {
    private final AuthenticationService authenticationService;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @PostMapping("/signUp")
    public Mono<Void> signUp(@RequestBody NewUser newUser) {
        log.info("sign up with {}", newUser);
        final var user = userMapper.fromNewUser(newUser).toBuilder().id(UUID.randomUUID().toString()).build();
        return userRepository.save(user).then();
    }

    @PostMapping("/login")
    public Mono<LoggedInUser> login(@RequestBody LoginUser loginUser) {
        log.info("login with {}", loginUser);

        try {
            return authenticationService.authenticate(loginUser.username(), loginUser.ppkEncryptedUsername())
                .map(userMapper::toLoggedInUser);
        } catch (UserNotExistException e) {
            log.error("user {} not found", loginUser.username());
            return Mono.just(LoggedInUser.NO_USER);
        }
    }

    @PostMapping("/logout")
    public Mono<Void> logout() {
        return Mono.empty();
    }
}
