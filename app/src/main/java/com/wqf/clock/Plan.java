package com.wqf.clock;

public class Plan {
    String name;   // 计划的名称
    String description;  // 计划的描述
    long workTime;  // 单次学习的时间
    long breakTime;  // 单次休息的时间
    long beginTime;  // 开始时间
    long finishTime;  // 结束时间
    Mould mould;  // 使用的闹铃模板
    //ArrayList<Clock> clocks;  // 一组闹钟
}
