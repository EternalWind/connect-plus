package io.eternalwind.connectplus.presentation.viewmodels;

import java.time.Instant;
import java.util.UUID;

public class GetLatestMsgVMs {
    public static record UserMessage(UUID userId, String msg, Instant timestamp) {
    }

    public static record ForumMessage(UUID forumId, String msg, Instant timestamp, String fromUser) {
    }
}
