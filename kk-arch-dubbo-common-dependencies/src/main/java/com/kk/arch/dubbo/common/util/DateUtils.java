/*
 * Copyright 2023-2024 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.kk.arch.dubbo.common.util;

import org.springframework.util.Assert;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;

/**
 * @author Zal
 */
public class DateUtils {

    public static final String YYYY_MM = "yyyy-MM";
    public static final String YYYY_MM_DD = "yyyy-MM-dd";
    public static final String YYYY_MM_DD_HH = "yyyy-MM-dd HH";
    public static final String YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";
    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    public static final String YYYY_MM_DD_HH_MM_SS_SSS = "yyyy-MM-dd HH:mm:ss.SSS";
    public static final String YYYYMMDD = "yyyyMMdd";
    public static final String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
    public static final String YYMM = "yyMM";
    public static final String HH_MM_SS = "HH:mm:ss";
    public static final String DAY_LAST_TIME = "23:59:59";
    public static final String DAY_FIRST_TIME = "00:00:00";
    public static final int ONE_DAY_SECONDS = 86400;
    public static final int ONE_HOUR_SECONDS = 3600;
    public static final int ONE_MINUTE_SECONDS = 60;

    public DateUtils() {
    }

    /**、
     * 将秒数转换成 X天X小时X分X秒
     */
    public static String prettySecond(int second) {
        int days = second / 86400;
        int modSecond = second % 86400;
        int hours = modSecond / 3600;
        modSecond %= 3600;
        int minutes = modSecond / 60;
        modSecond %= 60;

        StringBuilder sb = new StringBuilder();
        if (days > 0) {
            sb.append(days).append("天");
        }
        if (hours > 0) {
            sb.append(hours).append("小时");
        }
        if (minutes > 0) {
            sb.append(minutes).append("分");
        }
        if (modSecond > 0) {
            sb.append(modSecond).append("秒");
        }
        return sb.toString();
    }

    /**
     * 是不是闫年
     */
    public static boolean isLeapYear(int year) {
        if (year % 400 == 0) {
            return true;
        } else if (year % 100 == 0) {
            return false;
        } else {
            return year % 4 == 0;
        }
    }

    /**
     * 日期转字符串，格式: yyyy-MM-dd
     */
    public static String format(Date date) {
        return format(date, YYYY_MM_DD_HH_MM_SS);
    }

    /**
     * 日期转字符串，格式传参进来
     */
    public static String format(Date date, String fmt) {
        SimpleDateFormat sdf = new SimpleDateFormat(fmt);
        return sdf.format(date);
    }

    /**
     * 字符串转换成日期，格式自动检测
     */
    public static Date parse(String dateStr) {
        String fmt = getFmtStr(dateStr);
        return parse(dateStr, fmt);
    }

