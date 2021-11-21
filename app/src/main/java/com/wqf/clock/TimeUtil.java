package com.wqf.clock;

import android.icu.text.SimpleDateFormat;

import java.text.Format;
import java.util.Date;

public class TimeUtil {
    protected static String translateTime(long time){
        Date date = new Date(time);

        Format format = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");

        return format.format(date);
    }
}
