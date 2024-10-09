package com.net.msg.util;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class DateUtil {
    // 默认日期字符串格式 "yyyy-MM-dd"
    public final static String DATE_DEFAULT = "yyyy-MM-dd";
    // 日期字符串格式 "yyyyMMdd"
    public final static String DATE_YYYYMMDD = "yyyyMMdd";
    // 格式 map
    private static Map<String, SimpleDateFormat> formatMap;

    // 通过格式获取 SimpleDateFormat 对象
    private static SimpleDateFormat getFormat(String pattern) {
        if (formatMap == null) {
            formatMap = new HashMap<>();
        }

        SimpleDateFormat format = formatMap.get(pattern);
        if (format == null) {
            format = new SimpleDateFormat(pattern);
            formatMap.put(pattern, format);
        }
        return format;
    }

    // 将当前时间转换为字符串
    public static String getNowStr() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern(DATE_YYYYMMDD));
    }
}
