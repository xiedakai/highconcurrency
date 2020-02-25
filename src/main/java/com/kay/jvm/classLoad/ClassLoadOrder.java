package com.kay.jvm.classLoad;



class Singleton{

    //private static Singleton singleton = new Singleton();

    public static int counter1;

    public static int counter2 = 0;

    private static Singleton singleton = new Singleton();

    private Singleton(){
        counter1 ++;
        counter2 ++;
    }
    public static Singleton getInstance(){
        return singleton;
    }
}

public class ClassLoadOrder {

    public static void main(String[] args) {

        /**
         *
         * 测试类加载顺序的影响:
         *
         * 一.单例在第7行初始化,值的变化为
         *      1. 类加载准备阶段,为静态属性分配内存并赋值初始值 counter1=0 counter2=0
         *      2. 执行第7行,调用构造方法  counter1=1 counter1=1
         *      3. 执行第9行  counter1=1
         *      4. 执行第11行  counter2的值赋值=0
         *      结果为 counter1=1  counter2=0
         *
         * 二.单例在第13行初始化,值的变化为
         *       1. 类加载准备阶段,为静态属性分配内存并赋值初始值,counter1=0 counter2=0
         *       1. 执行第9行  counter1=0
         *       2. 执行第11行  counter2的值赋值=0
         *       3. 执行第13行,调用构造方法  counter1=1 counter1=1
         *       结果为 counter1=1  counter2=1
         *
         */
        Singleton singleton = Singleton.getInstance();
        System.out.println("counter1 = " + Singleton.counter1);
        System.out.println("counter2 = " + Singleton.counter2);

    }



}
