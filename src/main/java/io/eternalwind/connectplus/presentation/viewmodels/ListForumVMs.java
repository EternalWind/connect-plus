package io.eternalwind.connectplus.presentation.viewmodels;

import java.time.Instant;
import java.util.UUID;

public class ListForumVMs {
    public static record Forum(UUID id, String name, String description, Instant createdOn) {
    }
}
