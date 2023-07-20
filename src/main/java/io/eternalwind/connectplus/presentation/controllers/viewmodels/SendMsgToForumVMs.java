package io.eternalwind.connectplus.presentation.controllers.viewmodels;

import java.util.UUID;

public class SendMsgToForumVMs {
    public static record SendingForumMessage(String msg, UUID forumId) {
    }
}