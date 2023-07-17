package io.eternalwind.connectplus.controllers.viewmodels;

import java.util.UUID;

import io.eternalwind.connectplus.models.Sex;

public class GetRecommendedUserVMs {
    public static record RecommendedUser(UUID userId, String username, Sex selfBioSex, Sex selfPsySex, String description, String avatarUrl, int distanceInKm) {
    }
}
