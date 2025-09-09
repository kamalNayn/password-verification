package org.kamal.util;

import org.kamal.exception.IncorrectPasswordException;

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

        //Thread of size 3
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        //creating list of validations task
        List<Callable<Boolean>> tasks = List.of(
                () -> password.length()>8,
                () -> password.matches(".*[A-Z].*"),
                () -> password.matches(".*[0-9].*")
        );
        int conditionMets = 1; //as atleast one lowercase is true
        List<String> failedConditions = new ArrayList<>();
        try{
            List<Future<Boolean>> results = executorService.invokeAll(tasks);
            if(results.get(0).get()) {
                conditionMets++;
            } else {
                failedConditions.add("Password should be larger than 8 chars");
            }
            if(results.get(1).get()) {
                conditionMets++;
            } else {
                failedConditions.add("Password should have atleast 1 uppercase");
            }
            if(results.get(2).get()) {
                conditionMets++;
            } else {
                failedConditions.add("Password should have atleast 1 number");
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
