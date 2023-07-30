package io.eternalwind.connectplus.presentation.viewmodels;

import java.time.Instant;
import java.util.UUID;

import io.eternalwind.connectplus.domain.models.MessageType;

public class GetLatestMsgVMs {
    public static record LatestMessage(UUID senderId, UUID receiverId, String msg, Instant timestamp, MessageType messageType) {
    }
}
