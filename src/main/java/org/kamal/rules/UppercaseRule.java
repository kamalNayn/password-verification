package org.kamal.rules;

public class UppercaseRule implements PasswordRule{
    @Override
    public boolean validate(String password) {
        return password !=null && password.matches(".*[A-Z].*");
    }

    @Override
    public String getFailureMessage() {
        return "Must contain at least one uppercase letter";
    }

    @Override
    public String getName() {
        return "UpperCaseRule";
    }
}