    /**
     * 字符串转换成日期，格式自动检测
     */
    public static Date parse(String dateStr, String fmt) {
        SimpleDateFormat sdf = new SimpleDateFormat(fmt);
        try {
            return sdf.parse(dateStr);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 获取日期时间的格式
     */
    public static String getFmtStr(String dateStr) {
        if (dateStr == null || dateStr.isBlank()) {
            return "";
        } else {
            int len = dateStr.trim().length();
            if (len == 10) {
                return "yyyy-MM-dd";
            } else if (len == 19) {
                return "yyyy-MM-dd HH:mm:ss";
            } else if (len >= 21 && len <= 23) {
                return "yyyy-MM-dd HH:mm:ss.SSS";
            } else if (len == 7) {
                return "yyyy-MM";
            } else if (len == 8) {
                return "HH:mm:ss";
            } else if (len == 13) {
                return "yyyy-MM-dd HH";
            } else if (len == 14) {
                return "yyyyMMddHHmmss";
            } else {
                return len == 16 ? "yyyy-MM-dd HH:mm" : "";
            }
        }
    }

    /**
     * 加上几个月，负则是减几个月
     */
    public static Date addMonths(Date date, int month) {
        Calendar cal = getCalendar(date);
        cal.add(Calendar.MONTH, month);
        return cal.getTime();
    }

    /**
     * 加上几年，负则是减几年
     */
    public static Date addYears(Date date, int year) {
        Calendar cal = getCalendar(date);
        cal.add(Calendar.YEAR, year);
        return cal.getTime();
    }

    /**
     * 加上几天，负则是减几天
     */
    public static Date addDays(Date date, int day) {
        Calendar cal = getCalendar(date);
        cal.add(Calendar.DATE, day);
        return cal.getTime();
    }
    /**
     * 加上几个小时，负则是减几个小时
     */
    public static Date addHours(Date date, int hour) {
        Calendar cal = getCalendar(date);
        cal.add(Calendar.HOUR_OF_DAY, hour);
        return cal.getTime();
    }

    /**
     * 加上几分钟，负则是减几分钟
     */
    public static Date addMinutes(Date date, int minute) {
        Calendar cal = getCalendar(date);
        cal.add(Calendar.MINUTE, minute);
        return cal.getTime();
    }

    /**
     * 加上几秒，负则是减几秒
     */
    public static Date addSeconds(Date date, int second) {
        Calendar cal = getCalendar(date);
        cal.add(Calendar.SECOND, second);
        return cal.getTime();
    }

    /**
     * 获取参数日期的日历对象
     */
    public static Calendar getCalendar(Date date) {
        Calendar calendar = Calendar.getInstance();
        if (date != null) {
            calendar.setTime(date);
        }
        return calendar;
    }

    /**
     * 获取参数日期的日历对象
     */
    public static Calendar getCalendar(String date) throws ParseException {
        Date d = parse(date);
        return getCalendar(d);
    }

    /**
     * 获取参数日期时间的年份
     */
    public static int getYear(Date date1) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date1);
        return calendar.get(Calendar.YEAR);
    }

