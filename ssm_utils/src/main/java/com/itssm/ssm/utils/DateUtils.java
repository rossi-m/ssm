package com.itssm.ssm.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    //日期转换成字符串
public static String date2String(Date date , String pattern) {
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
    String format = simpleDateFormat.format(date);
    return format;
}
    //字符串转换成日期
    public static Date string2Date(String date , String pattern) throws Exception {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date date1 = simpleDateFormat.parse(date);
        return date1;
    }

}
