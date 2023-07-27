package io.eternalwind.connectplus.presentation.viewmodels;

import java.time.Instant;
import java.util.UUID;

import io.eternalwind.connectplus.domain.models.Sex;

public class ListMatchedUserVMs {
    public static record MatchedUser(UUID id, String username, Sex selfBioSex, Sex selfPsySex, String description,
            String avatarUrl, int distanceInKm, Instant whenYouLikeIt) {
    }
}
