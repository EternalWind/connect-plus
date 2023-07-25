package io.eternalwind.connectplus.presentation.viewmodels;

import java.util.UUID;

import io.eternalwind.connectplus.domain.models.Sex;

public class GetRecommendedUserVMs {
    public static record RecommendedUser(UUID id, String username, Sex selfBioSex, Sex selfPsySex, String description, String avatarUrl, int distanceInKm) {
    }
}
