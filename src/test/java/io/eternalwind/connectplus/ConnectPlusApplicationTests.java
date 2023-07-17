package io.eternalwind.connectplus;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.FirestoreEmulatorContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import com.google.api.gax.core.CredentialsProvider;
import com.google.api.gax.core.NoCredentialsProvider;

@SpringBootTest
@Testcontainers
class ConnectPlusApplicationTests {

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

	@Container
	private static final FirestoreEmulatorContainer EMULATOR = new FirestoreEmulatorContainer(
			DockerImageName.parse("gcr.io/google.com/cloudsdktool/google-cloud-cli:380.0.0-emulators"));
				
	@DynamicPropertySource
	private static void emulatorProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.cloud.gcp.firestore.host-port", EMULATOR::getEmulatorEndpoint);
    }

	@Test
	void contextLoads() {
	}

}
