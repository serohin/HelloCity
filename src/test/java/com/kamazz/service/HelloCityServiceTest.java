package com.kamazz.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Locale;

import static org.junit.Assert.assertEquals;

/**
 * Created by kamazz on 11.10.17.
 */
public class HelloCityServiceTest {

    HelloCityService service;

    @Before
    public void setUp() throws Exception {
        service = new HelloCityService(new String[]{"Kiev","Europe/Kiev"});
    }
    @After
    public void tearDown() throws Exception {    }


    @Test
    public void getTimeZoneByCityName() throws Exception {
        assertEquals("Europe/Kiev", service.getTimeZoneByCityName());

    }

    @Test
    public void getMessageHelloCity() throws Exception {
        String expectedMsg;
        if(service.getBundle().getLocale().equals(Locale.ENGLISH)){
            expectedMsg ="Good night,";
        }else{
            expectedMsg ="Доброй ночи,";
        }
        assertEquals( expectedMsg ,service.getBundle().getString("night"));
    }

    @Test
    public void getNameOfDayPeriod() throws Exception {
        assertEquals("day", service.getNameOfDayPeriod(17));

    }

    @Test
    public void getHourOfTimeZone() throws Exception {
        Instant instant = Instant.now();
        ZonedDateTime timeZone = instant.atZone(ZoneId.of("Europe/Kiev"));
        int expectedHour = timeZone.getHour();
        assertEquals(expectedHour, service.getHourOfTimeZone());
    }

}