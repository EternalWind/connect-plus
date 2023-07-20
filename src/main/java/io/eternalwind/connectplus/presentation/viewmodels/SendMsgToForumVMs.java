package io.eternalwind.connectplus.presentation.viewmodels;

import java.util.UUID;

public class SendMsgToForumVMs {
    public static record SendingForumMessage(String msg, UUID forumId) {
    }
}