package io.eternalwind.connectplus.presentation.controllers;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/user")
public class UserController {
    @PutMapping("/uploadAvatar")
    public Mono<Void> uploadAvatar(@RequestParam("avatar") MultipartFile avatar) {
        return Mono.empty();
    }
}
