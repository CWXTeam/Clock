package com.wqf.clock;

public class Mould {

    String name;  // 闹铃模板名称
    String breakClkPath;  // 下课铃音频路径
    String workClkPath;  // 上课铃音频路径

    Mould() {
        name = "hanser";
        breakClkPath = null;
        workClkPath = null;
    }

    Mould(String name,String breakClkPath,String workClkPath){
        this.name=name;
        this.breakClkPath=breakClkPath;
        this.workClkPath=workClkPath;
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
