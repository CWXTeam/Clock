package com.wqf.clock;

public class ClockException extends Exception{
    String msg;  // 错误信息

    public ClockException(String msg){
        super(msg);
    }

}