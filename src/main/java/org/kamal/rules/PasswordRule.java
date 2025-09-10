package org.kamal.rules;


public interface PasswordRule {

    boolean validate(String password);

    String getFailureMessage();

    String getName();
}
