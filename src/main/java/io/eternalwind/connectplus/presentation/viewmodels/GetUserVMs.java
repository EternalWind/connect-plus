package io.eternalwind.connectplus.presentation.viewmodels;

import io.eternalwind.connectplus.domain.models.Sex;

public class GetUserVMs {
    public static record UserInfo(String username, Sex selfBioSex, Sex selfPsySex, String description,
            String pkEncryptedToken) {
    }
}
