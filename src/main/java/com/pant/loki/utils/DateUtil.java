package com.pant.loki.utils;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.DateTimeFormatterBuilder;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Description: Copy from uip-framework
 * Created by xiangheng.song on 2017/10/25 15:15.
 */
public class DateUtil {
    public static DateTimeFormatter FORMATTER_yyyyMMddHHmmss = (new DateTimeFormatterBuilder()).appendYear(4, 4).appendMonthOfYear(2).appendDayOfMonth(2).appendHourOfDay(2).appendMinuteOfHour(2).appendSecondOfMinute(2).toFormatter();
    public static final String DF_Y4M2D2 = "yyyyMMdd";
    public static final String DF_Y4M2D2HMSL = "yyyyMMddHHmmssSSS";
    public static final String DF_Y4M2 = "yyyyMM";
    public static final String DATETIME_FORMAT_NO_SPLIT = "yyyyMMddHHmmss";
    public static final String DATETIME_FORMAT_WITH_SPLIT = "yyyy-MM-dd HH:mm:ss";
    public static final String DATETIME_FORMAT_WITH_SPLIT_NO_SEC = "yyyy-MM-dd HH:mm";
    public static final String DATE_FORMAT_WITHOUT_SPLIT = "yyyyMMdd";
    public static final String DATE_FORMAT_WITH_SPLIT = "yyyy-MM-dd";
    public static final String DATETIME_NO_SECOND_FORMAT_WITH_SPLIT = "yyyy-MM-dd HH:mm";

    public DateUtil() {
    }

    public static String getUtcTime(String bjTime, String dateFormat) {
        return DateTime.parse(bjTime, FORMATTER_yyyyMMddHHmmss).plusHours(8).toString(dateFormat);
    }

    public static String formatTime(String time, String dateFormat) {
        return DateTime.parse(time, FORMATTER_yyyyMMddHHmmss).toString(dateFormat);
    }

    public static String getUtcTime() {
        return DateTime.now().plusHours(8).toString("yyyyMMddHHmmss");
    }

    public static String getUtcTime(String dateFormat) {
        return DateTime.now().plusHours(8).toString(dateFormat);
    }

    public static String getOperateTimeUTC(String utcTime, String dateFormat) {
        return DateTime.parse(utcTime, FORMATTER_yyyyMMddHHmmss).plusHours(-8).toString(dateFormat);
    }

    public static String format(Date date) {
        return (new DateTime(date)).toString("yyyyMMddHHmmss");
    }

    public static String format(Date date, String dateFormat) {
        return date == null ? null : (new DateTime(date)).toString(dateFormat);
    }

    public static Date monthFirstDay(Date date) {
        if (date == null) {
            return null;
        } else {
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            c.set(c.get(1), c.get(2), 1, 0, 0, 0);
            c.set(14, 0);
            return c.getTime();
        }
    }

    public static Date monthLastDay(Date date) {
        if (date == null) {
            return null;
        } else {
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            c.set(2, c.get(2) + 1);
            c.set(5, 0);
            c.set(11, 23);
            c.set(12, 59);
            c.set(13, 59);
            return c.getTime();
        }
    }

    public static String monthFirstDay() {
        return monthFirstDay((String)null, (String)null);
    }

    public static String monthLastDay() {
        return monthLastDay((String)null, (String)null);
    }

    public static String monthFirstDay(String date, String dateFormat) {
        if (dateFormat == null) {
            dateFormat = "yyyyMMddHHmmss";
        }

        if (date == null) {
            SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
            date = sdf.format(new Date());
        }

        Date firstDay = monthFirstDay(DateTime.parse(date, FORMATTER_yyyyMMddHHmmss).toDate());
        return (new DateTime(firstDay)).toString(dateFormat);
    }

    public static String monthLastDay(String date, String dateFormat) {
        if (dateFormat == null) {
            dateFormat = "yyyyMMddHHmmss";
        }

        if (date == null) {
            SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
            date = sdf.format(new Date());
        }

        Date firstDay = monthLastDay(DateTime.parse(date, FORMATTER_yyyyMMddHHmmss).toDate());
        return (new DateTime(firstDay)).toString(dateFormat);
    }

