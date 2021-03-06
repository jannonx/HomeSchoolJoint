package com.overdose.homeschooljoint.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class CalenderUtils {
    private SimpleDateFormat simpleDateFormatFull;
    private SimpleDateFormat simpleDateFormatByDay;
    private SimpleDateFormat simpleDateFormatByYear;
    private SimpleDateFormat simpleDateFormatByChineseDay;
    private SimpleDateFormat simpleDateFormatByChineseMonthAndDay;
    private SimpleDateFormat simpleDateFormatByHourAndMinute;
    private SimpleDateFormat simpleDateFormatByChineseYearMonth;
    private SimpleDateFormat simpleDateFormatByNumericYearMonth;
    private static CalenderUtils instance;
    private String[] chineseMonths =
            new String[]{"一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月",};
    private String[] chineseWeekDays = new String[]{"周日", "周一", "周二", "周三", "周四", "周五", "周六"};

    //2019-11-28 08:56:36
    private CalenderUtils() {
        simpleDateFormatFull = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
        simpleDateFormatByDay = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        simpleDateFormatByYear = new SimpleDateFormat("yyyy", Locale.CHINA);
        simpleDateFormatByChineseDay = new SimpleDateFormat("yyyy年MM月dd日", Locale.CHINA);
        simpleDateFormatByChineseMonthAndDay = new SimpleDateFormat("MM月dd日", Locale.CHINA);
        simpleDateFormatByHourAndMinute = new SimpleDateFormat("HH时mm分", Locale.CHINA);
        simpleDateFormatByChineseYearMonth = new SimpleDateFormat("yyyy年MM月", Locale.CHINA);
        simpleDateFormatByNumericYearMonth = new SimpleDateFormat("yy-MM", Locale.CHINA);
    }

    public static CalenderUtils getInstance() {
        if (instance == null) {
            synchronized (CalenderUtils.class) {
                if (instance == null) {
                    instance = new CalenderUtils();
                }
            }
        }
        return instance;
    }

    public boolean checkIfTheSameDay(long day1, long day2) {
        Calendar left = Calendar.getInstance();
        left.setTimeInMillis(day1);

        Calendar right = Calendar.getInstance();
        right.setTimeInMillis(day2);

        if (left.get(Calendar.YEAR) == right.get(Calendar.YEAR)
                && left.get(Calendar.MONTH) == right.get(Calendar.MONTH)
                && left.get(Calendar.DAY_OF_MONTH) == right.get(Calendar.DAY_OF_MONTH)) {
            return true;
        }
        return false;
    }

    /**
     * 根据数字月份获取相应的中文月份，数字从0开始代表一月。
     */
    public String getChineseMonth(int month) {
        month = month % chineseMonths.length;
        return chineseMonths[month];
    }

    public String getChineseDayOfWeek(long timeMillis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timeMillis);
        int i = calendar.get(Calendar.DAY_OF_WEEK);
        return chineseWeekDays[i - 1];
    }

    public String getChineseTimeOfDay(long timeMillis) {
        return simpleDateFormatByHourAndMinute.format(new Date(timeMillis));
    }

    public String toChineseYearMonth(long timeMillis) {
        return simpleDateFormatByChineseYearMonth.format(new Date(timeMillis));
    }

    public String toNumericYearMonth(long timeMillis) {
        return simpleDateFormatByNumericYearMonth.format(new Date(timeMillis));
    }

    public String getChineseMonthAndDay(long timeMillis) {
        return simpleDateFormatByChineseMonthAndDay.format(new Date(timeMillis));
    }

    public String toSmartFactoryDateStringFormat(long time) {
        return simpleDateFormatFull.format(new Date(time));
    }

    public String getYearByDate(long time) {
        return simpleDateFormatByYear.format(new Date(time));
    }

    /**
     * 根据时间戳long,获取当天日期
     */
    public String getDateByDate(long time) {
        return simpleDateFormatByDay.format(new Date(time));
    }

    public Date parseSmartFactoryDateStringFormat(String date) {
        try {
            return simpleDateFormatFull.parse(date);
        } catch (Exception e) {
            e.printStackTrace();
            LogUtils.showLog(e.getMessage());
        }
        return null;
    }

    public String parseSmartFactoryChineseDateStringFormat(String date) {
        try {
            Date simpleDate = simpleDateFormatFull.parse(date);
            if (simpleDate != null) {
                return simpleDateFormatByChineseDay.format(simpleDate);
            }
        } catch (Exception e) {
            e.printStackTrace();
            LogUtils.showLog(e.getMessage());
        }
        return null;
    }

    public Date parseSmartFactoryDateFormatByDay(String date) {
        try {
            return simpleDateFormatByDay.parse(date);
        } catch (Exception e) {
            e.printStackTrace();
            LogUtils.showLog(e.getMessage());
        }
        return null;
    }

    public String toSmartFactoryDateFormatByDay(long time) {
        return simpleDateFormatByDay.format(new Date(time));
    }

    public String toChineseMonthAndDay(long time) {
        return simpleDateFormatByChineseMonthAndDay.format(new Date(time));
    }

    public String getHourAndMinuteDescription(long duration) {
        long hour = duration / 1000 / 60 / 60;
        long minute = (duration - hour * 1000 * 60 * 60) / 1000 / 60;
        if (hour > 0) {
            return String.format(Locale.CHINA, "%d小时%d分", hour, minute);
        } else if (minute >= 0) {
            return String.format(Locale.CHINA, "%d分", minute);
        } else {
            return "时间间隔小于0分，请检查";
        }
    }

    public String getDaysByDate(long duration) {
        double result;
        long days = duration / 1000 / 60 / 60 / 24;
        long hours = (duration - days * 1000 * 60 * 60 * 24) / 1000 / 60 / 60;
        if (hours > 4) {
            result = ++days;
        } else {
            result = days + 0.5;
        }
        return result + "";
    }

    public String getHoursByDate(long duration) {
        double result;
        long hours = duration / 1000 / 60 / 60;
        long minute = (duration - hours * 1000 * 60 * 60) / 1000 / 60;
        if (minute > 30) {
            result = hours + 0.5;
        } else {
            result = hours;
        }
        return result + "";
    }

    public long returnXMonthsAgoInYearMonthFormat(long date, int x) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(date);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        int month = calendar.get(Calendar.MONTH) + 1;
        int year = calendar.get(Calendar.YEAR);
        for (int i = 0; i < x; i++) {
            month--;
            if (month == 0) {
                month = 12;
                year--;
            }
        }
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.YEAR, year);
        return calendar.getTimeInMillis();
    }

    //将字符串时间格式转为long形
    public long getTimeToLong(String DateTime) {
        Date date = null;
        long time = -1;
        try {
            date = simpleDateFormatFull.parse(DateTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (date != null) {
            time = date.getTime();
        }

        return time;
    }

    /**
     * 计算两段时间包含了多少个月。
     *
     * @param startTime
     * @param endTime
     * @return
     */
    public int getMonthCountByDates(long startTime, long endTime) {
        Calendar from = Calendar.getInstance();
        from.setTimeInMillis(startTime);
        Calendar to = Calendar.getInstance();
        to.setTimeInMillis(endTime);
        int monthCountByEnd = to.get(Calendar.YEAR) * 12 + (to.get(Calendar.MONTH) + 1);
        int monthCountFromStart = from.get(Calendar.YEAR) * 12 + (from.get(Calendar.MONTH) + 1);
        return monthCountByEnd - monthCountFromStart + 1;
    }

}
