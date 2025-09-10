package org.kamal.util;

import org.kamal.exception.IncorrectPasswordException;
import org.kamal.model.ValidationResult;
import org.kamal.rules.PasswordRule;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;


public class PasswordValidator {
    private final List<PasswordRule> rules;
    private final int minConditionsRequired;

    public PasswordValidator(List<PasswordRule> rules, int minConditionsRequired) {
        this.rules = rules;
        this.minConditionsRequired = minConditionsRequired;
    }

    public boolean validate(String password) {
        ValidationResult result = runValidation(password);

        if (!result.isValid()) {
            throw new IncorrectPasswordException(result.getFailedMessages());
        }
        return true;
    }

    private ValidationResult runValidation(String password) {
        List<String> failedMessages = new ArrayList<>();
        ExecutorService executor = Executors.newFixedThreadPool(rules.size());
        List<Callable<Boolean>> tasks = new ArrayList<>();

        for (PasswordRule rule : rules) {
            tasks.add(() -> rule.validate(password));
        }

        int passedCount = 0;

        try {
            List<Future<Boolean>> results = executor.invokeAll(tasks);

            for (int i = 0; i < results.size(); i++) {
                boolean passed = results.get(i).get();
                if (passed) {
                    passedCount++;
                } else {
                    failedMessages.add(rules.get(i).getFailureMessage());
                }
            }
        } catch (InterruptedException | ExecutionException e) {
            failedMessages.add("Validation process was interrupted: " + e.getMessage());
        } finally {
            executor.shutdown();
        }

        boolean isValid = passedCount >= minConditionsRequired;
        return new ValidationResult(isValid, failedMessages);
    }
}