    /**
     * 获取参数日期时间的月份，月份是从0开始的，所以+1
     */
    public static int getMonth(Date date1) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date1);
        return calendar.get(Calendar.MONTH) + 1;
    }

    /**
     * 获取参数日期时间的日子
     */
    public static int getDay(Date date1) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date1);
        return calendar.get(Calendar.DATE);
    }

    /**
     * 获取参数日期时间的星期，星期一返回1，星期天返回7，星期天是一周的最后一天
     */
    public static int getWeek(Date date1) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date1);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

        // 如果是 Sunday (1)，返回 7；否则，返回 dayOfWeek - 1
        return (dayOfWeek == Calendar.SUNDAY) ? 7 : dayOfWeek - 1;
    }

    /**
     * 转成日期时间
     */
    public static Date toDate(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        return calendar.getTime();
    }

    /**
     * 获取当前日期时间
     */
    public static Date getNow() {
        return new Date();
    }

    /**
     * 获取一周最小的日期时间
     */
    public static Date getMinDateOfWeek(Date date) {
        int week = getWeek(date);
        return getMinDateOfDay(addDays(date, -(week - 1)));
    }

    /**
     * 获取一周最大的日期时间
     */
    public static Date getMaxDateOfWeek(Date date) {
        int week = getWeek(date);
        return getMaxDateOfDay(addDays(date, (7 - week)));
    }

    /**
     * 获取日期月份第一天的最小日期时间，比如2024-11-01 00:00:00
     */
    public static Date getMinDateOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DATE, calendar.getActualMinimum(Calendar.DATE));
        calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMinimum(Calendar.HOUR_OF_DAY));
        calendar.set(Calendar.MINUTE, calendar.getActualMinimum(Calendar.MINUTE));
        calendar.set(Calendar.SECOND, calendar.getActualMinimum(Calendar.SECOND));
        calendar.set(Calendar.MILLISECOND, calendar.getActualMinimum(Calendar.MILLISECOND));
        return calendar.getTime();
    }

    /**
     * 获取日期月份最后一天的最大日期时间，比如2024-11-30 23:59:59
     */
    public static Date getMaxDateOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
        calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMaximum(Calendar.HOUR_OF_DAY));
        calendar.set(Calendar.MINUTE, calendar.getActualMaximum(Calendar.MINUTE));
        calendar.set(Calendar.SECOND, calendar.getActualMaximum(Calendar.SECOND));
        calendar.set(Calendar.MILLISECOND, calendar.getActualMaximum(Calendar.MILLISECOND));
        return calendar.getTime();
    }

    /**
     * 获取日期的最小日期时间，比如2024-11-15 00:00:00
     */
    public static Date getMinDateOfDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMinimum(Calendar.HOUR_OF_DAY));
        calendar.set(Calendar.MINUTE, calendar.getActualMinimum(Calendar.MINUTE));
        calendar.set(Calendar.SECOND, calendar.getActualMinimum(Calendar.SECOND));
        calendar.set(Calendar.MILLISECOND, calendar.getActualMinimum(Calendar.MILLISECOND));
        return calendar.getTime();
    }

    /**
     * 获取日期的最大日期时间，比如2024-11-15 23:59:59
     */
    public static Date getMaxDateOfDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMaximum(Calendar.HOUR_OF_DAY));
        calendar.set(Calendar.MINUTE, calendar.getActualMaximum(Calendar.MINUTE));
        calendar.set(Calendar.SECOND, calendar.getActualMaximum(Calendar.SECOND));
        calendar.set(Calendar.MILLISECOND, calendar.getActualMaximum(Calendar.MILLISECOND));
        return calendar.getTime();
    }

    /**
     * 获取日期的最小小时的日期时间，比如2024-11-15 14:00:00
     */
    public static Date getMinDateOfHour(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.MINUTE, calendar.getActualMinimum(Calendar.MINUTE));
        calendar.set(Calendar.SECOND, calendar.getActualMinimum(Calendar.SECOND));
        calendar.set(Calendar.MILLISECOND, calendar.getActualMinimum(Calendar.MILLISECOND));
        return calendar.getTime();
    }

    /**
     * 获取日期的最大小时的日期时间，比如2024-11-15 14:59:59
     */
    public static Date getMaxDateOfHour(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.MINUTE, calendar.getActualMaximum(Calendar.MINUTE));
        calendar.set(Calendar.SECOND, calendar.getActualMaximum(Calendar.SECOND));
        calendar.set(Calendar.MILLISECOND, calendar.getActualMaximum(Calendar.MILLISECOND));
        return calendar.getTime();
    }

    /**
     * 获取两个时间的秒钟时间差
     */
    public static int getIntervalSecond(Date startDate, Date endDate) {
        Assert.isTrue(startDate != null && endDate != null, "参数不能为空");
        return Math.abs((int) ((endDate.getTime() - startDate.getTime()) / 1000L));
    }

    /**
     * 获取两个时间的分钟时间差
     */
    public static int getIntervalMinute(Date startDate, Date endDate) {
        Assert.isTrue(startDate != null && endDate != null, "参数不能为空");
        return Math.abs((int) ((endDate.getTime() - startDate.getTime()) / 60000L));
    }

    /**
     * 获取两个时间的时钟时间差
     */
    public static int getIntervalHour(Date startDate, Date endDate) {
        Assert.isTrue(startDate != null && endDate != null, "参数不能为空");
        return Math.abs((int) ((endDate.getTime() - startDate.getTime()) / 3600000L));
    }

    /**
     * 获取两个时间的天数时间差
     */
    public static int getIntervalDay(Date startDate, Date endDate) {
        Assert.isTrue(startDate != null && endDate != null, "参数不能为空");
        return Math.abs((int) ((endDate.getTime() - startDate.getTime()) / 86400000L));
    }

    /**
     * 获取两个时间的月份数时间差
     */
    public static int getIntervalMonth(Date startDate, Date endDate) {
        Assert.isTrue(startDate != null && endDate != null, "参数不能为空");

        if (startDate.after(endDate)) {
            Date tmp = endDate;
            endDate = startDate;
            startDate = tmp;
        }

        Calendar calendar = Calendar.getInstance();

        calendar.setTime(startDate);
        int monthS = calendar.get(Calendar.MONTH);
        int yearS = calendar.get(Calendar.YEAR);

        calendar.setTime(endDate);
        int monthE = calendar.get(Calendar.MONTH);
        int yearE = calendar.get(Calendar.YEAR);

        return yearE == yearS ? monthE - monthS : (yearE - yearS - 1) * 12 + (12 - monthS) + monthE;
    }

    /**
     * 获取两个时间的年份数时间差
     */
    public static int getIntervalYear(Date startDate, Date endDate) {
        Assert.isTrue(startDate != null && endDate != null, "参数不能为空");
        return Math.abs(getYear(endDate) - getYear(startDate));
    }

    /**
     * 获取年纪，传生日参数
     */
    public static int getAge(String birthday) throws ParseException {
        Date d = parse(birthday);
        if (d == null) {
            return 0;
        } else {
            int age = getIntervalYear(new Date(), d);
            return Math.abs(age);
        }
    }

    /**
     * 按天返回两个日期之间的日期时间列表
     */
    public static List<Date> splitDateByDay(Date startDate, Date endDate) {
        if (startDate != null && endDate != null) {
            List<Date> dateList = new ArrayList<>();
            dateList.add(startDate);
            int num = getIntervalDay(startDate, endDate);
            for (int i = 1; i <= num; ++i) {
                dateList.add(addDays(startDate, i));
            }
            return dateList;
        } else {
            return null;
        }
    }

    /**
     * 按月返回两个日期之间的日期时间列表
     */
    public static List<Date> splitDateByMonth(Date startDate, Date endDate) {
        List<Date> dateList = new ArrayList<>();
        if (startDate != null && endDate != null) {
            dateList.add(startDate);
            int num = getIntervalMonth(startDate, endDate);
            for (int i = 1; i <= num; ++i) {
                dateList.add(addMonths(startDate, i));
            }
        }
        return dateList;
    }

    /**
     * 按年返回两个日期之间的日期时间列表
     */
    public static List<Date> splitDateByYear(Date startDate, Date endDate) {
        List<Date> dateList = new ArrayList<>();
        if (startDate != null && endDate != null) {
            dateList.add(startDate);
            int num = getIntervalYear(startDate, endDate);
            for (int i = 1; i <= num; ++i) {
                dateList.add(addYears(startDate, i));
            }
        }
        return dateList;
    }

    /**
     * 获取参数日期所在季度的第一天的日期时间
     */
    public static Date getQuarterFirstDate(Date paramDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(paramDate);

        int month = calendar.get(Calendar.MONTH);
        if (month <= 2) {
            calendar.set(Calendar.MONTH, 0);
        } else if (month <= 5) {
            calendar.set(Calendar.MONTH, 3);
        } else if (month <= 8) {
            calendar.set(Calendar.MONTH, 6);
        } else {
            calendar.set(Calendar.MONTH, 9);
        }

        calendar.set(Calendar.DATE, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        return calendar.getTime();
    }

    /**
     * 获取参数日期上个季度第一天的日期时间
     */
    public static Date getLastQuarterFirstDate(Date paramDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(paramDate);

        int month = calendar.get(Calendar.MONTH);
        if (month <= 2) {
            calendar.add(Calendar.YEAR, -1);
            calendar.set(Calendar.MONTH, 9);
        } else if (month <= 5) {
            calendar.set(Calendar.MONTH, 0);
        } else if (month <= 8) {
            calendar.set(Calendar.MONTH, 3);
        } else {
            calendar.set(Calendar.MONTH, 6);
        }

        calendar.set(Calendar.DATE, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        return calendar.getTime();
    }

    /**
     * 获取参数日期上个季度最后一天的日期时间
     */
    public static Date getLastQuarterLastDate(Date paramDate) {
        Date lastQuarterFirstDate = getLastQuarterFirstDate(paramDate);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(lastQuarterFirstDate);

        calendar.add(Calendar.MONTH, 3);
        calendar.add(Calendar.DATE, -1);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);

        return calendar.getTime();
    }

    /**
     * 获取两个日期的最大值
     */
    public static Date max(Date date1, Date date2) {
        if (date1 == null && date2 == null) {
            return null;
        } else if (date1 == null) {
            return date2;
        } else if (date2 == null) {
            return date1;
        } else {
            return date1.after(date2) ? date1 : date2;
        }
    }

    /**
     * 获取两个日期的最小值
     */
    public static Date min(Date date1, Date date2) {
        if (date1 == null && date2 == null) {
            return null;
        } else if (date1 == null) {
            return date2;
        } else if (date2 == null) {
            return date1;
        } else {
            return date1.after(date2) ? date2 : date1;
        }
    }

    /**
     * 取参数日期到当天结束时间的剩余时长，precision可以是HH,mm，传空就是秒
     */
    public static int getRemainingTimeOfDay(Date date1, String precision) {
        Date date2 = getMaxDateOfDay(date1);
        int diffNum = 0;
        if ("HH".equals(precision)) {
            diffNum = getIntervalHour(date1, date2);
        } else if ("mm".equals(precision)) {
            diffNum = getIntervalMinute(date1, date2);
        } else {
            diffNum = getIntervalSecond(date1, date2);
        }
        return diffNum;
    }

    /**
     * 取参数日期到当天开始时间的已过时长，precision可以是HH,mm，传空就是秒
     */
    public static int getPassedTimeOfDay(Date date1, String precision) {
        Date date2 = getMinDateOfDay(date1);
        int diffNum = 0;
        if ("HH".equals(precision)) {
            diffNum = getIntervalHour(date1, date2);
        } else if ("mm".equals(precision)) {
            diffNum = getIntervalMinute(date1, date2);
        } else {
            diffNum = getIntervalSecond(date1, date2);
        }
        return diffNum;
    }

    /**
     * 判断参数日期时间是不是最大的日期时间
     */
    public static boolean isMaxDateOfDay(Date date1) {
        return getIntervalSecond(date1, getMaxDateOfDay(date1)) == 0;
    }

    /**
     * 判断参数日期时间是不是最小的日期时间
     */
    public static boolean isMinDateOfDay(Date date1) {
        return getIntervalSecond(date1, getMinDateOfDay(date1)) == 0;
    }

    /**
     * 判断参数日期时间是不是月份最大的日期时间
     */
    public static boolean isMaxDateOfMonth(Date date1) {
        return getIntervalSecond(date1, getMaxDateOfMonth(date1)) == 0;
    }

    /**
     * 判断参数日期时间是不是月份最小的日期时间
     */
    public static boolean isMinDateOfMonth(Date date1) {
        return getIntervalSecond(date1, getMinDateOfMonth(date1)) == 0;
    }

    /**
     * 判断参数日期时间是不是同一天，不管时间
     */
    public static boolean isSameDay(Date startDate, Date endDate) {
        String startDateStr = format(startDate, "yyyy-MM-dd");
        String endDateStr = format(endDate, "yyyy-MM-dd");
        return startDateStr.equals(endDateStr);
    }

    /**
     * 判断参数日期时间是不是同一月，不管天时分秒
     */
    public static boolean isSameMonth(Date startDate, Date endDate) {
        String startDateStr = format(startDate, "yyyy-MM");
        String endDateStr = format(endDate, "yyyy-MM");
        return startDateStr.equals(endDateStr);
    }

    /**
     * 判断参数日期时间是不是同一年，不管月天时分秒
     */
    public static boolean isSameYear(Date startDate, Date endDate) {
        String startDateStr = format(startDate, "yyyy");
        String endDateStr = format(endDate, "yyyy");
        return startDateStr.equals(endDateStr);
    }

    /**
     * 参数日期时间是不是在一个开始的时间与结束的时间的区段内
     */
    public static boolean isBetween(Date pDate, String startHms, String endHms) {
        final String datePart = format(pDate, YYYY_MM_DD);
        final Date startDate = parse(datePart + " " + startHms, YYYY_MM_DD_HH_MM_SS);
        final Date endDate = parse(datePart + " " + endHms, YYYY_MM_DD_HH_MM_SS);
        return isBetween(pDate, startDate, endDate);
    }

    /**
     * 参数日期时间是不是在开始日期时间与结束日期时间之间
     */
    private static boolean isBetween(Date pDate, Date startDate, Date endDate) {
        return !(pDate.before(startDate) || pDate.after(endDate));
    }

}