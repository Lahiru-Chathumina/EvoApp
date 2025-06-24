package edu.icet.util;

import java.util.Arrays;

public enum BudgetType {
    LOW,MEDIUM,HIGH;

    public static BudgetType fromName (String name) {
        return name == null ? null : Arrays.stream(BudgetType.values()).
                filter(budgetType -> budgetType.name().equalsIgnoreCase(name)).
                findFirst().
                orElse(null);
    }
}