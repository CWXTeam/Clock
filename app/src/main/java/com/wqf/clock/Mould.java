package com.wqf.clock;

public class Mould {

    String name;  // 闹铃模板名称
    String breakClkPath;  // 下课铃音频路径
    String workClkPath;  // 上课铃音频路径

    Mould() {
        name = "新模块";
        breakClkPath = null;
        workClkPath = null;
    }


    public void setName(String name) {
        this.name = name;
        return;
    }

    public void setBreakClkPath(String breakClkPath) {
        this.breakClkPath = breakClkPath;
        return;
    }


    public String setWorkClkPath(String breakClkPath) {
        this.workClkPath = workClkPath;
        return null;
    }
}
