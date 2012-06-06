/* 
 * @(#)StringToDateConverter.java    Created on 2012-6-6
 * Copyright (c) 2005-2012 Shunwang. All rights reserved.
 * $Id$
 */
package demo.spring.mvc.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

/**
 * 将字符串转换为日期对象的转换器。
 * 
 * @author wj.huang
 * @version $Revision: 1.0 $, $Date: 2012-6-6 下午1:24:10 $
 */
public class StringToDateConverter implements Converter<String, Date> {

    private String datePattern = "yyyy-MM-dd";

    @Override
    public Date convert(String source) {
        DateFormat dateFormat = new SimpleDateFormat(datePattern);

        Date target = null;
        try {
            target = dateFormat.parse(source);
        } catch (ParseException e) {
            ; // Ignore
        }
        return target;
    }

    /**
     * 日期的格式，默认为：yyyy-MM-dd。
     * 
     * @param datePattern
     *            日期的格式
     */
    public void setDatePattern(String datePattern) {
        this.datePattern = datePattern;
    }

}
