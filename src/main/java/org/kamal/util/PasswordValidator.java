package org.kamal.util;

import org.kamal.exception.IncorrectPasswordException;
import org.kamal.model.PasswordRule;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class PasswordValidator {

    public static void validatePassword(String password){
        //check if password is not null
        if(password==null){
            throw new IncorrectPasswordException("Password cannot be null");
        }

        //check if password has atleast one lowercase
        if(!password.matches(".*[a-z].*")){
            throw new IncorrectPasswordException("Password must contain atleast one lowercase letter");
        }

        //list of the rules
        List<PasswordRule> rules = List.of(
                new PasswordRule(p->p.length()>8, "Password must be longer than 8 chars"),
                new PasswordRule(p->p.matches(".*[A-Z].*"), "Password must have atleast one uppercase"),
                new PasswordRule(p->p.matches(".*[0-9].*"), "Password must have atleast one number")
        );

        //Thread of size 3
        ExecutorService executorService = Executors.newFixedThreadPool(rules.size());

        //creating list of validations task
        List<Callable<Boolean>> tasks = new ArrayList<>();
        for(PasswordRule rule: rules){
            tasks.add(()->rule.test(password));
        }

        int conditionMets = 1; //as atleast one lowercase is true
        List<String> failedConditions = new ArrayList<>();
        try{
            List<Future<Boolean>> results = executorService.invokeAll(tasks);
            for(int i=0;i<results.size();i++){
                if(results.get(i).get()){
                    conditionMets++;
                }else {
                    failedConditions.add(rules.get(i).getFailureMessage());
                }
            }

        } catch (InterruptedException | ExecutionException e) {
            throw new IncorrectPasswordException("Validation failed due to unexpected error");
        }finally {
            executorService.shutdown();
        }
        if(conditionMets<3){
            throw new IncorrectPasswordException("Password not met these conditions\n"+String.join("\n", failedConditions));
        }
    }
}