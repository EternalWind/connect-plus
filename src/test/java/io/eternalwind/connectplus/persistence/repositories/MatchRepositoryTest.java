package io.eternalwind.connectplus.persistence.repositories;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.FirestoreEmulatorContainer;
import org.testcontainers.junit.jupiter.Container;

import io.eternalwind.connectplus.persistence.dao.Match;

public class MatchRepositoryTest extends FirestoreRepositoryTestBase<MatchRepository> {
    @Container
    private static final FirestoreEmulatorContainer EMULATOR = createFirestoreEmulatorContainer();

    @DynamicPropertySource
    private static void emulatorProperties(DynamicPropertyRegistry registry) {
        configureFirestoreEmulator(registry, EMULATOR);
    }

    @Test
    void testFindByInitUserId() {
        var initUserId = UUID.randomUUID().toString();

        var match1 = Match.builder()
                .id(UUID.randomUUID().toString())
                .initUserId(initUserId)
                .receivingUserId(UUID.randomUUID().toString())
                .build();

        var match2 = Match.builder()
                .id(UUID.randomUUID().toString())
                .initUserId(initUserId)
                .receivingUserId(UUID.randomUUID().toString())
                .build();

        var match3 = Match.builder()
                .id(UUID.randomUUID().toString())
                .initUserId(UUID.randomUUID().toString())
                .receivingUserId(UUID.randomUUID().toString())
                .build();

        repository.saveAll(List.of(match1, match2, match3)).collectList().block();

        var actual = repository.findByInitUserId(initUserId).map(Match::getId).collectList().block();
        assertThat(actual).containsExactlyInAnyOrder(match1.getId(), match2.getId());
    }
    
    @Test
    void testFindByReceivingUserId() {
        var receivingUserId = UUID.randomUUID().toString();

        var match1 = Match.builder()
                .id(UUID.randomUUID().toString())
                .initUserId(UUID.randomUUID().toString())
                .receivingUserId(receivingUserId)
                .build();

        var match2 = Match.builder()
                .id(UUID.randomUUID().toString())
                .initUserId(UUID.randomUUID().toString())
                .receivingUserId(receivingUserId)
                .build();

        var match3 = Match.builder()
                .id(UUID.randomUUID().toString())
                .initUserId(UUID.randomUUID().toString())
                .receivingUserId(UUID.randomUUID().toString())
                .build();

        repository.saveAll(List.of(match1, match2, match3)).collectList().block();

        var actual = repository.findByReceivingUserId(receivingUserId).map(Match::getId).collectList().block();
        assertThat(actual).containsExactlyInAnyOrder(match1.getId(), match2.getId());
    }
}
