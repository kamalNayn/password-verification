package org.kamal.util;

import org.junit.Test;
import org.kamal.exception.IncorrectPasswordException;


public class PasswordValidatorTest {

    @Test(expected = IncorrectPasswordException.class)
    public void validatePasswordNull(){
        String password = null;
        PasswordValidator.validatePassword(password);
    }

    @Test(expected = IncorrectPasswordException.class)
    public void validatePasswordLengthLessThan8(){
        String password = "abc";
        PasswordValidator.validatePassword(password);
    }

    @Test(expected = IncorrectPasswordException.class)
    public void validatePasswordAllLowerCase(){
        String password = "password";
        PasswordValidator.validatePassword(password);
    }

    @Test(expected = IncorrectPasswordException.class)
    public void validatePasswordAllUpperCase(){
        String password = "PASSWORD";
        PasswordValidator.validatePassword(password);
    }

    @Test(expected = IncorrectPasswordException.class)
    public void validatePasswordAllNumber(){
        String password = "12345678";
        PasswordValidator.validatePassword(password);
    }

    @Test
    public void validatePassword(){
        String password = "Passwo4d";
        PasswordValidator.validatePassword(password);
    }

    @Test(expected = IncorrectPasswordException.class)
    public void validatePasswordOneLowerOneUpper(){
        String password = "Password";
        PasswordValidator.validatePassword(password);
    }
}