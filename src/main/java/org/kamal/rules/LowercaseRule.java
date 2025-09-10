package org.kamal.rules;


public class LowercaseRule implements PasswordRule {

    @Override
    public boolean validate(String password) {
        return password != null && password.matches(".*[a-z].*");
    }

    @Override
    public String getFailureMessage() {
        return "Must contain at least one lowercase";
    }

    @Override
    public String getName() {
        return "LowercaseRule";
    }
}
