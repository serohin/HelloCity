package com.kamazz.validator;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.*;


import static org.junit.Assert.assertTrue;
import static org.junit.runners.Parameterized.Parameters;


@RunWith(Parameterized.class)
public class ParameterValidatorImplTest {

    private String[] args;
    public ParameterValidatorImplTest(String[] args) {
        this.args = args;
    }

    @Before
    public void setUp() throws Exception {   }
    @After
    public void tearDown() throws Exception {    }

   @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {new String[]{"Dnipro", "Europe/Kiev"}},
                {new String[]{"Tokyo", "Asia/Tokyo"}},
                {new String[]{"Berlin"}},
                {new String[]{"New", "York","America/New_York"}},
                {new String[]{"Los", "Angeles"}},
                {new String[]{"Los Angeles"}},
                {new String[]{"San Carlos de Bariloche"}},
                {new String[]{"Bo"}},
                {new String[]{"Dnipro", "EET"}},
        });
    }


    @Test
    public void testValidate() throws Exception {
        ParameterValidator paramValidator =  new ParameterValidatorImpl();
        Map<String, String> errorMap = paramValidator.validate(args);
        assertTrue(errorMap.isEmpty());
    }

}