package org.kamal.rules;

public class NumberRule implements PasswordRule{
    @Override
    public boolean validate(String password) {
        return password !=null && password.matches(".*[0-9].*");
    }

    @Override
    public String getFailureMessage() {
        return "Must contain at least one number";
    }

    @Override
    public String getName() {
        return "NumberRule";
    }
}
