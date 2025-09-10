package org.kamal.rules;

/**
 * Rule that checks if a password contains at least one uppercase letter.
 */
public class UppercaseRule implements PasswordRule {

    /**
     * Validates that the password contains at least one uppercase letter.
     *
     * @param password password to check
     * @return true if password is non-null and contains an uppercase letter
     */
    @Override
    public boolean validate(String password) {
        return password != null && password.matches(".*[A-Z].*");
    }

    /**
     * @return failure message when no uppercase letter is found
     */
    @Override
    public String getFailureMessage() {
        return "Must contain at least one uppercase letter";
    }

    /**
     * @return name of this rule
     */
    @Override
    public String getName() {
        return "UpperCaseRule";
    }
}
