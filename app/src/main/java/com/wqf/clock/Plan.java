package com.wqf.clock;

public class Plan {
    String name;   // 计划的名称
    String description;  // 计划的描述
    long workTime;  // 单次学习的时间
    long breakTime;  // 单次休息的时间
    long beginTime;  // 开始时间
    long finishTime;  // 结束时间
    Mould mould;  // 使用的闹铃模板
    ArrayList<Clock> clocks;  // 一组闹钟

    Plan() {
        name = "新计划";
        description = "";
        workTime = 0;
        breakTime = 0;
        beginTime = System.currentTimeMillis();
        finishTime = beginTime;
        mould = new Mould();
        clocks = this.setClocks();
    }

    Clock[] setClocks() {
        // 根据开始时间、结束时间、单次学习时间和单次休息时间设定一定数量的闹钟

        Clock[] clks =
    }

}
