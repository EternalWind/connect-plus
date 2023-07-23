package io.eternalwind.connectplus.domain.services;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.UUID;

import org.apache.commons.lang3.tuple.Pair;
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
        final var userId = UUID.randomUUID();
        when(user.getId()).thenReturn(userId.toString());
        when(userRepository.findByUsername("username")).thenReturn(Mono.just(user));

        assertThat(authenticationService.authenticate("username", "token").block())
                .isEqualTo(Pair.of(userId, "fake_token"));
    }
}
