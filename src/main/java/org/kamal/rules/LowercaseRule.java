package org.kamal.rules;

/**
 * Rule that checks if a password contains at least one lowercase letter.
 */
public class LowercaseRule implements PasswordRule {

    /**
     * Validates that the password contains at least one lowercase letter.
     *
     * @param password password to check
     * @return true if password is non-null and has at least one lowercase character
     */
    @Override
    public boolean validate(String password) {
        return password != null && password.matches(".*[a-z].*");
    }

    /**
     * @return failure message when no lowercase letter is found
     */
    @Override
    public String getFailureMessage() {
        return "Must contain at least one lowercase";
    }

    /**
     * @return name of this rule
     */
    @Override
    public String getName() {
        return "LowercaseRule";
    }
}
