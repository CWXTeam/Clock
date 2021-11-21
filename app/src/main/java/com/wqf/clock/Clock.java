package com.wqf.clock;

public class Clock {
    long ringTime;  // 响铃时间
    Mould mould;  // 闹钟模板
    String mode;  // 上课铃="WORK"；下课铃="BREAK"

    protected Clock() {
        ringTime = 0;
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

}