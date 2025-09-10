package org.kamal.util;

import org.kamal.exception.IncorrectPasswordException;
import org.kamal.model.ValidationResult;
import org.kamal.rules.PasswordRule;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Utility class responsible for validating passwords against a set of rules.
 *
 * <p>Uses an {@link ExecutorService} to run all rules in parallel and
 * throws an {@link IncorrectPasswordException} if the password does not
 * satisfy the minimum number of required conditions.</p>
 */
public class PasswordValidator {
    private final List<PasswordRule> rules;
    private final int minConditionsRequired;

    /**
     * Constructs a PasswordValidator.
     *
     * @param rules                 list of password rules to evaluate
     * @param minConditionsRequired minimum number of rules that must pass for validation to succeed
     */
    public PasswordValidator(List<PasswordRule> rules, int minConditionsRequired) {
        this.rules = rules;
        this.minConditionsRequired = minConditionsRequired;
    }

    /**
     * Validates the given password against all configured rules.
     *
     * @param password the password to validate
     * @return true if password satisfies at least {@code minConditionsRequired} rules
     * @throws IncorrectPasswordException if password fails validation
     */
    public boolean validate(String password) {
        ValidationResult result = runValidation(password);

        if (!result.isValid()) {
            throw new IncorrectPasswordException(result.getFailedMessages());
        }
        return true;
    }

    /**
     * Runs all configured rules and collects validation results.
     * <p>This method uses a thread pool to evaluate each rule in parallel.</p>
     *
     * @param password password to validate
     * @return ValidationResult containing overall validity and failure messages
     */
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
