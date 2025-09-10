package org.kamal.rules;

/**
 * Rule that checks if a password is longer than 8 characters.
 */
public class LengthRule implements PasswordRule {

    /**
     * Validates that the password length is greater than 8 characters.
     *
     * @param password password to check
     * @return true if password is non-null and longer than 8 characters
     */
    @Override
    public boolean validate(String password) {
        return password != null && password.length() > 8;
    }

    /**
     * @return failure message when password length is too short
     */
    @Override
    public String getFailureMessage() {
        return "Password must be longer than 8 characters.";
    }

    /**
     * @return name of this rule
     */
    @Override
    public String getName() {
        return "LengthRule";
    }
}
