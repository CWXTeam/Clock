package com.wqf.clock;

import static android.content.Context.ALARM_SERVICE;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class Clock {
    AlarmManager alarmManager;
    long ringTime;  // 响铃时间
    Mould mould;  // 闹钟模板
    String mode;  // 上课铃="WORK"；下课铃="BREAK"

    protected Clock() {
        ringTime = 0;
        mould = new Mould();
        mode = "WORK";
    }

    protected Clock(long ringTime) {
        this.ringTime = ringTime;
        mould = new Mould();
        mode = "WORK";
    }

    protected Clock(long ringTime, Mould mould, String mode) {
        this.ringTime = ringTime;
        this.mould = mould;
        this.mode = mode;
    }

    protected void setRingTime(long time) {
        this.ringTime = time;
        return;
    }

    protected void setMould(Mould mld) {
        this.mould = mld;
        return;
    }

    protected void setMode(String mode) {
        this.mode = mode;
        return;
    }

    protected void startWork(Context context, int requestCode) {
        //当前时间小于设定响铃时间才开始工作，否则无需工作
        if (System.currentTimeMillis()<=ringTime) {
        alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
        Intent intent = new Intent(context, ClockActivity.class);
        intent.putExtra("mouldName", mould.name);
        intent.putExtra("mode", mode);
        PendingIntent pi = PendingIntent.getActivity(context, requestCode, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        Log.d("debug", "alarmManager还没开始工作");
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, ringTime, pi);
        Log.d("debug", TimeUtil.getStringTime(ringTime));
        Log.d("debug", "alarmManager工作了");
        }
    }
}