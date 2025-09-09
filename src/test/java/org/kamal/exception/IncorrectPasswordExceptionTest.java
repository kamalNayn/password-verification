package org.kamal.exception;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class IncorrectPasswordExceptionTest {

    @Test
    public void testIncorrectPasswordExceptionString(){
        IncorrectPasswordException exception = new IncorrectPasswordException("Invalid Password");
        assertNotNull(exception);
    }
}