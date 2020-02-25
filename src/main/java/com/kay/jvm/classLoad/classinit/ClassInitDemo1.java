package com.kay.jvm.classLoad.classinit;


class ClassInit1{
    public static final int x = 6/3;

    static {
        System.out.println("ClassInit1 静态代码块");
    }
}

public class ClassInitDemo1 {

    public static void main(String[] args) {
        /**
         * 测试类的初始化1:
         * 上面的静态代码块不会被执行,ClassInit1没有被初始化
         * 因为x的值在编译时能被赋值
         */
        System.out.println(ClassInit1.x);
    }

}
