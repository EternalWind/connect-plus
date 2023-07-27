package io.eternalwind.connectplus.presentation.mappers;

import java.time.Instant;
import java.util.Optional;

import com.google.cloud.Timestamp;

public interface FirestoreDaoMapper {
    default Timestamp instantToTimestamp(Instant instant) {
        return Optional.ofNullable(instant)
            .map(i -> Timestamp.ofTimeSecondsAndNanos(i.getEpochSecond(), i.getNano()))
            .orElse(null);
    }

    default Instant timestampToInstant(Timestamp timestamp) {
        return Optional.ofNullable(timestamp)
            .map(ts -> Instant.ofEpochSecond(ts.getSeconds(), ts.getNanos()))
            .orElse(null);
    }
}
