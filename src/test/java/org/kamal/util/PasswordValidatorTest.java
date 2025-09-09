package org.kamal.util;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kamal.exception.IncorrectPasswordException;
import org.kamal.model.ValidationResult;
import org.kamal.rules.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PasswordValidatorTest {

    PasswordValidator passwordValidator;
    @Before
    public void setUp() throws Exception {
        List<PasswordRule> rules = List.of(
                new LengthRule(),
                new UppercaseRule(),
                new NumberRule()
        );
        PasswordRule lowerCaseRule = new LowercaseRule();
        passwordValidator = new PasswordValidator(rules, lowerCaseRule, 3);
    }

    @After
    public void tearDown() throws Exception {
        passwordValidator = null;
    }

    @Test
    public void validatePassword() {
        String password = "ABCDE";
        ValidationResult validationResult = passwordValidator.validatePassword(password);
        assertFalse(validationResult.isValid());
    }

    @Test
    public void validatePassword2() {
        String password = "pass";
        ValidationResult validationResult = passwordValidator.validatePassword(password);
        assertFalse(validationResult.isValid());
    }

    @Test
    public void validatePassword3() {
        String password = "pAss";
        ValidationResult validationResult = passwordValidator.validatePassword(password);
        assertFalse(validationResult.isValid());
    }

    @Test
    public void validatePassword4() {
        String password = "pAssW0rd";
        ValidationResult validationResult = passwordValidator.validatePassword(password);
        assertTrue(validationResult.isValid());
    }
}