package com.mabel;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
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

    public static Date convertToDate(ZonedDateTime zonedDateTime) {
        LocalDateTime localDateTime = zonedDateTime.toLocalDateTime();
        return Date.from(localDateTime.toInstant(zonedDateTime.getOffset()));
    }

    public static String formatDateWithZone(Date date, String zoneId) {
        ZonedDateTime zonedDateTime = convertDate(date, zoneId);
        return zonedDateTime.format(DateTimeFormatter.ofPattern(DATE_TIME_FORMATTER));
    }

    /**
     * 通过周获取该周的第一天 入参形如 201901 表示2019年第一周,
     * startDayOfWeek 定义了一周中的第一天，1 ~ 7 表示周一到周日
     * */
    public static ZonedDateTime generateFirstDayByWeekCycle(Integer weekCycle, String zoneId, Integer startDayOfWeek) {
        int year = weekCycle / 100;
        int week = weekCycle % 100;
        ZonedDateTime zonedDateTime = ZonedDateTime.of(year, 1,1,0,0,0,0,ZoneId.of(zoneId));
        ZonedDateTime firstWeekFirstDay = zonedDateTime.with(ChronoField.DAY_OF_WEEK, startDayOfWeek);
        ZonedDateTime firstDayOfWeek = firstWeekFirstDay.plusWeeks(week - 1);
        return firstDayOfWeek;
    }

    /**
     * 通过周获取该周的最后一天 入参形如 201901 表示2019年第一周
     * */
    public static ZonedDateTime generateLastDayByWeekCycle(Integer weekCycle, String zoneId, Integer startDayOfWeek) {
        ZonedDateTime firstDayOfWeek = generateFirstDayByWeekCycle(weekCycle, zoneId, startDayOfWeek);
        ZonedDateTime lastDayOfWeek = firstDayOfWeek.plusDays(6);
        LocalDate localDate = lastDayOfWeek.toLocalDate();
        LocalDateTime localDateTime = LocalDateTime.of(localDate, LocalTime.MAX);
        return ZonedDateTime.of(localDateTime, ZoneId.of(zoneId));
    }

    /**
     * 求两个时间之前的间隔天数，
     * from 到 to 间隔了多少天，其结果为 to - from,
     * 同样的方式可以获取两个时间的时间差，该时间差的单位可以通过 Duration自带的方法转换，
     * 这些方法可以转换成的时间单位包括天，小时，分钟，毫秒，纳秒
     * */
    public static long intervalDays(ZonedDateTime from, ZonedDateTime to) {
        return Duration.between(from, to).toDays();
    }
}