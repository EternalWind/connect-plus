package io.eternalwind.connectplus.persistence.repositories;

import org.assertj.core.api.WithAssertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.testcontainers.containers.FirestoreEmulatorContainer;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import com.google.api.gax.core.CredentialsProvider;
import com.google.api.gax.core.NoCredentialsProvider;


@SpringBootTest
@Testcontainers
public abstract class FirestoreRepositoryTestBase<R> implements WithAssertions {
    @TestConfiguration
    private static class EmulatorConfiguration {
        // By default, autoconfiguration will initialize application default
        // credentials.
        // For testing purposes, don't use any credentials. Bootstrap w/
        // NoCredentialsProvider.
        @Bean
        CredentialsProvider googleCredentials() {
            return NoCredentialsProvider.create();
        }
    }

    @Autowired
    protected R repository;

    protected static FirestoreEmulatorContainer createFirestoreEmulatorContainer() {
        return new FirestoreEmulatorContainer(
                DockerImageName.parse("gcr.io/google.com/cloudsdktool/google-cloud-cli:380.0.0-emulators"));
    }

    protected static void configureFirestoreEmulator(DynamicPropertyRegistry registry,
            FirestoreEmulatorContainer emulator) {
        registry.add("spring.cloud.gcp.firestore.host-port", emulator::getEmulatorEndpoint);
    }
}
