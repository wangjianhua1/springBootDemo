package com.example.demo.java8;

import java.time.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * filter, map, reduce, find,match, sorted
 *
 * java循环和stream,parallelStream耗时差异
 * https://blog.csdn.net/Al_assad/article/details/82356606
 */
public class StreamTest {

    public static void main(String[] args) {
        List<String> list = Arrays.asList("a", "b", "c", "");
        List<String> filtered = list.stream().filter(str -> !str.isEmpty()).collect(Collectors.toList());
        long count = list.stream().filter(str -> !str.isEmpty()).count();
        list.parallelStream().limit(2);

        Random random = new Random();
        random.ints().limit(10).forEach(System.out::println);

        Optional<Integer> op1 = Optional.of(1);
        System.out.println(op1.get());
        testLocalDateTime();

        String encoder = Base64.getEncoder().encodeToString("wjh".getBytes());
        System.out.println(encoder);
        String mime_encoder = Base64.getMimeEncoder().encodeToString("wjh".getBytes());
        System.out.println(mime_encoder);
    }

    public static void testLocalDateTime() {
        // 获取当前的日期时间
        LocalDateTime now = LocalDateTime.now();
        System.out.println("当前时间: " + now);
        LocalDate date1 = now.toLocalDate();
        System.out.println("date1: " + date1);
        Month month = now.getMonth();
        int day = now.getDayOfMonth();
        int seconds = now.getSecond();
        System.out.println("月: " + month + ", 日: " + day + ", 秒: " + seconds);
        LocalDateTime date2 = now.withDayOfMonth(10).withYear(2012);
        System.out.println("date2: " + date2);
        // 12 december 2014
        LocalDate date3 = LocalDate.of(2014, Month.DECEMBER, 12);
        System.out.println("date3: " + date3);
        // 22 小时 15 分钟
        LocalTime date4 = LocalTime.of(22, 15);
        System.out.println("date4: " + date4);
        // 解析字符串
        LocalTime date5 = LocalTime.parse("20:15:30");
        System.out.println("date5: " + date5);

        ZonedDateTime dateTime = ZonedDateTime.parse("2015-12-03T10:15:30+05:30[Asia/Shanghai]");
        System.out.println("dateTime: " + dateTime.toLocalDateTime());
    }
}

