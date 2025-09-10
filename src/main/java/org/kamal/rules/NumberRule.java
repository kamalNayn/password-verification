package org.kamal.rules;

/**
 * Rule that checks if a password contains at least one numeric digit.
 */
public class NumberRule implements PasswordRule {

    /**
     * Validates that the password contains at least one digit.
     *
     * @param password password to check
     * @return true if password is non-null and contains a digit
     */
    @Override
    public boolean validate(String password) {
        return password != null && password.matches(".*[0-9].*");
    }

    /**
     * @return failure message when no digit is found
     */
    @Override
    public String getFailureMessage() {
        return "Must contain at least one number";
    }

    /**
     * @return name of this rule
     */
    @Override
    public String getName() {
        return "NumberRule";
    }
}
