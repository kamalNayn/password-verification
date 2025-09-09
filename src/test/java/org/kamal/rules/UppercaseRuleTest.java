package org.kamal.rules;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UppercaseRuleTest {

    UppercaseRule rule;

    @Before
    public void setUp() throws Exception {
        rule = new UppercaseRule();
    }

    @After
    public void tearDown() throws Exception {
        rule = null;
    }

    @Test
    public void testValidate(){
        String password = "123";
        boolean validate = rule.validate(password);
        assertFalse(validate);
    }

    @Test
    public void testValidate2(){
        String password = "paSsword123";
        boolean validate = rule.validate(password);
        assertTrue(validate);
    }

    @Test
    public void testValidate3(){
        String password = null;
        boolean validate = rule.validate(password);
        assertFalse(validate);
    }

    @Test
    public void testGetFailureMessage(){
        String failureMessage = rule.getFailureMessage();
        assertNotNull(failureMessage);
    }

    @Test
    public void testGetName(){
        String name = rule.getName();
        assertNotNull(name);
    }

}