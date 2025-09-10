package org.kamal.exception;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IncorrectPasswordExceptionTest {

    IncorrectPasswordException exception;
    @Before
    public void setUp() throws Exception {
        List<String> errorMessages = List.of("error1", "error2");
        exception = new IncorrectPasswordException(errorMessages);
    }

    @After
    public void tearDown() throws Exception {
        exception = null;
    }

    @Test
    public void testGetErrorMessages(){
        List<String> errorMessages = exception.getErrorMessages();
        assertEquals(2, errorMessages.size());
    }
}