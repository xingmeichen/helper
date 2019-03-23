package com.mabel;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @project: helper
 * @description:
 * @author: Mabel.Chen
 * @create: 2019-03-23 11:24
 **/
public class ZoneDateUtil {

    public static String DATE_TIME_FORMATTER = "yyyy-MM-dd HH:mm:ss";
    public static String ASIA_SHANGHAI_ZONEID = ZoneId.SHORT_IDS.get("CTT");
    public static String AMERICA_LOS_ANGELES = ZoneId.SHORT_IDS.get("PST");

    /**
     * 将形如 2019-03-23 的字符串转换成对应时区的日期
     */
    public static ZonedDateTime convertDateStr(String dateStr, LocalTime localTime, String zoneId) {
        LocalDate localDate = LocalDate.parse(dateStr);
        return ZonedDateTime.of(localDate, localTime, ZoneId.of(zoneId));
    }

    /**
     * 将形如 2019-03-23 14:34:23 的字符串转换成对应时区的日期
     */
    public static ZonedDateTime convertDateTimeStr(String dateTimeStr, String zoneId) {
        LocalDateTime localDateTime = LocalDateTime.parse(dateTimeStr);
        return ZonedDateTime.of(localDateTime, ZoneId.of(zoneId));
    }

    public static ZonedDateTime convertDate(Date date, String zoneId) {
        return ZonedDateTime.ofInstant(date.toInstant(), ZoneId.of(zoneId));
    }

    public static String formateDateWithZone(Date date, String zoneId) {
        ZonedDateTime zonedDateTime = convertDate(date, zoneId);
        return zonedDateTime.format(DateTimeFormatter.ofPattern(DATE_TIME_FORMATTER));
    }
}