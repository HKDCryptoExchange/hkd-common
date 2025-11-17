package com.hkd.common.util;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * 日期时间工具类
 *
 * @author HKD Team
 */
public class DateUtil {

    public static final String DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_PATTERN = "yyyy-MM-dd";
    public static final String TIME_PATTERN = "HH:mm:ss";
    public static final String DATETIME_COMPACT_PATTERN = "yyyyMMddHHmmss";

    public static final DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ofPattern(DATETIME_PATTERN);
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_PATTERN);
    public static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern(TIME_PATTERN);
    public static final DateTimeFormatter DATETIME_COMPACT_FORMATTER = DateTimeFormatter.ofPattern(DATETIME_COMPACT_PATTERN);

    public static final ZoneId SYSTEM_ZONE_ID = ZoneId.systemDefault();
    public static final ZoneId UTC_ZONE_ID = ZoneId.of("UTC");
    public static final ZoneId HONG_KONG_ZONE_ID = ZoneId.of("Asia/Hong_Kong");

    /**
     * 获取当前时间戳（毫秒）
     */
    public static long currentTimeMillis() {
        return System.currentTimeMillis();
    }

    /**
     * 获取当前时间戳（秒）
     */
    public static long currentTimeSeconds() {
        return System.currentTimeMillis() / 1000;
    }

    /**
     * 获取当前LocalDateTime
     */
    public static LocalDateTime now() {
        return LocalDateTime.now();
    }

    /**
     * 获取当前UTC时间
     */
    public static LocalDateTime nowUtc() {
        return LocalDateTime.now(UTC_ZONE_ID);
    }

    /**
     * 获取当前香港时间
     */
    public static LocalDateTime nowHongKong() {
        return LocalDateTime.now(HONG_KONG_ZONE_ID);
    }

    /**
     * LocalDateTime转时间戳（毫秒）
     */
    public static long toTimestamp(LocalDateTime dateTime) {
        return dateTime.atZone(SYSTEM_ZONE_ID).toInstant().toEpochMilli();
    }

    /**
     * 时间戳（毫秒）转LocalDateTime
     */
    public static LocalDateTime fromTimestamp(long timestamp) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), SYSTEM_ZONE_ID);
    }

    /**
     * LocalDateTime转字符串
     */
    public static String format(LocalDateTime dateTime) {
        return dateTime.format(DATETIME_FORMATTER);
    }

    /**
     * LocalDateTime转字符串（自定义格式）
     */
    public static String format(LocalDateTime dateTime, String pattern) {
        return dateTime.format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 字符串转LocalDateTime
     */
    public static LocalDateTime parse(String dateTimeStr) {
        return LocalDateTime.parse(dateTimeStr, DATETIME_FORMATTER);
    }

    /**
     * 字符串转LocalDateTime（自定义格式）
     */
    public static LocalDateTime parse(String dateTimeStr, String pattern) {
        return LocalDateTime.parse(dateTimeStr, DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 获取今天开始时间
     */
    public static LocalDateTime startOfToday() {
        return LocalDate.now().atStartOfDay();
    }

    /**
     * 获取今天结束时间
     */
    public static LocalDateTime endOfToday() {
        return LocalDate.now().atTime(23, 59, 59, 999999999);
    }

    /**
     * 获取指定日期的开始时间
     */
    public static LocalDateTime startOfDay(LocalDate date) {
        return date.atStartOfDay();
    }

    /**
     * 获取指定日期的结束时间
     */
    public static LocalDateTime endOfDay(LocalDate date) {
        return date.atTime(23, 59, 59, 999999999);
    }

    /**
     * 计算两个时间之间的天数差
     */
    public static long daysBetween(LocalDateTime start, LocalDateTime end) {
        return ChronoUnit.DAYS.between(start, end);
    }

    /**
     * 计算两个时间之间的小时差
     */
    public static long hoursBetween(LocalDateTime start, LocalDateTime end) {
        return ChronoUnit.HOURS.between(start, end);
    }

    /**
     * 计算两个时间之间的分钟差
     */
    public static long minutesBetween(LocalDateTime start, LocalDateTime end) {
        return ChronoUnit.MINUTES.between(start, end);
    }

    /**
     * 计算两个时间之间的秒差
     */
    public static long secondsBetween(LocalDateTime start, LocalDateTime end) {
        return ChronoUnit.SECONDS.between(start, end);
    }

    /**
     * 增加天数
     */
    public static LocalDateTime plusDays(LocalDateTime dateTime, long days) {
        return dateTime.plusDays(days);
    }

    /**
     * 增加小时
     */
    public static LocalDateTime plusHours(LocalDateTime dateTime, long hours) {
        return dateTime.plusHours(hours);
    }

    /**
     * 增加分钟
     */
    public static LocalDateTime plusMinutes(LocalDateTime dateTime, long minutes) {
        return dateTime.plusMinutes(minutes);
    }

    /**
     * 判断是否在指定时间范围内
     */
    public static boolean isBetween(LocalDateTime target, LocalDateTime start, LocalDateTime end) {
        return !target.isBefore(start) && !target.isAfter(end);
    }

    /**
     * 判断是否过期
     */
    public static boolean isExpired(LocalDateTime expireTime) {
        return LocalDateTime.now().isAfter(expireTime);
    }
}
