package org.kamal.rules;


public class NotNullRule implements PasswordRule {

    @Override
    public boolean validate(String password) {
        return password != null;
    }

    @Override
    public String getFailureMessage() {
        return "Password must not be null.";
    }

    @Override
    public String getName() {
        return "NotNullRule";
    }
}
