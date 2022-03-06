package com.mabel;

import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class ZoneDateUtilTest {

    @Test
    public void convertDateStrTest() {
        ZonedDateTime zonedDateTime = ZoneDateUtil.convertDateStr("2019-02-01", LocalTime.MIN, ZoneId.SHORT_IDS.get("JST"));
        String format = zonedDateTime.format(DateTimeFormatter.ofPattern(ZoneDateUtil.DATE_TIME_FORMATTER));
        System.out.println(format);
    }

    @Test
    public void convertDateTimeStrTest() {
        ZoneDateUtil.convertDateTimeStr("2019-03-23 14:34:23", ZoneDateUtil.ASIA_SHANGHAI_ZONEID);
    }

    @Test
    public void convertToDateTest() {
        Date date = new Date();
        ZonedDateTime zonedDateTime = ZoneDateUtil.convertDate(date, ZoneDateUtil.AMERICA_LOS_ANGELES);
        String format = zonedDateTime.format(DateTimeFormatter.ofPattern(ZoneDateUtil.DATE_TIME_FORMATTER));
        System.out.println(format);
    }

    @Test
    public void formatDateWithZoneTest() {
        String s = ZoneDateUtil.formatDateWithZone(new Date(), ZoneDateUtil.ASIA_SHANGHAI_ZONEID);
        System.out.println(s);
    }
}