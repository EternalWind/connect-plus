package io.eternalwind.connectplus.presentation.viewmodels;

import java.util.UUID;

public class LoginVMs {
    public record LoginUser(String username, String ppkEncryptedUsername) {

    }

    public record LoggedInUser(UUID id, String pkEncryptedToken) {
        public static final LoggedInUser NO_USER = new LoggedInUser(null, null);
    }
}
