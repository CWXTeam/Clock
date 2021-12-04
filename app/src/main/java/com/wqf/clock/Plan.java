package com.wqf.clock;


import java.util.ArrayList;

public class Plan implements Comparable<Plan> {

    //为实现数据库而新增的id属性

    protected long planid = 0;
    protected boolean onStart=false;//计划是否处于工作状态
    protected String name;   // 计划的名称
    protected String description;  // 计划的描述
    protected long workTime;  // 单次学习的时间
    protected long breakTime;  // 单次休息的时间
    protected long beginTime;  // 开始时间
    protected long finishTime;  // 结束时间
    protected Mould mould=new Mould();  // 使用的闹铃模板
    protected ArrayList<Clock> clocks=new ArrayList<Clock>();;  // 一组闹钟

    protected Plan() {
        this.planid = System.currentTimeMillis();
        name = "新计划";
        description = "";
        workTime = 0;
        breakTime = 0;
        beginTime = System.currentTimeMillis();
        finishTime = beginTime;
    }

    protected Plan(String name, String description, long workTime, long breakTime, long beginTime, long finishTime, Mould mould) {
        this.planid = System.currentTimeMillis();
        this.name = name;
        this.description = description;
        this.workTime = workTime;
        this.breakTime = breakTime;
        this.beginTime = beginTime;
        this.finishTime = finishTime;
        this.mould = mould;
    }

    protected ArrayList<Clock> setClocks() throws ClockException {
        // 根据开始时间、结束时间、单次学习时间和单次休息、使用的闹铃模板时间设定一定数量的闹钟

        if (workTime <= 0) {
            throw new ClockException("工作一次的时间不能为0！");
        } else if (breakTime <= 0) {
            throw new ClockException("休息一次的时间不能为0！");
        } else if (finishTime <= beginTime) {
            throw new ClockException("结束时间不能和开始时间相等！");
        } else if (workTime > (finishTime - beginTime)) {
            throw new ClockException("工作一次的时间不能超过总时间！");
        } else if (breakTime > (finishTime - beginTime)) {
            throw new ClockException("休息一次的时间不能超过总时间！");
        }

        ArrayList<Clock> clks = new ArrayList<Clock>();
        long clkTime = beginTime;

        clks.add(new Clock(clkTime, mould, "WORK"));  // 工作时间开始！


        while (true) {
            // 如果工作一段时间后还没到finishTime，则响一次下课铃，开始休息
            clkTime += workTime;
            if (clkTime < finishTime) {
                clks.add(new Clock(clkTime, mould, "BREAK"));
            } else {  // 否则，在到达finishTime时响一次下课铃，结束
                clks.add(new Clock(finishTime, mould, "BREAK"));
                break;
            }

            // 如果休息一段时间后还没到finishTime，则响一次上课铃，开始工作
            clkTime += breakTime;

            if (clkTime < finishTime) {
                clks.add(new Clock(clkTime, mould, "WORK"));
            } else {  // 否则，到达finishTime时再响一次下课铃，结束
                clks.add(new Clock(finishTime, mould, "BREAK"));
                break;
            }
        }

        clocks = clks;
        return clks;
    }


    protected void setName(String name) {
        this.name = name;
        return;
    }


    protected void setDescription(String descrpt) {
        this.description = descrpt;
        return;
    }


    protected void setWorkTime(long time) {
        this.workTime = time;
        return;
    }


    protected void setBreakTime(long time) {
        this.breakTime = time;
        return;
    }


    protected void setBeginTime(long time) {
        this.beginTime = time;
        return;
    }


    protected void setFinishTime(long time) {
        this.finishTime = time;
        return;
    }


    protected void setMould(Mould mld) {
        this.mould = mld;
        return;
    }

    @Override
    public int compareTo(Plan o) {
        int flag;
        if (this.beginTime < o.beginTime)
            flag = -1;
        else
            flag = 1;
        return flag;
    }

    //判断是否和另一计划在计划时间上有无交集
    //例如：参数中的plan开始计划时间是0，结束时间是20；
    //本计划中的开始计划时间是2，结束时间是22，
    //则应输出：true
    public boolean haveIntersectionWith(Plan p) {
        //如果本计划两个时间都在p的“左边”，那么肯定无交集
        if (beginTime < p.beginTime && finishTime < p.beginTime)
            return false;
        //如果本计划两个时间都在p的“右边”，那么也肯定无交集
        return beginTime <= p.finishTime || finishTime <= p.finishTime;
        //其他情况一律认为有交集
    }
}

