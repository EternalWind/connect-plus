package io.eternalwind.connectplus.controllers.viewmodels;

import java.time.ZonedDateTime;
import java.util.UUID;

public class ListForumVMs {
    public static record Forum(UUID forumId, String forumName, String forumDescription, ZonedDateTime createdOn) {
    }
}
