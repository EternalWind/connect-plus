package io.eternalwind.connectplus.domain.services;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import io.eternalwind.connectplus.persistence.dao.User;
import io.eternalwind.connectplus.persistence.repositories.UserRepository;
import reactor.core.publisher.Mono;

@ExtendWith(MockitoExtension.class)
public class AuthenticationServiceTest implements WithAssertions {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private AuthenticationService authenticationService;

    @Test
    void authenticationShouldFailIfUserNotExist() {
        when(userRepository.findByUsername("username")).thenReturn(Mono.empty());
        assertThatThrownBy(() -> authenticationService.authenticate("username", "token").block()).isInstanceOf(UserNotExistException.class);
    }

    @Test
    void authenticationShouldSucceedIfUserIsFoundAndMatched() {
        final var user = mock(User.class);
        when(userRepository.findByUsername("username")).thenReturn(Mono.just(user));
        assertThatNoException().isThrownBy(() -> authenticationService.authenticate("username", "token").block());
    }
}
