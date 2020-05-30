package com.mirror.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author Mirror
 * @CreateDate 2020/3/1.
 *
 *日期类型转换工具类
 */
public class DateUtils {

    //日期转换字符串
    public static String dateToString(Date date, String format){
        SimpleDateFormat sdf = new SimpleDateFormat(format);

        String dateStr = sdf.format(date);
        return dateStr;
    }
    //字符串转换日期
    public static Date StringToDate(String dateStr, String format) throws ParseException {
        SimpleDateFormat sdf  = new SimpleDateFormat(format);
        Date date = sdf.parse(dateStr);
        return date;
    }
}
