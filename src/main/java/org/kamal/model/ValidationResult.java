package org.kamal.model;

import java.util.List;

public class ValidationResult {
    private boolean valid;
    private List<String> failedMessages;

    public ValidationResult(boolean valid, List<String> failedMessages) {
        this.valid = valid;
        this.failedMessages = failedMessages;
    }

    public boolean isValid(){
        return valid;
    }

    public List<String> getFailedMessages(){
        return failedMessages;
    }
}
