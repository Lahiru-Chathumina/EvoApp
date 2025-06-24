package edu.icet.util;

import java.util.Arrays;

public enum EventType {
    WEDDING,
    BIRTHDAY_PARTIES,
    GET_TOGETHER,
    ANNIVERSARIES;

    public static EventType fromName (String name) {
        return name == null ? null : Arrays.stream(EventType.values()).
                filter(eventType -> eventType.name().equalsIgnoreCase(name)).
                findFirst().
                orElse(null);
    }
}