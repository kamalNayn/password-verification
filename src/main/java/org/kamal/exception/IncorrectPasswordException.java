package org.kamal.exception;

import java.util.List;

public class IncorrectPasswordException extends RuntimeException {

    private final List<String> errorMessages;

    public IncorrectPasswordException(List<String> errorMessages) {
        super(buildMessage(errorMessages));
        this.errorMessages = errorMessages;
    }

    public List<String> getErrorMessages() {
        return errorMessages;
    }

    private static String buildMessage(List<String> messages) {
        StringBuilder sb = new StringBuilder("Password validation failed due to:");
        for (String msg : messages) {
            sb.append("\n - ").append(msg);
        }
        return sb.toString();
    }
}
