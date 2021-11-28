package ${package}.common;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.WeekFields;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DateTimeUtil {

    private DateTimeUtil() {
        throw new UnsupportedOperationException();
    }

    public static final DateTimeFormatter yyyyMM = DateTimeFormatter.ofPattern("yyyyMM");

    public static final DateTimeFormatter yyyyMMdd = DateTimeFormatter.ofPattern("yyyyMMdd");

    public static final DateTimeFormatter yyyyMMddHHmmssSSS = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");

    public static final DateTimeFormatter yyyy_MM_dd = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static final DateTimeFormatter yyyy_MM_dd_HH_mm_ss = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static LocalDateTime queryByTimestamp(long timestamp) {
        Instant instant = Instant.ofEpochMilli(timestamp);
        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
    }

    public static String localDateFormat(String dateTime, DateTimeFormatter origin, DateTimeFormatter target) {
        return LocalDate.parse(dateTime, origin).format(target);
    }

    public static String format(String dateTime, DateTimeFormatter origin, DateTimeFormatter target) {
        return LocalDateTime.parse(dateTime, origin).format(target);
    }

    public static String nowStr() {
        return LocalDateTime.now().format(yyyy_MM_dd_HH_mm_ss);
    }

    public static String nowStr(DateTimeFormatter formatter) {
        return LocalDateTime.now().format(formatter);
    }

    public static long nowToMidnight() {
        LocalTime midnight = LocalTime.MIDNIGHT;
        LocalDate today = LocalDate.now();
        LocalDateTime todayMidnight = LocalDateTime.of(today, midnight);
        LocalDateTime tomorrowMidnight = todayMidnight.plusDays(1);
        return TimeUnit.NANOSECONDS.toSeconds(Duration.between(LocalDateTime.now(), tomorrowMidnight).toNanos());
    }

    /**
     * LocalDate日期'00:00'的毫秒值.
     *
     * @return
     */
    public static long midnightTimeMillis(LocalDate localDate) {
        LocalDateTime todayMidnight = LocalDateTime.of(localDate, LocalTime.MIDNIGHT);
        return toMilliSecond(todayMidnight);
    }

    /**
     * 当前时间戳下当天的'00:00'的毫秒值.
     *
     * @return
     */
    public static long midnightTimeMillis(long timestamp) {
        LocalDate localDate = queryByTimestamp(timestamp).toLocalDate();
        return midnightTimeMillis(localDate);
    }

    /**
     * 当前时间戳下当月的'00:00'的毫秒值.
     *
     * @return
     */
    public static long monthTimeMillis(long timestamp) {
        LocalDate localDate = queryByTimestamp(timestamp).toLocalDate();
        LocalDate firstDay = localDate.with(TemporalAdjusters.firstDayOfMonth());
        return midnightTimeMillis(firstDay);
    }

    /**
     * 当前时间戳下下周一的'00:00'的毫秒值.
     *
     * @return
     */
    public static long nextWeekTimeMillis(long timestamp) {
        LocalDate localDate = queryByTimestamp(timestamp).toLocalDate();
        LocalDate nextWeek = localDate.plusWeeks(1).with(WeekFields.ISO.dayOfWeek(), 1);
        return midnightTimeMillis(nextWeek);
    }

    /**
     * 当前时间戳下上周一的'00:00'的毫秒值.
     *
     * @return
     */
    public static long lastWeekTimeMillis(long timestamp) {
        LocalDate localDate = queryByTimestamp(timestamp).toLocalDate();
        LocalDate nextWeek = localDate.minusWeeks(1).with(WeekFields.ISO.dayOfWeek(), 1);
        return midnightTimeMillis(nextWeek);
    }

    /**
     * String(localDate) -> midnight timestamp
     *
     * @return
     */
    public static long localDatetoMidNightMilliSecond(String time) {
        LocalDate localDate = LocalDate.parse(time, yyyy_MM_dd);
        return midnightTimeMillis(localDate);
    }

    /**
     * String(localDate) -> max timestamp
     *
     * @return
     */
    public static long localDatetoMaxTimeMillis(String time) {
        LocalDate localDate = LocalDate.parse(time, yyyy_MM_dd);
        return maxTimeMillis(localDate);
    }

    /**
     * String(localDateTime) -> timestamp
     *
     * @return
     */
    public static long localDateTimetoMilliSecond(String time) {
        LocalDateTime localDateTime = LocalDateTime.parse(time, yyyy_MM_dd_HH_mm_ss);
        return toMilliSecond(localDateTime);
    }

    /**
     * 今天'00:00'的毫秒值.
     *
     * @return
     */
    public static long todayMidnightTimeMillis() {
        return midnightTimeMillis(LocalDate.now());
    }

    /**
     * 明天'00:00'的毫秒值.
     *
     * @return
     */
    public static long tomorrowMidnightTimeMillis() {
        return midnightTimeMillis(LocalDate.now().plusDays(1));
    }

    /**
     * LocalDate日期'23:59:59.999999999'的毫秒值.
     *
     * @return
     */
    public static long maxTimeMillis(LocalDate localDate) {
        LocalDateTime todayMidnight = LocalDateTime.of(localDate, LocalTime.MAX);
        return toMilliSecond(todayMidnight);
    }

    /**
     * 今天'23:59:59.999999999'的毫秒值.
     *
     * @return
     */
    public static long todayMaxTimeMillis() {
        return maxTimeMillis(LocalDate.now());
    }

    public static long toMilliSecond(LocalDateTime localDateTime) {
        return localDateTime.toInstant(ZoneOffset.of("+8")).toEpochMilli();
    }

    public static long toMilliSecond(String localDateTime) {
        return LocalDateTime.parse(localDateTime, DateTimeUtil.yyyy_MM_dd_HH_mm_ss).toInstant(ZoneOffset.of("+8")).toEpochMilli();
    }


    public static long toSeconds(LocalDateTime localDateTime) {
        return localDateTime.toEpochSecond(ZoneOffset.of("+8"));
    }

    public static Date toUDate(LocalDateTime localDateTime) {
        Instant instant = localDateTime.toInstant(ZoneOffset.of("+8"));
        return Date.from(instant);
    }

    // 获取本周的开始时间
    public static LocalDateTime getBeginOfWeek() {
        LocalDateTime now = LocalDateTime.now();
        int weekValue = now.getDayOfWeek().getValue();
        LocalDateTime targetTime = now.minusDays(weekValue - 1);
        return LocalDateTime.of(targetTime.toLocalDate(), LocalTime.MIDNIGHT);
    }

    // 获取本周的结束时间
    public static LocalDateTime getEndOfWeek() {
        LocalDateTime now = LocalDateTime.now();
        int weekValue = now.getDayOfWeek().getValue();
        LocalDateTime targetTime = now.plusDays(7 - weekValue);
        return LocalDateTime.of(targetTime.toLocalDate(), LocalTime.MAX);
    }

    /**
     * 获取月份的开始日期
     *
     * @param month
     * @return
     */
    public static String getLastMonthStartDate() {
        LocalDate nowDate = LocalDate.now().minusMonths(1);
        int monthDay = nowDate.getDayOfMonth();
        return nowDate.minusDays(monthDay - 1).format(DateTimeUtil.yyyy_MM_dd);
    }

    /**
     * 获取月份的结束日期
     *
     * @param month
     * @return
     */
    public static String getLastMonthEndDate() {
        LocalDate nowDate = LocalDate.now().minusMonths(1);
        int monthDay = nowDate.getDayOfMonth();
        return nowDate.plusDays(nowDate.lengthOfMonth() - monthDay).format(DateTimeUtil.yyyy_MM_dd);
    }

    /**
     * 获取上个月第一天的'00:00'的毫秒值
     *
     * @return
     */
    public static long lastMonthMidTimeMillis() {
        int lastMonthValue = MonthDay.now().getMonthValue() - 1;
        return toMilliSecond(LocalDateTime.of(LocalDate.of(Year.now().getValue(), lastMonthValue, 1), LocalTime.MIDNIGHT));
    }

    /**
     * 获取上个月最后一天的'23:59:59.999999999'的毫秒值
     *
     * @return
     */
    public static long lastMonthMaxTimeMillis() {
        int lastMonthValue = MonthDay.now().getMonthValue() - 1;
        return toMilliSecond(LocalDateTime.of(LocalDate.of(Year.now().getValue(), lastMonthValue, Month.of(lastMonthValue).length(Year.isLeap(1))), LocalTime.MAX));
    }

    /**
     * 获取当前日期的上个月的同一天
     *
     * @param date
     * @return
     */
    public static String getLastMonthDate(String date) {
        LocalDate localDate = LocalDate.parse(date, DateTimeUtil.yyyy_MM_dd);
        return localDate.minusMonths(1).toString();
    }

    public static String dateToMidnightDateTime(String date) {
        return LocalDateTime.of(LocalDate.parse(date), LocalTime.MIDNIGHT).format(yyyy_MM_dd_HH_mm_ss);
    }

    public static String dateToMaxDateTime(String date) {
        return LocalDateTime.of(LocalDate.parse(date), LocalTime.MAX).format(yyyy_MM_dd_HH_mm_ss);
    }

    /**
     * 获取昨天的日期
     *
     * @return
     */
    public static String lastDayDate() {
        return LocalDate.now().minusDays(1).format(yyyy_MM_dd);
    }

    /**
     * 获取上周开始的时间 2019-09-03
     *
     * @return
     */
    public static String getLastWeekStartDate() {
        LocalDate now = LocalDate.now().minusWeeks(1);
        int weekValue = now.getDayOfWeek().getValue();
        return now.minusDays(weekValue - 1).format(yyyy_MM_dd);
    }

    /**
     * 获取上周开始的时间 2019-09-10
     *
     * @return
     */
    public static String getLastWeekEndDate() {
        LocalDate now = LocalDate.now().minusWeeks(1);
        int weekValue = now.getDayOfWeek().getValue();
        return now.plusDays(7 - weekValue).format(yyyy_MM_dd);
    }

    public static String getMonthEndDate(String date) {
        LocalDate localDate = LocalDate.parse(date, yyyy_MM_dd);
        int monthDay = localDate.getDayOfMonth();
        return localDate.plusDays(localDate.lengthOfMonth() - monthDay).format(yyyy_MM_dd);
    }
    /**
     * 时分秒转秒
     */
    public static long toSecond(String time) {
        long s = 0;
        if(time.length()==8){ //时分秒格式00:00:00
            int index1=time.indexOf(":");
            int index2=time.indexOf(":",index1+1);
            s = Integer.parseInt(time.substring(0,index1))*3600;//小时
            s+=Integer.parseInt(time.substring(index1+1,index2))*60;//分钟
            s+=Integer.parseInt(time.substring(index2+1));//秒
        }
        if(time.length()==5){//分秒格式00:00
            s = Integer.parseInt(time.substring(time.length()-2)); //秒  后两位肯定是秒
            s+=Integer.parseInt(time.substring(0,2))*60;    //分钟
        }
        return s;
    }
}
