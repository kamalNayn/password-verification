package org.kamal.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PasswordRuleTest {

    PasswordRule passwordRule;
    @Before
    public void setup(){
        passwordRule = new PasswordRule(p->p.length()>8,
                "Password must be longer than 8 chars");
    }

    @After
    public void destroy(){
        passwordRule = null;
    }

    @Test
    public void test1() {
        boolean test = passwordRule.test("1234");
        assertFalse(test);
    }

    @Test
    public void getFailureMessage() {
        String expectedMessage = "Password must be longer than 8 chars";
        String actualMessage = passwordRule.getFailureMessage();
        assertEquals(expectedMessage, actualMessage);
    }
}