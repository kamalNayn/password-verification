package org.kamal.exception;

import java.util.List;

/**
 * Exception thrown when password validation fails.
 */
public class IncorrectPasswordException extends RuntimeException {

    private final List<String> errorMessages;

    /**
     * Creates a new exception with failure messages.
     *
     * @param errorMessages list of messages for failed rules
     */
    public IncorrectPasswordException(List<String> errorMessages) {
        super(buildMessage(errorMessages));
        this.errorMessages = errorMessages;
    }

    /**
     * @return list of failed validation messages
     */
    public List<String> getErrorMessages() {
        return errorMessages;
    }

    /**
     * Builds a combined message from all errors.
     */
    private static String buildMessage(List<String> messages) {
        StringBuilder sb = new StringBuilder("Password validation failed due to:");
        for (String msg : messages) {
            sb.append("\n - ").append(msg);
        }
        return sb.toString();
    }
}
