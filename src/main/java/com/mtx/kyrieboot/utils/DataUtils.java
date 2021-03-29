package com.mtx.kyrieboot.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Auther: kyriemtx
 * @Date: 2021/3/29 11:39
 * @Description:
 */
public class DataUtils {

    private static final String YYYY_MM_DD_HH_MM_SS ="yyyy-MM-dd'T'HH:mm:ss";


    public static String toYYYY_MM_DD_HH_MM_SS(String paramDate) throws ParseException {
        DateFormat df = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS);
        Date date = df.parse(paramDate);
        return date.toString();

    }

    public static void main(String[] args) throws ParseException {
        String ss = "2020-04-26 09:32:14";
        String result = toYYYY_MM_DD_HH_MM_SS(ss);

    }
}
