package io.eternalwind.connectplus.controllers.viewmodels;

import java.util.UUID;

public class SendMsgToForumVMs {
    public static record SendingForumMessage(String msg, UUID forumId) {
    }
}