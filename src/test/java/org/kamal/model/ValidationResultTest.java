package org.kamal.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ValidationResultTest {

    ValidationResult validationResult;

    @Before
    public void setUp() throws Exception {
        List<String> failedMessages = List.of("Error");
        validationResult = new ValidationResult(false, failedMessages);
    }

    @After
    public void tearDown() throws Exception {
        validationResult = null;
    }

    @Test
    public void isValid() {
        boolean valid = validationResult.isValid();
        assertFalse(valid);
    }

    @Test
    public void getFailedMessages() {
        List<String> failedMessages = validationResult.getFailedMessages();
        assertEquals(1, failedMessages.size());
    }
}