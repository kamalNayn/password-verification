package org.kamal.util;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kamal.exception.IncorrectPasswordException;
import org.kamal.rules.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PasswordValidatorTest {

    PasswordValidator passwordValidator;
    @Before
    public void setUp() throws Exception {
        List<PasswordRule> rules = List.of(
                new NotNullRule(),
                new LowercaseRule(),
                new LengthRule(),
                new UppercaseRule(),
                new NumberRule()
        );
        passwordValidator = new PasswordValidator(rules, 3);
    }

    @After
    public void tearDown() throws Exception {
        passwordValidator = null;
    }

    @Test(expected = IncorrectPasswordException.class)
    public void validatePassword() {
        String password = "ABCDE";
        passwordValidator.validate(password);
    }

    @Test(expected = IncorrectPasswordException.class)
    public void validatePassword2() {
        String password = "pass";
        boolean valid= passwordValidator.validate(password);
        assertFalse(valid);
    }

    @Test
    public void validatePassword3() {
        String password = "pAss";
        boolean valid= passwordValidator.validate(password);
        assertTrue(valid);
    }

    @Test
    public void validatePassword4() {
        String password = "pAssW0rd";
        boolean valid= passwordValidator.validate(password);
        assertTrue(valid);
    }
}