package io.eternalwind.connectplus.controllers.viewmodels;

import io.eternalwind.connectplus.models.Sex;

public class SignUpVMs {
    public static record NewUser(String username, String userPk, Sex selfBioSex, Sex selfPsySex) {
    }
}
