package model;

/**
 * Represents the category of an expense.
 */
public enum ExpenseCategory {
    FOOD("Food"),
    TRANSPORT("Transport"),
    ENTERTAINMENT("Entertainment"),
    UTILITIES("Utilities"),
    OTHER("Other");

    private final String displayName;

    /**
     * Constructs a new ExpenseCategory with the given display name.
     *
     * @param displayName the display name of the category
     */
    ExpenseCategory(String displayName) {
        this.displayName = displayName;
    }

    /**
     * Gets the display name of the category.
     *
     * @return the display name
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * Returns the ExpenseCategory corresponding to the given string.
     * If the string doesn't match any category, returns OTHER.
     *
     * @param text the string representation of the category
     * @return the corresponding ExpenseCategory, or OTHER if not found
     */
    public static ExpenseCategory fromString(String text) {
        if (text == null) {
            return OTHER;
        }
        for (ExpenseCategory b : ExpenseCategory.values()) {
            if (b.displayName.equalsIgnoreCase(text) || b.name().equalsIgnoreCase(text)) {
                return b;
            }
        }
        return OTHER;
    }
}
