package io.eternalwind.connectplus.presentation.controllers.viewmodels;

import io.eternalwind.connectplus.domain.models.Sex;

public class LoginVMs {
    public record LoginUser(String username, String ppkEncryptedUsername) {

    }

    public record LoggedInUser(String username, Sex selfBioSex, Sex selfPsySex, String description,
            String pkEncryptedToken) {
    }
}
