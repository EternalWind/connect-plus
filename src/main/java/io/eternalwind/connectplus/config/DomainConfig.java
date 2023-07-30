package io.eternalwind.connectplus.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.eternalwind.connectplus.domain.services.AuthenticationService;
import io.eternalwind.connectplus.domain.services.ChatService;
import io.eternalwind.connectplus.domain.services.ForumService;
import io.eternalwind.connectplus.domain.services.MatchService;
import io.eternalwind.connectplus.persistence.repositories.ForumMembershipRepository;
import io.eternalwind.connectplus.persistence.repositories.ForumRepository;
import io.eternalwind.connectplus.persistence.repositories.MatchRepository;
import io.eternalwind.connectplus.persistence.repositories.MessageAcknowledgeRepository;
import io.eternalwind.connectplus.persistence.repositories.MessageRepository;
import io.eternalwind.connectplus.persistence.repositories.UserRepository;

@Configuration
public class DomainConfig {
    @Bean
    public AuthenticationService authenticationService(UserRepository userRepository) {
        return new AuthenticationService(userRepository);
    }

    @Bean
    public MatchService matchService(UserRepository userRepository, MatchRepository matchRepository) {
        return new MatchService(userRepository, matchRepository);
    }

    @Bean
    public ChatService chatService(MatchService matchService, MessageRepository messageRepository,
            ForumMembershipRepository forumMembershipRepository,
            MessageAcknowledgeRepository messageAcknowledgeRepository) {
        return new ChatService(matchService, messageRepository, forumMembershipRepository,
                messageAcknowledgeRepository);
    }

    @Bean
    public ForumService forumService(ForumRepository forumRepository, ForumMembershipRepository forumMembershipRepository) {
        return new ForumService(forumRepository, forumMembershipRepository);
    }
}
