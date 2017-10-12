package com.kamazz.service;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;


public class HelloCityService {
    public static final String FILE_MESSAGE_PROPERTY = "Message";
    public static final String GMT_TIME_ZONE = "GMT";
    public static final int INDEX_LAST_ELEM_IN_ARRAY = 1;
    public static final int INDEX_FIRST_ELEM_IN_ARRAY = 0;
    public static final int INDEX_START_MORNING = 6;
    public static final int INDEX_END_MORNING = 9;
    public static final int INDEX_START_DAY = 9;
    public static final int INDEX_END_DAY = 19;
    public static final int INDEX_START_EVENING = 19;
    public static final int INDEX_END_EVENING = 23;


    private String cityName;
    private String idTimeZone;
    private List<String> zoneIdList;
    private ResourceBundle bundle;

    public HelloCityService(String[] arg) {
        this.bundle = ResourceBundle.getBundle(FILE_MESSAGE_PROPERTY, Locale.getDefault());
        this.zoneIdList = new ArrayList(ZoneId.getAvailableZoneIds());
        if (arg.length == 1) {
            this.setCityName(arg[INDEX_FIRST_ELEM_IN_ARRAY]);
            this.setIdTimeZone(this.getTimeZoneByCityName());
        } else {
            String lastElemArray = arg[arg.length - 1];
            if ((lastElemArray.contains("/") & this.zoneIdList.contains(lastElemArray)|| (this.zoneIdList.contains(lastElemArray)))) {
                this.setIdTimeZone(lastElemArray);
                this.setCityName(this.getCityNameFromArgs(arg, INDEX_LAST_ELEM_IN_ARRAY));
            } else {
                this.setCityName(this.getCityNameFromArgs(arg));
                this.setIdTimeZone(this.getTimeZoneByCityName());
            }
        }
    }


    public String getTimeZoneByCityName() {
        String city = cityName.replaceAll("\\s+", "_");
        for (int i = 0; i < zoneIdList.size(); i++) {
            String str = zoneIdList.get(i);
            int num = str.lastIndexOf(city);
            if (num != -1) {
                return zoneIdList.get(i);
            }
        }
        return GMT_TIME_ZONE;
    }

    private String getCityNameFromArgs(String[] args) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < args.length; i++) {
            sb.append(args[i]).append(" ");
        }
        return  sb.toString().trim();
    }

    private String getCityNameFromArgs(String[] args, int index) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < args.length - index; i++) {
            sb.append(args[i]).append(" ");
        }
        return  sb.toString().trim();
    }


    public String getMessageHelloCity() {
        String periodDay = getNameOfDayPeriod(getHourOfTimeZone());
        return bundle.getString(periodDay);
    }


    public String getNameOfDayPeriod(int currentHour) {
        String dayPeriod;
        if (currentHour >= INDEX_START_MORNING && currentHour < INDEX_END_MORNING) {
            dayPeriod = "morning";
        } else if (currentHour >= INDEX_START_DAY && currentHour < INDEX_END_DAY) {
            dayPeriod = "day";
        } else if (currentHour >= INDEX_START_EVENING && currentHour < INDEX_END_EVENING) {
            dayPeriod = "evening";
        } else {
            dayPeriod = "night";
        }
        return dayPeriod;
    }

    public int getHourOfTimeZone() {
        Instant instant= Instant.now();
        ZonedDateTime timeZone = instant.atZone(ZoneId.of(idTimeZone));
        return timeZone.getHour();
    }


    private void setIdTimeZone(String idTimeZone) {
        this.idTimeZone = idTimeZone;
    }
    private void setCityName(String cityName) {
        this.cityName = cityName;
    }
    public String getCityName() {
        return cityName;
    }
    public String getIdTimeZone() {
        return idTimeZone;
    }
    public ResourceBundle getBundle() {
        return bundle;
    }





}
