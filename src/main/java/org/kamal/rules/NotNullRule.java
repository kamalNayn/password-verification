package org.kamal.rules;

/**
 * Rule that checks if a password is not null.
 */
public class NotNullRule implements PasswordRule {

    /**
     * Validates that the password is not null.
     *
     * @param password password to check
     * @return true if password is not null
     */
    @Override
    public boolean validate(String password) {
        return password != null;
    }

    /**
     * @return failure message when password is null
     */
    @Override
    public String getFailureMessage() {
        return "Password must not be null.";
    }

    /**
     * @return name of this rule
     */
    @Override
    public String getName() {
        return "NotNullRule";
    }
}
