package edu.icet.util;

import java.util.Arrays;

public enum EventStatusType {
    SCHEDULED,
    STARTED,
    IN_PROGRESS ,
    ON_HOLD,
    POSTPONED,
    CANCELLED,
    COMPLETED;

    public static EventStatusType fromName (String name) {
        return name == null ? null : Arrays.stream(EventStatusType.values()).
                filter(eventStatusType -> eventStatusType.name().equalsIgnoreCase(name)).
                findFirst().
                orElse(null);
    }
}