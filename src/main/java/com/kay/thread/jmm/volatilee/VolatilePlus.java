package com.kay.thread.jmm.volatilee;

/**
 *  查看 volatile++ 的jvm机器码
 *  1.先编译 VolatilePlus.java
 *  2. javap -c VolatilePlus
 */
public class VolatilePlus {

    private int num;

    public void plus(){
        this.num++;
    }


}
