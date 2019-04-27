package com.mabel;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalUnit;
import java.time.temporal.WeekFields;
import java.util.Date;

public class DateUtil {

    /**
     * 通过一个日期获取其对应的周,
     * startDayOfWeek = 1 表示周一是每周的第一天，startDayOfWeek = 7 表示周日是每周的第一天,
     * 该方法默认 startDayOfWeek = 1
     * */
    public static Integer getWeekCycleByDate(Date date, String zoneId, Integer startDayOfWeek) {
        ZonedDateTime zonedDateTime = ZoneDateUtil.convertDate(date, zoneId);
        int year = zonedDateTime.getYear();
        int week;
        if (7 == startDayOfWeek) {
            week = zonedDateTime.get(WeekFields.SUNDAY_START.weekOfWeekBasedYear());
        } else {
            week = zonedDateTime.get(WeekFields.ISO.weekOfWeekBasedYear());
        }
        int monthValue = zonedDateTime.getMonthValue();
        if (monthValue == 12 && week == 1) {
            return (year + 1) * 100 + week;
        }
        return year * 100 + week;
    }
}
