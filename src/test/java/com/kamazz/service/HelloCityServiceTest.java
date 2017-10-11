package com.kamazz.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import static org.junit.Assert.assertEquals;


public class HelloCityServiceTest {

    private HelloCityService service;

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

//    @Test
//    public void getMessageHelloCity() throws Exception {
//        assertEquals( "Доброй ночи," ,service.getBundle().getString("night"));
//    }

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