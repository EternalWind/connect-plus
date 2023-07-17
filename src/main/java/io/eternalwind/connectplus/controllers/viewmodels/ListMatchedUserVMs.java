package io.eternalwind.connectplus.controllers.viewmodels;

import java.time.ZonedDateTime;
import java.util.UUID;

import io.eternalwind.connectplus.models.Sex;

public class ListMatchedUserVMs {
    public static record MatchedUser(UUID userId, String username, Sex selfBioSex, Sex selfPsySex, String description,
            String avatarUrl, int distanceInKm, ZonedDateTime whenYouLikeIt) {
    }
}
