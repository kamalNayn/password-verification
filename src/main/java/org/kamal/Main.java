package org.kamal;

import org.kamal.exception.IncorrectPasswordException;
import org.kamal.model.ValidationResult;
import org.kamal.rules.*;
import org.kamal.util.PasswordValidator;

import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        List<PasswordRule> rules = List.of(
                new LengthRule(),
                new UppercaseRule(),
                new NumberRule()
                );
        PasswordRule lowerCaseRule = new LowercaseRule();
        PasswordValidator passwordValidator = new PasswordValidator(rules, lowerCaseRule, 3);

        String[] passwords = {
                "abc",
                "Abc124",
                "password123",
                "12345678"
        };
        for(String password: passwords){
            ValidationResult validationResult = passwordValidator.validatePassword(password);
            if(validationResult.isValid()){
                System.out.println("Password: " +password+" is valid!");
            }else{
                System.out.println("Password: " +password+" is Invalid! Below failed rules:\n"
                        +String.join("\n", validationResult.getFailedMessages()));
            }
        }
    }
}