package io.eternalwind.connectplus.domain.services;

import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.UUID;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import io.eternalwind.connectplus.persistence.dao.Match;
import io.eternalwind.connectplus.persistence.dao.User;
import io.eternalwind.connectplus.persistence.repositories.MatchRepository;
import io.eternalwind.connectplus.persistence.repositories.UserRepository;
import reactor.core.publisher.Flux;

@ExtendWith(MockitoExtension.class)
public class MatchServiceTest implements WithAssertions {
    @Mock
    private UserRepository userRepository;

    @Mock
    private MatchRepository matchRepository;

    @InjectMocks
    private MatchService matchService;

    @Test
    void testGetRecommendedUsers() {
        var forUserId = UUID.randomUUID();

        var userId1 = UUID.randomUUID().toString();
        var userId2 = UUID.randomUUID().toString();
        var userId3 = UUID.randomUUID().toString();

        var match = mock(Match.class);
        when(match.getReceivingUserId()).thenReturn(userId1);
        when(matchRepository.findByInitUserId(forUserId.toString())).thenReturn(Flux.just(match));

        var user1 = mock(User.class);
        when(user1.getId()).thenReturn(userId1);

        var user3 = mock(User.class);
        when(user3.getId()).thenReturn(userId2);

        var user4 = mock(User.class);
        lenient().when(user4.getId()).thenReturn(userId3);

        var user2 = mock(User.class);
        when(user2.getId()).thenReturn(forUserId.toString());

        when(userRepository.findAll()).thenReturn(Flux.just(user1, user2, user3, user4));

        var actual = matchService.getRecommendedUsers(forUserId, 1).collectList().block();
        assertThat(actual).containsExactlyInAnyOrder(user3);
    }
}
