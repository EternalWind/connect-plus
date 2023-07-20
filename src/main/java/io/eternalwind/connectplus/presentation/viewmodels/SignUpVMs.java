package io.eternalwind.connectplus.presentation.viewmodels;

import io.eternalwind.connectplus.domain.models.Sex;

public class SignUpVMs {
    public static record NewUser(String username, String userPk, Sex selfBioSex, Sex selfPsySex) {
    }
}
