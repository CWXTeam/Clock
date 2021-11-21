package com.wqf.clock;

public class Plan {
    protected String name;   // 计划的名称
    protected String description;  // 计划的描述
    protected long workTime;  // 单次学习的时间
    protected long breakTime;  // 单次休息的时间
    protected long beginTime;  // 开始时间
    protected long finishTime;  // 结束时间
    protected Mould mould;  // 使用的闹铃模板
    protected ArrayList<Clock> clocks;  // 一组闹钟

    protected Plan() {
        name = "新计划";
        description = "";
        workTime = 0;
        breakTime = 0;
        beginTime = System.currentTimeMillis();
        finishTime = beginTime;
        mould = new Mould();
        clocks = new ArrayList<Clock>();
    }

    protected ArrayList<Clock> setClocks() throws ClockException{
        // 根据开始时间、结束时间、单次学习时间和单次休息、使用的闹铃模板时间设定一定数量的闹钟

        if(workTime <= 0){
            throw new ClockException("工作一次的时间不能为0！");
        }
        else if(breakTime <= 0){
            throw new ClockException("休息一次的时间不能为0！");
        }
        else if(finishTime <= beginTime){
            throw new ClockException("结束时间不能和开始时间相等！")
        }
        else if(workTime > (finishTime - beginTime)){
            throw new ClockException("工作一次的时间不能超过总时间！")
        }
        else if(breakTime > (finishTime - beginTime)){
            throw  new ClockException("休息一次的时间不能超过总时间！")
        }

        ArrayList<Clock> clks = new ArrayList<clock>();
        long clkTime = beginTime;

        clks.add(new Clock(clkTime, new Mould(), "WORK"));  // 工作时间开始！

        while(true){
            // 如果工作一段时间后还没到finishTime，则响一次下课铃，开始休息
            clkTime += workTime;
            if(clkTime < finishTime){
                clks.add(new Clock(clkTime, new Mould(), "BREAK"));
            }
            else{  // 否则，在到达finishTime时响一次下课铃，结束
                clks.add(new Clock(finishTime, new Mould(), "BREAK"));
                break;
            }

            // 如果休息一段时间后还没到finishTime，则响一次上课铃，开始工作
            clkTime += breakTime;
            if(clkTime < finishTime){
                clks.add(clkTime, new Mould(), "WORK");
            }
            else{  // 否则，到达finishTime时再响一次下课铃，结束
                clks.add(new Clock(finishTime, new Mould(), "BREAK"));
                break;
            }
        }

    }

}
