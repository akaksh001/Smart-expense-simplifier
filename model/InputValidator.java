package model;

/**
 * Utility class for validating user inputs.
 */
public class InputValidator {

    /**
     * Validates a person's name.
     *
     * @param name the name to validate
     * @return true if the name is not empty, not too long (<= 50 chars), and contains no special characters
     * @throws IllegalArgumentException if the name is invalid
     */
    public static boolean validateName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty.");
        }
        if (name.length() > 50) {
            throw new IllegalArgumentException("Name cannot exceed 50 characters.");
        }
        if (!name.matches("^[a-zA-Z0-9 ]+$")) {
            throw new IllegalArgumentException("Name cannot contain special characters.");
        }
        return true;
    }

    /**
     * Validates an expense amount.
     *
     * @param amount the amount as a String
     * @return true if the amount is a valid positive number
     * @throws IllegalArgumentException if the amount is not a valid positive number
     */
    public static boolean validateAmount(String amount) {
        if (amount == null || amount.trim().isEmpty()) {
            throw new IllegalArgumentException("Amount cannot be empty.");
        }
        try {
            double parsedAmount = Double.parseDouble(amount);
            if (parsedAmount <= 0) {
                throw new IllegalArgumentException("Amount must be greater than zero.");
            }
            return true;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Amount must be a valid number.");
        }
    }

    /**
     * Validates an expense description.
     *
     * @param desc the description to validate
     * @return true if the description is valid (not empty)
     * @throws IllegalArgumentException if the description is empty
     */
    public static boolean validateDescription(String desc) {
        if (desc == null || desc.trim().isEmpty()) {
            throw new IllegalArgumentException("Description cannot be empty.");
        }
        return true;
    }
}
