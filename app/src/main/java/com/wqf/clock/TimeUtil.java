package com.wqf.clock;

import android.icu.text.SimpleDateFormat;

import java.text.Format;
import java.text.ParseException;
import java.util.Date;

public class TimeUtil {
    //将时间戳*1000转化为日期
    public static String getStringTime(long time) {
        Date date = new Date(time);

        Format format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        return format.format(date);
    }

    //将日期转化为时间戳*1000
    public static long dateToStamp(String time) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.parse(time).getTime();
    }
}
