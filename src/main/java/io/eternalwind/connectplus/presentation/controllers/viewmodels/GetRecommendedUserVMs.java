package io.eternalwind.connectplus.presentation.controllers.viewmodels;

import java.util.UUID;

import io.eternalwind.connectplus.domain.models.Sex;

public class GetRecommendedUserVMs {
    public static record RecommendedUser(UUID userId, String username, Sex selfBioSex, Sex selfPsySex, String description, String avatarUrl, int distanceInKm) {
    }
}
