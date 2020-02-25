package com.kay.jvm.classLoad.classinit;

import java.util.Random;

class ClassInit2{
    public static final int x = new Random().nextInt(100);

    static {
        System.out.println("ClassInit2 静态代码块");
    }
}

public class ClassInitDemo2 {

    public static void main(String[] args) {
        /**
         * 测试类的初始化2:
         * 上面的静态代码块会被执行,ClassInit2执行了初始化
         *  因为x在编译时不能赋值
         */
        System.out.println(ClassInit2.x);
    }

}
