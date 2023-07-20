package io.eternalwind.connectplus.presentation.controllers.viewmodels;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

public class GetLatestMsgVMs {
    public static record UserMessage(UUID userId, String msg, ZonedDateTime timestamp) {
    }

    public static record ForumMessage(UUID forumId, String msg, ZonedDateTime timestamp, String fromUser) {
    }

    public static record LatestMessages(List<UserMessage> userMsg, List<ForumMessage> forumMsg) {
    }
}
