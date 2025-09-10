package org.kamal;

import org.kamal.exception.IncorrectPasswordException;
import org.kamal.rules.*;
import org.kamal.util.PasswordValidator;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<PasswordRule> rules = List.of(
                new NotNullRule(),
                new LowercaseRule(),
                new LengthRule(),
                new UppercaseRule(),
                new NumberRule()
                );
        PasswordValidator passwordValidator = new PasswordValidator(rules, 3);

        String[] passwords = {
                "abc",
                "Abc124",
                "password123",
                "12345678"
        };
        for (String password : passwords) {
            System.out.println("🔑 Testing password: " + password);
            try {
                boolean isValid = passwordValidator.validate(password);
                if (isValid) {
                    System.out.println("✅ Password is valid!\n");
                }
            } catch (IncorrectPasswordException ex) {
                System.out.println("❌ Password validation failed:");
                ex.getErrorMessages().forEach(msg -> System.out.println("   - " + msg));
                System.out.println();
            }
        }
    }
}