    public static int getSubMonth(Date bigDate, Date smallDate) {
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(bigDate);
        c2.setTime(smallDate);
        int year1 = c1.get(1);
        int month1 = c1.get(2);
        int year2 = c2.get(1);
        int month2 = c2.get(2);
        int subMonth = (year1 - year2) * 12 + month1 - month2;
        return subMonth;
    }

    public static int diffMinute(Date startDate, Date endDate) {
        if (endDate != null && startDate != null) {
            long diffMinute = (endDate.getTime() - startDate.getTime()) / 60000L;
            return (int)diffMinute;
        } else {
            return 10000;
        }
    }

    public static Date StringToDate(String dateStr, String pattern) {
        DateTimeFormatter formatter = DateTimeFormat.forPattern(pattern);
        DateTime dateTime = DateTime.parse(dateStr, formatter);
        return dateTime.toDate();
    }

    public static Date stringToDate(String dateStr) {
        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyyMMddHHmmss");
        DateTime dateTime = DateTime.parse(dateStr, formatter);
        return dateTime.toDate();
    }

    public static Date dayFirstSecond(Date date) {
        if (date == null) {
            return null;
        } else {
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            c.set(11, 0);
            c.set(12, 0);
            c.set(13, 0);
            return c.getTime();
        }
    }

    public static String dayFirstSecond(Date date, String dateFormat) {
        if (dateFormat == null) {
            dateFormat = "yyyyMMddHHmmss";
        }

        if (date == null) {
            date = new Date();
        }

        Date date2 = dayFirstSecond(date);
        return format(date2, dateFormat);
    }

    public static Date nextDayFirstSecond(Date date) {
        if (date == null) {
            return null;
        }

        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DAY_OF_MONTH, c.get(Calendar.DAY_OF_MONTH) + 1);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        return c.getTime();
    }

    /**
     * @description 获取当月的前一天，从那天的0点0分0秒开始
     * @params [date]
     * @returnType java.util.Date
     * @author pantao
     * @date 2018/7/12 11:24
     */
    public static Date beforDayFirstSecond(Date date) {
        if (date == null) {
            date = new Date();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) - 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    public static String getUTCTimeStr() {
        Calendar cal = Calendar.getInstance();
        int zoneOffset = cal.get(15);
        int dstOffset = cal.get(16);
        cal.add(14, -(zoneOffset + dstOffset));
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");

        try {
            return df.format(cal.getTime());
        } catch (Exception var5) {
            var5.printStackTrace();
            return null;
        }
    }

    public static String nextDayFirstSecond(Date date, String dateFormat) {
        if (dateFormat == null) {
            dateFormat = "yyyyMMddHHmmss";
        }

        if (date == null) {
            date = new Date();
        }

        Date date2 = nextDayFirstSecond(date);
        return format(date2, dateFormat);
    }

    /**
     * @description 获取当月的前一天，从那天的0点0分0秒开始
     * @params [date, dateFormat]
     * @returnType java.lang.String
     * @author pantao
     * @date 2018/7/12 11:25
     */
    public static String beforeDayFirstSecond(Date date, String dateFormat) {
        if (StringUtils.isEmpty(dateFormat)) {
	        dateFormat = "yyyyMMddHHmmss";
        }

	    if (date == null) {
		    date = new Date();
	    }

	    return format(beforDayFirstSecond(date), dateFormat);
    }

    public static void main(String[] args) throws ParseException {
       // System.out.println(format(DateUtils.addHours(DateUtils.parseDate("20160430160000", new String[]{"yyyyMMddHHmmss"}), 8), "yyyyMMddHHmmss"));
        //System.out.println(getUTCTimeStr());
        System.out.println(StringToDate("20180101010000", "yyyyMMddHHmmss"));
    }
}
