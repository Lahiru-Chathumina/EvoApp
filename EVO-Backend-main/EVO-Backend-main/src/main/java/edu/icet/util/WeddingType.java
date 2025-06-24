package edu.icet.util;

import java.util.Arrays;

public enum WeddingType {
    TRADITIONAL;

    public static WeddingType fromName (String name) {
        return name == null ? null : Arrays.stream(WeddingType.values()).
                filter(weddingType -> weddingType.name().equalsIgnoreCase(name)).
                findFirst().
                orElse(null);
    }
}