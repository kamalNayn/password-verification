package org.kamal.util;

import org.junit.Test;
import org.kamal.exception.IncorrectPasswordException;

import static org.junit.jupiter.api.Assertions.*;

public class PasswordValidatorTest {

    @Test(expected = IncorrectPasswordException.class)
    public void isValidPasswordNull(){
        String password = null;
        PasswordValidator.isValidPassword(password);
    }

    @Test(expected = IncorrectPasswordException.class)
    public void isValidPasswordLengthLessThan8(){
        String password = "abc";
        PasswordValidator.isValidPassword(password);
    }

    @Test
    public void isValidPasswordAllLowerCase(){
        String password = "password";
        boolean validPassword = PasswordValidator.isValidPassword(password);
        assertTrue(validPassword);
    }

    @Test(expected = IncorrectPasswordException.class)
    public void isValidPasswordAllUpperCase(){
        String password = "PASSWORD";
        PasswordValidator.isValidPassword(password);
    }

    @Test(expected = IncorrectPasswordException.class)
    public void isValidPasswordAllNumber(){
        String password = "12345678";
        PasswordValidator.isValidPassword(password);
    }

    @Test
    public void isValidPassword(){
        String password = "Passwo4d";
        boolean validPassword = PasswordValidator.isValidPassword(password);
        assertTrue(validPassword);
    }

    @Test
    public void isValidPasswordOneLowerOneUpper(){
        String password = "Password";
        boolean validPassword = PasswordValidator.isValidPassword(password);
        assertTrue(validPassword);

    }
}