package io.eternalwind.connectplus.persistence.repositories;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.FirestoreEmulatorContainer;
import org.testcontainers.junit.jupiter.Container;

import io.eternalwind.connectplus.persistence.dao.User;

public class UserRepositoryTest extends FirestoreRepositoryTestBase<UserRepository> {
    @Container
    private static final FirestoreEmulatorContainer EMULATOR = createFirestoreEmulatorContainer();

    @DynamicPropertySource
    private static void emulatorProperties(DynamicPropertyRegistry registry) {
        configureFirestoreEmulator(registry, EMULATOR);
    }

    @Test
    void testFindByUsername() {
        var user = User.builder().id(UUID.randomUUID().toString()).username("username").build();
        repository.save(user).block();

        var actual = repository.findByUsername("username").block();
        assertThat(actual).isEqualTo(user);
    }
}
