package org.kamal.rules;

/**
 * Represents a single password validation rule.
 */
public interface PasswordRule {

    /**
     * Validates the given password against this rule.
     *
     * @param password password to check
     * @return true if the rule passes, false otherwise
     */
    boolean validate(String password);

    /**
     * @return message describing why this rule failed
     */
    String getFailureMessage();

    /**
     * @return name of this rule
     */
    String getName();
}
