package com.itssm.ssm.utils;

import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 自定义类型转换器，配置的是全局变量
 * 把页面传入的字符串转换成date类型
 */
public class StringToDateConverter implements Converter<String, Date> {
    @Override
    public Date convert(String source) {
        if (source==null){
            throw new RuntimeException("日期数据不能为空");
        }
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {
            return sdf.parse(source);
        } catch (ParseException e) {
            throw new RuntimeException("字符数据转换失败");
        }

    }
}
