package io.eternalwind.connectplus.controllers;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.eternalwind.connectplus.controllers.viewmodels.LoginVMs.LoggedInUser;
import io.eternalwind.connectplus.controllers.viewmodels.LoginVMs.LoginUser;
import io.eternalwind.connectplus.controllers.viewmodels.SignUpVMs.NewUser;
import io.eternalwind.connectplus.models.Sex;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @PostMapping("/signUp")
    public Mono<Void> signUp(@RequestBody NewUser newUser) {
        return Mono.empty();
    }

    @PostMapping("/login")
    public Mono<LoggedInUser> login(@RequestBody LoginUser loginUser) {
        return Mono.just(new LoggedInUser(
                "Bob", Sex.MALE, Sex.FEMALE, "hello!", RandomStringUtils.randomAscii(32)));
    }

    @PostMapping("/logout")
    public Mono<Void> logout() {
        return Mono.empty();
    }
}
