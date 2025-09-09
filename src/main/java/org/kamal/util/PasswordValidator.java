package org.kamal.util;

import org.kamal.exception.IncorrectPasswordException;
import org.kamal.model.ValidationResult;
import org.kamal.rules.PasswordRule;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class PasswordValidator {
    private List<PasswordRule> rules;
    private PasswordRule mandatoryLowerCaseRule;
    private int minConditionsRequired;

    public PasswordValidator(List<PasswordRule> rules, PasswordRule mandatoryLowerCaseRule, int minConditionsRequired) {
        this.rules = rules;
        this.mandatoryLowerCaseRule = mandatoryLowerCaseRule;
        this.minConditionsRequired = minConditionsRequired;
    }

    public ValidationResult validatePassword(String password){
        List<String> failedMessages = new ArrayList<>();
        //mandatory lowercase check
        if(!mandatoryLowerCaseRule.validate(password)){
            failedMessages.add(mandatoryLowerCaseRule.getFailureMessage());
            System.out.println("Password validation failed: "+mandatoryLowerCaseRule.getFailureMessage());
            return new ValidationResult(false, failedMessages);
        }

        ExecutorService executorService = Executors.newFixedThreadPool(rules.size());
        List<Callable<Boolean>> tasks = new ArrayList<>();
        for(PasswordRule rule: rules){
            tasks.add(()->rule.validate(password));
        }
        int conditionsMet = 1;
        try{
            List<Future<Boolean>> results = executorService.invokeAll(tasks);
            for(int i=0; i<results.size(); i++){
                if(results.get(i).get()){
                    System.out.println("Validation: "+rules.get(i).getName()+" passed.");
                    conditionsMet++;
                }else {
                    System.out.println("Validation: "+rules.get(i).getName()+" failed.");
                    failedMessages.add(rules.get(i).getFailureMessage());
                }
            }
        } catch (InterruptedException | ExecutionException e) {
            failedMessages.add("Validation failed due to unexpected error");
        }finally {
            executorService.shutdown();
        }
        boolean valid = conditionsMet>=minConditionsRequired;
        return new ValidationResult(valid, failedMessages);
    }
}
