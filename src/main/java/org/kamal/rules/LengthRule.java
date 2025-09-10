package org.kamal.rules;


public class LengthRule implements PasswordRule {

    @Override
    public boolean validate(String password) {
        return password != null && password.length() > 8;
    }

    @Override
    public String getFailureMessage() {
        return "Password must be longer than 8 characters.";
    }

    @Override
    public String getName() {
        return "LengthRule";
    }
}
