package com.kay.jvm.classLoad.classinit;

public class ClassInitDemo3 {

    static {
        i = 0;
        /**
         * 静态代码块只能访问在静态代码块之前声明的变量,在之后声明的变量 只能赋值,不能访问
         */
        //System.out.println(i);
    }
    static int i = 1 ;

}
