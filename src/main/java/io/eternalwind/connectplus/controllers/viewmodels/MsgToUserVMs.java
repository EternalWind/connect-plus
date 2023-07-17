package io.eternalwind.connectplus.controllers.viewmodels;

import java.time.ZonedDateTime;
import java.util.UUID;

public class MsgToUserVMs {
    public static record SendingMessage(String msg, UUID userId) {
    }

    public static record SentMessage(ZonedDateTime timestamp) {}
}
