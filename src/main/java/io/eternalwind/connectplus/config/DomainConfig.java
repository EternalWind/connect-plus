package io.eternalwind.connectplus.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.eternalwind.connectplus.domain.services.AuthenticationService;
import io.eternalwind.connectplus.persistence.repositories.UserRepository;

@Configuration
public class DomainConfig {
    @Bean
    public AuthenticationService authenticationService(UserRepository userRepository) {
        return new AuthenticationService(userRepository);
    }
}
