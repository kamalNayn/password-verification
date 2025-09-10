package org.kamal.model;

import java.util.List;

/**
 * Holds the result of password validation.
 */
public class ValidationResult {

    private final boolean valid;
    private final List<String> failedMessages;

    /**
     * Creates a new validation result.
     *
     * @param valid true if validation passed, false otherwise
     * @param failedMessages list of messages for failed rules
     */
    public ValidationResult(boolean valid, List<String> failedMessages) {
        this.valid = valid;
        this.failedMessages = failedMessages;
    }

    /**
     * @return true if password is valid
     */
    public boolean isValid() {
        return valid;
    }

    /**
     * @return list of failed validation messages
     */
    public List<String> getFailedMessages() {
        return failedMessages;
    }
}
