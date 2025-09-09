package org.kamal.model;

import java.util.function.Predicate;

public class PasswordRule {
    private Predicate<String> rule;
    private String failureMessage;

    public PasswordRule(Predicate<String> rule, String failureMessage) {
        this.rule = rule;
        this.failureMessage = failureMessage;
    }

    public boolean test(String password){
        return rule.test(password);
    }

    public String getFailureMessage(){
        return failureMessage;
    }
}